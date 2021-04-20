package com.imooc.my;

import java.util.Map;
import java.util.Objects;

/**
 * @author Ace
 * @create 2020-04-21
 */
public class MyHashMap<K,V> {

    public MyHashMap(float loadFactor) {
        this.loadFactor = loadFactor;
    }

    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        MyHashMap.Node<K,V> next;

        Node(int hash,K key,V value,MyHashMap.Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey() { return key; }
        public final V getValue() { return value; }
        public final String toString() { return key + "=" + value; }

        public final int haseCode() { return Objects.hashCode(key) ^ Objects.hashCode(value); }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this) return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?, ?>) o;
                if (Objects.equals(key,e.getKey()) && Objects.equals(value,e.getValue())) {
                    return true;
                }
            }
            return false;
        }
    }

    static final int TREEIFY_THRESHOLD = 8;

    transient Node<K,V>[] table; //哈希桶数组  transient关键字  变量前加了此关键字  序列化对象的时候忽略此变量

    /**
     * map put方法
     * @param key
     * @param value
     * @return
     */
    public V put(K key, V value) {
        return putVal(hash(key), key, value);
    }

    /**
     * 哈希算法  取 key的hashCode 亦或  key的hashCode的高16位
     *   解释  主要是从速度、功效、质量来考虑的，这么做可以在数组table的length比较小的时候，也能保证考虑到高低Bit都参与到Hash的计算中，同时不会有太大的开销
     *       ？？  目前还不懂这个操作
     * @param key
     * @return
     */
    static final int hash(Object key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    final V putVal(int hash,K key,V value) {
        int n; //数组长度
        int i; //对象在数组中的位置
        Node<K,V> p;
        Node<K,V>[] tab;
        // 如果map还是空的，则先开始初始化，table是map中用于存放索引的表
        if ((tab=table) == null || (n = tab.length)==0)
            n = resize().length;
        // 根据key计算的hash值得到该对象在数组中的保存位(数组索引i) 如果此处没对象  直接插入
        // 其中  hash&(n-1) 等价于 hash%n 只是&比%快  & 是位运算  直接对内存数据进行操作  不需要转换十进制
        if ((p = tab[i=hash & (n-1)])==null)
            tab[i] = newNode(hash, key, value, null);
        // 如果走到else这一步，说明key索引到的数组位置上已经存在内容，即出现了碰撞
        // 这个时候需要更为复杂处理碰撞的方式来处理，如链表和树
        else {
            Node<K,V> e;K k;
            // 判断key是否存在
            // 其中p已经在上面通过计算索引找到了，即发生碰撞那一个节点
            // 比较，如果该节点的hash和当前的hash相等，而且key也相等或者
            // 在key不等于null的情况下key的内容也相等，则说明两个key是一样的，
            // 则将当前节点p用临时节点e保存
            if (p.hash==hash && ((k=p.key) == key || (key!=null && key.equals(k)))) {
                e = p;
            }
            // 如果当前节点p是（红黑）树类型的节点，则需要特殊处理
            // 如果是树，则说明碰撞已经开始用树来处理，后续的数据结构都是树而非链表了
            else if (p instanceof TreeNode) {
                // 其中this表示当前HashMap, tab为map中的数组
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            }
            else {
                for(int count=0; ;++count) {
                    // 如果当前碰撞到的节点没有后续节点，则直接新建节点并追加
                    if ((e=p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        // TREEIFY_THRESHOLD = 8
                        // 从0开始的，如果到了7则说明满8了，这个时候就需要转
                        // 重新确定是否是扩容还是转用红黑树了
                        if ( count > TREEIFY_THRESHOLD-1 ) {
                            //转换成红黑树
                        }
                        break;
                    }
                    if (e.hash==hash && ((k=e.key)==key || (key!=null && key.equals(k))))
                        break;
                    p = e;
                }
            }

            // 此时的e是保存的被碰撞的那个节点，即老节点
            if (e!=null) {
                V oldValue = e.value;
                if(e.value == null) e.value = value;
                return oldValue;
            }

        }

        return null;
    }

    int threshold;

    final float loadFactor;

    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    final Node<K,V>[] resize() {
        //旧数组
        Node<K,V>[] oldTab = table;
        //旧数组的容量
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        //旧数组的扩容阈值，注意看，这里取的是当前对象的 threshold 值，下边的第2种情况会用到。
        int oldThr = threshold;
        //初始化新数组的容量和阈值，分三种情况讨论。
        int newCap, newThr = 0;
        //1.当旧数组的容量大于0时，说明在这之前肯定调用过 resize扩容过一次，才会导致旧容量不为0。
        //为什么这样说呢，之前我在 tableSizeFor 卖了个关子，需要注意的是，它返回的值是赋给了 threshold 而不是 capacity。
        //我们在这之前，压根就没有在任何地方看到过，它给 capacity 赋初始值。
        if (oldCap > 0) {
            //容量达到了最大值
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            //新数组的容量和阈值都扩大原来的2倍
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                    oldCap >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1; // double threshold
        }
        //2.到这里，说明 oldCap <= 0，并且 oldThr(threshold) > 0，这就是 map 初始化的时候，第一次调用 resize的情况
        //而 oldThr的值等于 threshold，此时的 threshold 是通过 tableSizeFor 方法得到的一个2的n次幂的值(我们以16为例)。
        //因此，需要把 oldThr 的值，也就是 threshold ，赋值给新数组的容量 newCap，以保证数组的容量是2的n次幂。
        //所以我们可以得出结论，当map第一次 put 元素的时候，就会走到这个分支，把数组的容量设置为正确的值（2的n次幂)
        //但是，此时 threshold 的值也是2的n次幂，这不对啊，它应该是数组的容量乘以加载因子才对。别着急，这个会在③处理。
        else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
            //3.到这里，说明 oldCap 和 oldThr 都是小于等于0的。也说明我们的map是通过默认无参构造来创建的，
            //于是，数组的容量和阈值都取默认值就可以了，即 16 和 12。
        else {               // zero initial threshold signifies using defaults
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        //③ 这里就是处理第2种情况，因为只有这种情况 newThr 才为0，
        //因此计算 newThr（用 newCap即16 乘以加载因子 0.75，得到 12） ，并把它赋值给 threshold
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                    (int)ft : Integer.MAX_VALUE);
        }
        //赋予 threshold 正确的值，表示数组下次需要扩容的阈值（此时就把原来的 16 修正为了 12）。
        threshold = newThr;
        @SuppressWarnings({"rawtypes","unchecked"})
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        table = newTab;
        //如果原来的数组不为空，那么我们就需要把原来数组中的元素重新分配到新的数组中
        //如果是第2种情况，由于是第一次调用resize，此时数组肯定是空的，因此也就不需要重新分配元素。
        if (oldTab != null) {
            //遍历旧数组
            for (int j = 0; j < oldCap; ++j) {
                Node<K,V> e;
                //取到当前下标的第一个元素，如果存在，则分三种情况重新分配位置
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    //1.如果当前元素的下一个元素为空，则说明此处只有一个元素
                    //则直接用它的hash()值和新数组的容量取模就可以了，得到新的下标位置。
                    if (e.next == null)
                        newTab[e.hash & (newCap - 1)] = e;
                        //2.如果是红黑树结构，则拆分红黑树，必要时有可能退化为链表
                    else if (e instanceof TreeNode)
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                        //3.到这里说明，这是一个长度大于 1 的普通链表，则需要计算并
                        //判断当前位置的链表是否需要移动到新的位置
                    else { // preserve order
                        // loHead 和 loTail 分别代表链表旧位置的头尾节点
                        Node<K,V> loHead = null, loTail = null;
                        // hiHead 和 hiTail 分别代表链表移动到新位置的头尾节点
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        do {
                            next = e.next;
                            //如果当前元素的hash值和oldCap做与运算为0，则原位置不变
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            }
                            //否则，需要移动到新的位置
                            else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        //原位置不变的一条链表，数组下标不变
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        //移动到新位置的一条链表，数组下标为原下标加上旧数组的容量
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }

    Node<K,V> newNode(int hash,K key,V value,MyHashMap.Node<K,V> next) {
        return new Node<>(hash,key,value,next);
    }

    static final class TreeNode<K,V> extends MyHashMap.Node<K,V>{
        MyHashMap.TreeNode<K,V> parent;
        MyHashMap.TreeNode<K,V> left;
        MyHashMap.TreeNode<K,V> right;
        MyHashMap.TreeNode<K,V> prev;
        boolean red;
        TreeNode(int hash, K key, V value, MyHashMap.Node<K,V> next) {
            super(hash,key,value,next);
        }


        final TreeNode<K,V> putTreeVal(MyHashMap<K,V> map,Node<K,V>[] tab,int hash,K key,V value) {
            return new TreeNode<K,V>(hash,key,value,null);
        }

        final void split(MyHashMap<K, V> kvMyHashMap, Node<K, V>[] newTab, int j, int oldCap) {
        }
    }


}
