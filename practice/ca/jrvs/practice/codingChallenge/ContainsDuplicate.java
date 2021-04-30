package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashSet;

/**
 * https://www.notion.so/jarvisdev/Contains-Duplicate-c467d83129544324975791dc2605c08c
 */
public class ContainsDuplicate {

    /**
     * Checks for duplicates by sorting the array values and looking for sequential duplicates
     * O(NlogN) runtime because of the sorting
     * @param nums array of integers
     * @return whether or not duplicates were found
     */
    public static boolean sorting(int[] nums){
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++){
            if (nums[i] == nums[i+1]){
                return true;
            }
        }
        return false;
    }

    /**
     * Uses a set to track unique integers and then comparing the length of the set to the length of the array
     * Unequal lengths implies there is a duplicate in the array
     * Runtime will be O(N) where N is the length of the array as we simply go through each index once
     * @param nums array of integers
     * @return whether or not duplicates were found
     */
    public static boolean set(int[] nums){
        HashSet mySet = new HashSet();
        for (int num : nums){
            mySet.add(num);
        }
        if (mySet.size() == nums.length){
            return true;
        }
        return false;
    }
}
