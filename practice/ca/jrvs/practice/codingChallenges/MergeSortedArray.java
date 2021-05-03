package ca.jrvs.practice.codingChallenges;

/**
 * https://www.notion.so/jarvisdev/Merge-Sorted-Array-a9956f99ecb04461a68fe92d4a50656c
 */
public class MergeSortedArray {

    /**
     * Merges two integer arrays by using a pointer at each minimum value then comparing and incrementing
     * Runtime will be O(N + M), where N is the length of the first array and M is the length of the second
     * Note that this is still a linear runtime
     * @param arr1 the first sorted array
     * @param arr2 the second sorted array
     * @return merged array that contains all values from the first two arrays sorted
     */
    public static int[] mergeUsingPointers(int[] arr1, int[] arr2){
        int len1 = arr1.length;
        int len2 = arr2.length;
        int[] merged = new int[len1 + len2];

        int pointer1 = 0;
        int pointer2 = 0;
        int index = 0;

        while (pointer1 < len1 || pointer2 < len2){
            if (pointer1 >= len1){
                merged[index] = arr2[pointer2];
                pointer2 += 1;
                index += 1;
            } else if (pointer2 >= len2) {
                merged[index] = arr1[pointer1];
                pointer1 += 1;
                index += 1;
            } else {
                if (arr1[pointer1] < arr2[pointer2]){
                    merged[index] = arr1[pointer1];
                    pointer1 += 1;
                    index += 1;
                } else {
                    merged[index] = arr2[pointer2];
                    pointer2 += 1;
                    index += 1;
                }
            }
        }
        return merged;
    }


}
