package ca.jrvs.practice.algorithms.sort;

public class QuickSort {

    /**
     * Quicksort essentially works by picking a value (pivot) and finding it's final position in the array
     * Once found, recurse through all values less and greater than the pivot
     * Repeat until every value is in its final resting spot
     *
     * Pointer 'i' will track the latest value less than the pivot
     * Pointer 'j' will recurse and look for values less than the pivot
     * If we find a value less than the pivot, we swap the pivot's position with the value at 'i'
     *
     * The worst case O(n^2) runtime is achieved when we pack a bad pivot (eg. smallest or largest value)
     * This is because we will be doing n -> n-1 -> n-2 -> etc. operations each time
     * As such, a good pivot to pick is the median of the array to be sorted
     *
     * @param arr the array to be sorted
     * @param low the lowest index of the array
     * @param high the highest index of the array
     *
     * Running quickSort on an array will make that same array sorted rather than returning a new array
     */
    public static void quickSort(int[] arr, int low, int high){
        if (low < high) {
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high){
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++){
            if (arr[j] <= pivot){
                i += 1;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

}
