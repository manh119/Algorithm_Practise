package org.example.Sort;

import java.util.Arrays;

// TODO : reread code from lecture video
public class MergeSort {
    public static int[] sort(int[] arr, int from, int to) {
       if(from == to) {
            int[] x = new int[1];
            x[0] = arr[from];
            return x;
        }

        int i = from + (to - from) / 2;
        int[] sortedLeft = sort(arr, from, i);
        int[] sortedRight = sort(arr, i + 1, to);
        return merge(sortedLeft,sortedRight);
    }

    // merge two sorted array
    public static int[] merge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int k = 0, i = 0, j = 0;
        while(i < a.length && j < b.length) {
            if(a[i] < b[j])
                result[k++] = a[i++];
            else
                result[k++] = b[j++];
        }
        while(i < a.length)
            result[k++] = a[i++];
        while(j < b.length)
            result[k++] = b[j++];
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {5,5,4,3,2,1};
        System.out.println(Arrays.toString(        MergeSort.sort(arr, 0, arr.length - 1)
        ));
//        int[] a = {1};
//        int[] b = {0, 99, 8, 7};
//        System.out.println(Arrays.toString(MergeSort.merge(a,b)));
    }
}
