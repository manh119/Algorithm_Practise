package org.example.Sort;

import java.util.Arrays;

public class InsertionSort {
    public static void sort(int[] arr) {
        // insert arr[i+1] in sorted arr[0] -> a[i]
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i; j > 0; j--) {
                int x = arr[i+1];
                if(x < arr[j])
                    arr[j+1] = arr[j];
                else
                    arr[j] = x;
            }
        }
    }
    public static void main(String[] args) {
        int[] arr = {3, 10, 2, 9, 11, 13, 100, 0};
        BubbleSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
