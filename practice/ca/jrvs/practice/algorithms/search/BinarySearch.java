package ca.jrvs.practice.algorithms.search;

public class BinarySearch {

    /**
     * Recursive binary search
     */
    public static int recursive(int[] arr, int target, int low, int high) {

        if (low <= high){
            int mid = (low + high) / 2;

            if (target == arr[mid]){
                return mid;
            } else if (target > arr[mid]){
                return recursive(arr, target, mid + 1, high);
            } else {
                return recursive(arr, target, low, mid - 1);
            }
        }
        return -1;
    }

    /**
     * Iterative binary search
     */
    public static int iterative(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high){
            int mid = (low + high) / 2;
            if (target == arr[mid]){
                return mid;
            } else if (target > arr[mid]){
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
