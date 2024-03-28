package org.example.Sort;

import java.util.Arrays;

public class SelectionSort {
    public static void sort(int[] arr) {
        // insert arr[iMax] into sorted arr [i-1]
        // = insert arr[iMax] into arr[i-1]
        for (int i = arr.length; i > 1; i--) {
            int max = (int) Double.MIN_VALUE;
            int iMax = 0;
            for (int j = 0; j < i; j++) {
                if(arr[j] > max) {
                    max = arr[j];
                    iMax = j;
                }
            }
            arr[iMax] = arr[i-1];
            arr[i-1] = max;

        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 0, 100, 9, 7, 8, 10};
        SelectionSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
