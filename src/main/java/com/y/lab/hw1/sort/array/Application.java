package com.y.lab.hw1.sort.array;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * Отсортируйте массив [5,6,3,2,5,1,4,9]
 */
public class Application {

    public static void main(String[] args) {
        var arr = new int[]{5, 6, 3, 2, 5, 1, 4, 9};
        sort(arr);
        test(arr);
        System.out.println("Отсортированный массив: " + Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        var freq = new TreeMap<Integer, Integer>();
        for (int i : arr) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }
        var i = 0;
        for (var entry : freq.entrySet()) {
            var value = entry.getValue();
            while (value-- > 0) {
                arr[i++] = entry.getKey();
            }
        }
    }

    private static void test(int[] actual) {
        var expected = new int[]{1, 2, 3, 4, 5, 5, 6, 9};
        for(var i = 0; i < actual.length; i++) {
            assert expected[i] == actual[i];
        }
    }
}
