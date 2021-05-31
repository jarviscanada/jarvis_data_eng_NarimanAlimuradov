package ca.jrvs.practice.algorithms.sort;

public class MergeSort {

    /**
     * Mergesort is an algorithm that combines recursion with the idea that you can merge two sorted lists quickly
     * If you have two sorted lists, you can easily merge them linearly by using two pointers
     *
     * As such, we take our array and split it in half until we reach arrays of length 1 (this is our base case)
     * When we reach this base case, we merge these 'sorted arrays' until we reach the top again
     *
     * @param arr
     */
    public static void mergeSort(int[] arr){
        if (arr.length <= 1){
            return;
        }

        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        for (int i = 0; i < mid; i++){
            left[i] = arr[i];
        }

        for (int i = mid; i < arr.length; i++){
            right[i - mid] = arr[i];
        }

        mergeSort(left);
        mergeSort(right);

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < mid && j < (arr.length - mid)){
            if (left[i] <= right[j]){
                arr[k] = left[i];
                k += 1;
                i += 1;
            } else {
                arr[k] = right[j];
                k += 1;
                j += 1;
            }
        }

        while (i < mid) {
            arr[k] = left[i];
            k += 1;
            i += 1;
        }

        while (j < arr.length - mid){
            arr[k] = right[j];
            k += 1;
            j += 1;
        }
    }
}
