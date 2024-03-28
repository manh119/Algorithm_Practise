package org.example.Sort;

import java.util.Arrays;

public class BubbleSort {
    public static void sort(int[] arr) {
        for (int i = arr.length; i > 0; i--) {
            for (int j = 0; j < i - 1; j++) {
                if(arr[j] > arr[j+1])
                    swap(arr,j, j+1);
            }

        }
    }

    private static void swap(int[] arr, int i, int j) {
        int x = arr[i];
        arr[i] = arr[j];
        arr[j] = x;
    }
    public static void main(String[] args) {
        int[] arr = {3, 6, 13, 1, 0, 7, 6, 99, 2};
        BubbleSort b = new BubbleSort();
        b.sort(arr);
        System.out.println(        Arrays.toString(arr))   ;

    }

}
