package org.example.Sort;

import java.util.Arrays;

// TODO : rewatch from video lecture
public class QickSort {
    public static void sort(int[] arr, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(arr, lo, hi);
        sort(arr, lo, j - 1);
        sort(arr, j + 1, hi);
    }

    // partition and return index of pivot
    public static int partition(int[] arr, int lo, int hi) {
        int pivot = arr[lo];
        int i = lo;
        int j = hi+1;
        while(true) {
            while(arr[++i] < pivot && i < hi) {
            }
            while(arr[--j] > pivot && j > lo) {
            }
            if(i < j)
                swap(arr,i,j);
            else
                break;
        }
        swap(arr,lo,j);
        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int x = arr[i];
        arr[i] = arr[j];
        arr[j] = x;
    }

    public static void main(String[] args) {
        int[] a = {5,5,4,3,2,1};
        QickSort.sort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}
