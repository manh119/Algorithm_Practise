package org.example.Search;

public class BinarySearch {

    // find x in sorted arr, return index if find, -1 if not
    public static int search(int[] arr, int lo, int hi, int x){
        if(lo > hi)
            return -1;
        int i = lo + (hi - lo)/2;
        if(arr[i] == x)
            return i;
        else if (arr[i] < x)
            return search(arr, i+1, hi, x);
        else
            return search(arr, lo, i-1, x);
    }

    public static void main(String[] args) {
        int[] arr = {0,1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(BinarySearch.search(arr, 0, arr.length - 1, 10));
    }
}
