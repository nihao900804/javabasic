package com.imooc.test;

import com.imooc.javabasic.collect.Adults;
import com.imooc.javabasic.collect.Child;
import com.imooc.javabasic.collect.Demo;
import com.imooc.javabasic.collect.User;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.apache.commons.lang3.StringUtils;
import org.jdom2.Attribute;
import org.jdom2.Comment;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.w3c.dom.ls.LSOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

/**
 * @author Ace
 * @create 2020-05-07
 */
public class Test {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void test() {
        HashMap<Integer, Object> map = new HashMap<Integer, Object>(2);
        map.put(0, 1);
        map.put(null, 2);
        for (Integer key : map.keySet()) {
            System.out.println("key= " + key + " and value= " + map.get(key));
        }
        System.out.println(map.size());
        for (int i = 0; i < 10; i++) {
            map.put(i, i);
        }
        for (Integer key : map.keySet()) {
            System.out.println("key= " + key + " and value= " + map.get(key));
        }
    }

    public static void test1() {
        int i = 0;
        int a = i++;
        System.out.println("a " + a);
        System.out.println("i " + i);
        int b = ++i;
        System.out.println("b " + b);
        System.out.println("i " + i);
        if (i++ > 2) {
            System.out.println(true);
        }
        System.out.println("i " + i);
    }

    public static void test2() {
        User user1 = new User();
        User user2;
        user1.setName("呵呵");
        user2 = user1;
        System.out.println(user1 == user2);
        user1.setName("哈哈");
        System.out.println("user1:" + user1);
        System.out.println("user2:" + user2);
        System.out.println(user1 == user2);
    }

