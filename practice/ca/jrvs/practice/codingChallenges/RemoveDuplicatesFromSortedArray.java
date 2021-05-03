package ca.jrvs.practice.codingChallenges;

/**
 * https://www.notion.so/jarvisdev/Duplicates-from-Sorted-Array-e1a0004c4d744d35b420a29a825d707f
 */
public class RemoveDuplicatesFromSortedArray {

    /**
     * Oddly worded question in LeetCode - need to modify the array indices and return number of unique integers
     * Essentially it's asking for a way of returning unique values without allocating extra space
     * Can use pointers to track where the duplicates are and where they need to be moved to for in-place modification
     * Solution will take O(N) runtime because of the pointers
     * The LeetCode description asks to return the number of unique values
     * @param nums array of integers
     * @return number of unique values
     */
    public static int twoPointer(int[] nums){
        if (nums.length == 0){
            return 0;
        }
        int uniqueNumbers = 0;
        for (int i = 1; i < nums.length; i++){
            if (nums[i] != nums[uniqueNumbers]){
                uniqueNumbers += 1;
                nums[uniqueNumbers] = nums[i];
            }
        }
        return uniqueNumbers + 1;
    }

}
