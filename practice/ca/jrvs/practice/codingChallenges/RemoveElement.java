package ca.jrvs.practice.codingChallenges;

/**
 * https://www.notion.so/jarvisdev/Remove-Element-6af3f85eed02460eabcbed71edff845e
 */
public class RemoveElement {

    /**
     * Removes elements with the given value from the array in-place
     * The order of the returned values in the array does not matter
     * Runtime will be O(N) where N is the length of the array as we must traverse through it
     * @param nums array of integers
     * @param val the integer to remove from the array
     * @return the length of the new array
     */
    public static int twoPointerApproach(int[] nums, int val){
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++){
            if (nums[fast] != val){
                nums[slow] = nums[fast];
                slow += 1;
            }
        }
        return slow;
    }
}
