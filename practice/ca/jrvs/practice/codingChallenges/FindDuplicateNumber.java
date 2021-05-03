package ca.jrvs.practice.codingChallenges;

import java.util.Arrays;
import java.util.HashSet;

/**
 * https://www.notion.so/jarvisdev/Find-the-Duplicate-Number-a8b421f759f543aea560698f365ce4bd
 */
public class FindDuplicateNumber {

    /**
     * Finds duplicate by sorting the array and checking adjacent values for any matches
     * Runtime will be O(NlogN) where N is the length of the array because of the sort
     * @param nums array of integers
     * @return the duplicate value
     */
    public static int sort(int[] nums){
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++){
            if (nums[i] == nums[i + 1]){
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * Stores all values in a set and if we visit an index with an already seen value, we have our answer
     * Runtime will be O(N) where N is the length of the array
     * @param nums array of integers
     * @return the duplicate value
     */
    public static int set(int[] nums){
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++){
            if (!set.contains(nums[i])){
                set.add(nums[i]);
            } else {
                return nums[i];
            }
        }
        return -1;
    }
}
