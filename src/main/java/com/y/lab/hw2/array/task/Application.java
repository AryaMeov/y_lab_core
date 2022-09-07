package com.y.lab.hw2.array.task;

import java.util.*;

/*
Task2

    [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
 */
public class Application {

    public static void main(String[] args) {
        test(new int[]{3, 4, 2, 7}, 10);
        var scanner = new Scanner(System.in);
        System.out.print("Введите размер массива: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println("Введите элементы массива: ");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.print("Введите сумму: ");
        int sum = scanner.nextInt();

        var result = find(arr, sum);
        result.forEach(r -> {
            System.out.println("[" + r.getKey() + ", " + r.getValue() + "]");
        });
    }

    private static List<Map.Entry<Integer, Integer>> find(int[] arr, int sum) {
        List<Map.Entry<Integer, Integer>> result = new ArrayList<>();
        for (int i = 0; i < arr.length; i++)
            for (int j = i + 1; j < arr.length; j++)
                if (arr[i] + arr[j] == sum)
                    result.add(Map.entry(arr[i], arr[j]));
        return result;
    }


    private static void test(int[] arr, int sum) {
        var expected = Map.entry(3, 7);
        var result = find(arr, sum);
        assert 1 == result.size();
        assert expected.getKey().equals(result.get(0).getKey());
        assert expected.getValue().equals(result.get(0).getValue());
    }
}