    public static void test3(int cap) {
        int n = cap - 1;
        System.out.println("n:" + n);
        System.out.println("n>>>1:" + (n>>>1));
        //00000101
        //00001011
        //11110001

        System.out.println("n:" + n);
        n |= n >>> 1;
        System.out.println("n|(n>>>1):" + n);
        n |= n >>> 2;
        System.out.println("n>>>2:" + n);
        n |= n >>> 4;
        System.out.println("n>>>4:" + n);
        n |= n >>> 8;
        System.out.println("n>>>8:" + n);
        n |= n >>> 16;
        System.out.println("n>>>16:" + n);
        System.out.println((n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1);
    }

    static void foo(StringBuilder sb) {
        sb.append("4");
    }

    static void test4() throws IOException {
        //创建文档
        Document document = new Document();
        //创建根元素
        Element people = new Element("people");
        //把根元素加入到document中
        document.addContent(people);

        //创建注释
        Comment rootComment = new Comment("将数据从程序输出到XML中！");
        people.addContent(rootComment);

        //创建父元素
        Element person1 = new Element("person");
        //把元素加入到根元素中
        people.addContent(person1);
        //设置person1元素属性
        person1.setAttribute("id", "001");

        Attribute person1_gender = new Attribute("gender", "male");
        person1.setAttribute(person1_gender);

        Element person1_name = new Element("name");
        person1_name.setText("刘德华");
        person1.addContent(person1_name);

        Element person1_address = new Element("address");
        person1_address.setText("香港");
        person1.addContent(person1_address);


        Element person2 = new Element("person");
        people.addContent(person2);

        person2.setAttribute("id", "002").setAttribute("gender", "male");//添加属性，可以一次添加多个属性

        Element person2_name = new Element("name");
        person2_name.setText("林志颖");
        person2.addContent(person2_name);

        Element person2_address = new Element("address");
        person2_address.setText("台湾");
        person2.addContent(person2_address);


        //设置xml输出格式
        Format format = Format.getPrettyFormat();
        format.setEncoding("utf-8");//设置编码
        format.setIndent("    ");//设置缩进


        //得到xml输出流
        XMLOutputter out = new XMLOutputter(format);
        //把数据输出到xml中
        out.output(document, new FileOutputStream("jdom.xml"));//或者FileWriter
    }

    public static void test5() {
        List<String> al = Arrays.asList("a", "b", "c", "d");
        al.forEach(Test::printValue);
    }

    public static void printValue(String str) {
        System.out.println("print value : " + str);
    }

    public static void main(String[] args) throws Exception {

        long timeStart = System.nanoTime();
        //timeStart = System.currentTimeMillis();
        System.out.println(timeStart);

        long timeEnd = System.nanoTime();
        //timeEnd = System.currentTimeMillis();
        System.out.println(timeEnd);
        System.out.println(timeEnd - timeStart);



    }


    /**
     * 处理查询条件  主要处理  第一个and
     * @param condition
     * @return
     */
    public static String dealingWithCondition(String condition) {
        //空直接返回
        if (StringUtils.isEmpty(condition)) {
            return "";
        }
        //去掉头尾空白符
        condition = condition.trim();
        if (StringUtils.isEmpty(condition)) {
            return "";
        }
        if (condition.length() < 3) {
            return condition;
        }
        //判断 传过来的条件 前三位是否 and 关键字
        if (condition.substring(0,3).equalsIgnoreCase("and")) {
            condition = condition.substring(3);
            condition = condition.trim();
        }
        return condition;
    }


    public static String test99() {
        try {
            int i = 1/0;
            String aaa = "1111";
            return aaa;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

        }
    }


    public static void treeMapSort() {
        TreeMap map = new TreeMap<User,Integer>((u1,u2)-> u2.getAge() - u1.getAge());
        User user_1 = new User("Sanme",10);
        User user_2 = new User("Jack", 1);
        User user_3 = new User("Rose", 3);
        map.put(user_1,user_1.getName());
        map.put(user_2,user_2.getName());
        map.put(user_3,user_3.getName());
        map.forEach((user,i) -> {
            System.out.println(user);
        });
    }

    public static void sort() {
        List<User> list = new ArrayList<>();
        User user_1 = new User(10);
        User user_2 = new User(1);
        User user_3 = new User(3);
        list.add(user_1);
        list.add(user_2);
        list.add(user_3);
        list.forEach(System.out::println);
        list.sort(Comparator.comparing(User::getAge));
        list.forEach(System.out::println);
    }

    public static void completableFutureTest() {
        CompletableFuture.supplyAsync(()-> "hello").thenApply(s-> s + " world").thenApply(String::toUpperCase).thenAccept(System.out::println);
    }

    public static boolean isValid(String s) {
        return true;
    }


    public static int[] searchRange(int[] nums, int target) {
        int find = searchRangeHelper(nums, target);
        //如果没找到，返回{-1, -1}
        if (find == -1) {
            return new int[]{-1, -1};
        }
        int left = find - 1;
        int right = find + 1;
        //查看前面是否还有target
        while (left >= 0 && nums[left] == target) {
            left--;
        }
        //查看后面是否还有target
        while (right < nums.length && nums[right] == target) {
            right++;
        }
        return new int[]{left + 1, right - 1};
    }


    //二分法查找  nums=[1,2,3,4,5,6,7,8,9]  target=7
    public static int searchRangeHelper(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midVal = nums[mid];
            if (midVal < target) {
                low = mid + 1;
            } else if (midVal > target) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void jiemiStr() {
        String str = "H4sIAAAAAAAAAJ1YW3MTRxb+Kyo9hYex564RJTuFMXiXkN3E69QCL11zt8aSsTRDrJknkcWYi/Fl\n" +
                "wTZgEyBlg5dgAsuCjbn9F1CP5Kf8hT3dPRpLQCBAUa6e6dPd53znO1+fUeHrWrmU+dGu+sWT431Z\n" +
                "oYfPZuxx86RVHHf7sj+MHOa07Nf9haGiXxm2K6dsP+gvHDADMO4XCr3JqDBwyi+O274/Ek7Y/YJS\n" +
                "6O16URjUA72/8N3wX9HIgYGjh9DggZEDmUA3Svbf9LLdlx1BQ4PHvs32F4YPHfz78GDGipChI2MU\n" +
                "/MlmfKPal3Ut089m3BBNlixU9vqycg+f/MtmDH0UTYIx2HrIs5DvkXGtjKIAdhBgjyCKAlSL4Ckn\n" +
                "ZTOOj7yIPrYevIqXXjavbODVh9lMZNRQqUbWlkZLyKUj10LuqaLVl1UdR3d4VeNsSXI42ZYELp/X\n" +
                "JE7JOZqh844jWhoSs5myXiO7uxZZXQmRU0VWmYzDAPl0YLnUQ5EXBY4XODGX4fn99D8B3yyhSkBD\n" +
                "5A8B9pla9F68sKvvU08Fal+rgHFPe7ZcHCcOlK02IG4CyITJtu3hNSWvasTWrKJx2EaB4Zjj+agC\n" +
                "D1IPmfEgLYrK720LcJpkNYsQACJbRgB2hCphglRE9ld65LyWJ2up8yQH7Rx5ZZM6EtF0EQB4XsBz\n" +
                "WxmGvcdcdpA/SteMAnpwzACvwnp49ZeR5tyt1vS95pmn8fZveHP5K7LFvnSPqtfOGgElguCIP0In\n" +
                "cgEKLBRWiA2hgJvYGyaqhahioWqlIzF8d2LCyEy4oDt5yZBkkcuJZh64YKmcIeYUzrQs3sypji4a\n" +
                "OmwaUYg8123jnngxOeaUQhRS1JJsMboQZEkQgQ619+b8GptOsB4NaMp5UhEQXaW9mnCVLmAgG5Y3\n" +
                "xk5OKiENR+kKZxLRgBglR/UfbSg5BrtZS3xzx5yK5dXe8R6yZ012eEsQ1gi2H0WvZoa1MEhy825a\n" +
                "qCeEEnjlJb7wM55beFu/IvK89ra+KJDkEqbaemAjC/50nSEo+wV5v6CRM4CNhCZDR2FC5DnghQIo\n" +
                "jCGXss5Ly07+gH8ESRoM36PJhLgVWrayzOcoVcpWQpWTIECpKABGnuEy5GiJ1GCgUk+YgSDItmPr\n" +
                "OifJss7JjmNxhqwS5zUjx8u2qalmGtwp3wapk6RE1VxktUuXEoZkFQp+stQegaYlOuZFPFXKdxMu\n" +
                "CPtFZb8i9UhiPpUmpgyk4IKQyZ05mpxj2SU7RZnqrePDhqCZpGxIJas8dSF8nxcJgh3k9RLpUoW8\n" +
                "0CNKPKOu6TNZ9/bCSyWrTeS0KlJmu3s6Bl4xndnTsTEnAKWxE3doJojUxMu3dm8/a648wPOXmncf\n" +
                "CqIk00giyDNISfxka/fXq/GtOVCT5vO5+MksntnBzx7Hz17hlZXW1m+N7VU8s9x4dbd58zGeWcJT\n" +
                "m/Cytf6avSSSB7wN0nNZcXhue3MIslpte9++loD+rpGKE9N4cMkz+rKWp3YkqR160UdQPLQyIciK\n" +
                "1XXYRyouvYhSUOFSd8I0uYmMJ7O9/YXe7gu6v/CPHwb+8MI+OHwEnTh8fHDv0k7kzkHvXR1m1UvK\n" +
                "QbF1R+V1h1MkJQflIJucxus8JyuSZedMPadY6hfcuXBohTULMNorAZET8p2QkBi7Y/p4jCeODA2h\n" +
                "oeNk3UfNDh899u2xT1oNDh478f0eXJHVxeLP1owOhZJB7XjlS3CzLLINWwSJ0BQnL3KS7iicbDky\n" +
                "l9c0yJIsKlJehB2hej4XwoMDn8Zv4LtP2xw8+mmbkcHjI5/Owz+PHh88cuxPpHXom8Pf/znLg8ND\n" +
                "x9E3Jzr6V3K/iXy7Tk0LTdDiMKvQDJJLNqeo0DuomqZItPlgt7DpuT764CVGFoagEIqQZ5UfICck\n" +
                "TEnPCCK4f2usP5goQ+GTDaloI+g/SD8Ec181tuv7QJWK0WgRGXYxKVg2tTOzjx005pCb+PkONMe7\n" +
                "l5+2bs+Q12NRQhOHh1MNzeI0U4PCFXWB0x3B5nRbNCzVUC2Jt0GQXeiy0GToIaNESc4n0Ud/rLzx\n" +
                "ncX41gKIbzqF1+7i2Tv40ul4/YXACzx+sPYlLA+R53XG71QSwKHj9X3ac4Jr5KaT2DDyyL1IHmog\n" +
                "J/SKjEosFJGGwhCsFZPeMRhrbxJ5o7QmFUmUBRlETlVzak5T1OQOjaoo6PKFwA8KTc4mTZnrkpaa\n" +
                "sYH15gw3E3BTZFFSJNgaCAHtQBtc1q+TGzPPoyiie8m0zXT9js1YgELiJmlTwD1ZVYh7MjgJD+Aw\n" +
                "wSSERWHgtdXcJ0TjU6LBCROkE6VBCISKodXRCTBPEjcE9sb/MKmIUY02442Xc/jBFMw2Nxfw+jL+\n" +
                "+WLrzun40U+0aevgkUpDhXzCtUt6UMYkvP1TY2cnfrgQP17cvfYL+b4DBEAhLeSAEZ7a2P3XRnxu\n" +
                "Cf7ic2dJWzIJXxgAR2ce2rX3eRJMviYIUr+/uB6v3oePSHxjGa/C1+RNMp6GaP+D58/h+Znm5qvW\n" +
                "g18azy7Gy+utJ1uN7bXG9r149Rw+OxXPnH9TX8XTdVjypn4jvjWNp8827z5v7Kz9/mIGZpuzD5sX\n" +
                "nsIjGMAbvPVfPLWF16bhyxVvrQOgMG5szwIGeOFq6/UKXt1pXj+ze/oyNCtv66eZWXy+vrtah/eN\n" +
                "Vyvxw//hf8/AoHnlSXPzPL6wAQb4RR36bjDDr6eYJb65A0HFN243nj/NsCyS8iXNz879xrN5fGm2\n" +
                "sb3UXJzBG4/gMV49v3ttHk/dx3PbFAkIaIM0S5cW4417IC+wk5KHZh7Ku3ntOattKGcz+VbpyLNM\n" +
                "82xN0lYCCl/rkXKKwFhMqAtYxFcvN18uNH+91prdwnNL3QWADMKAVHU6iMK+HltPzsQ78/hRvV3J\n" +
                "1slxt13C7wi0TFWgxlTAT3VYo8x2QxDXz2QNVE+tTHtT1oNN0C6+s4Jo19icvQjMxJeup6e8fy1Q\n" +
                "9lEWA1oegmqtVhJJSsvUSQWuS0NU5ggsJb9ZJEYUi3F9nH6kk7o2SsyrD977vexnnd7O34b+D6+7\n" +
                "4fpPEgAA";
        String newStr = gunzip(str);
        System.out.println(newStr);

    }

    public static String gunzip(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        byte[] compressed = null;
        String decompressed = null;
        try {
            compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
            in = new ByteArrayInputStream(compressed);
            ginzip = new GZIPInputStream(in);

            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ginzip != null) {
                try {
                    ginzip.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return decompressed;
    }

}
