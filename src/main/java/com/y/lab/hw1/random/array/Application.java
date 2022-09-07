package com.y.lab.hw1.random.array;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * Заполните двумерный массив случайным числами и выведите максимальное, минимальное и среднее значение.
 * Array.sort() использовать нельзя, Random можно но не желательно
 */
public class Application {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        System.out.print("Введите размер массива: ");
        int firstSize = scanner.nextInt();
        System.out.print("Введите размер внутреннего массива: ");
        int secondSize = scanner.nextInt();
        var arr = getRandomArray(firstSize, secondSize);

        var stats = Arrays.stream(arr)
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .summaryStatistics();

        int min = stats.getMin();
        int max = stats.getMax();
        double avg = stats.getAverage();
        System.out.println("Максимальное: " + max + ", минимальное: " + min + ", среднее значение: " + avg);
    }

    private static Integer[][] getRandomArray(int firstSize, int secondSize) {
        var arr = new Integer[firstSize][secondSize];
        var random = new Random(200);

        for (int i = 0; i < firstSize; i++) {
            for (int j = 0; j < secondSize; j++) {
                arr[i][j] = random.nextInt();
            }
        }
        return arr;
    }
}

class Random {

    private final int max;
    private int last;

    public Random(int max) {
        this.max = max;
        last = (int) (System.currentTimeMillis() % max);
    }

    public int nextInt() {
        last = (last * 32719 + 3) % 32749;
        return last % max;
    }
}