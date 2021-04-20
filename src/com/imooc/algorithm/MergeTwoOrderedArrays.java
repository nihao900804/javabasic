package com.imooc.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ace
 * @create 2020-06-03
 */
public class MergeTwoOrderedArrays {

    public static List mergerList(List list1, List list2) {
        List mergeList = new ArrayList();
        if (list1.size() == 0 && list2.size() == 0) {
            return mergeList;
        }
        if (list1.size() == 0 && list2.size() > 0) {
            mergeList = list2;
        }
        if (list2.size() == 0 && list1.size() > 0) {
            mergeList = list1;
        }
        int i = 0, j = 0;
        while (i < list1.size() && j < list2.size()) {
            if ((int) list1.get(i) < (int) list2.get(j)) {
                mergeList.add(list1.get(i));
                i++;
            } else if ((int) list1.get(i) == (int) list2.get(j)) {
                mergeList.add(list1.get(i));
                i++;
                j++;
            } else {
                mergeList.add(list2.get(j));
                j++;
            }
        }
        while (i < list1.size()) {
            mergeList.add(list1.get(i));
            i++;
        }
        while (j < list2.size()) {
            mergeList.add(list2.get(j));
            j++;
        }
        return mergeList;
    }

    public static void main(String[] args) {
        List list1 = Arrays.asList(1, 3, 4, 9);
        List list2 = Arrays.asList(3, 4, 5, 6, 8);
        List list = mergerList(list1, list2);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("i:" + list.get(i));
        }

    }

}
