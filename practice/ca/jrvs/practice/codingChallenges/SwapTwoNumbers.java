package ca.jrvs.practice.codingChallenges;

/**
 * https://www.notion.so/jarvisdev/Swap-two-numbers-906e2a70560b4abd84fc10d0a5d00aa3
 */
public class SwapTwoNumbers {

    /**
     * Swaps two values by using the bitwise XOR operator
     * Runtime is O(1) as the same operations will be done regardless of the input values
     * @param nums array of two integers to be swapped
     * @return same array with value positions swapped
     */
    public static int[] bitSwap(int[] nums){
        nums[0] ^= nums[1];
        nums[1] ^= nums[0];
        nums[0] ^= nums[1];
        return nums;
    }

    /**
     * Swaps two numbers by adding one into the other to temporarily store it
     * Runtime is O(1) as the same operations will be done regardless of the input values
     * @param nums array of two integers to be swapped
     * @return same array with value positions swapped
     */
    public static int[] arithmeticSwap(int[] nums){
        nums[0] += nums[1];
        nums[1] = nums[0] - nums[1];
        nums[0] -= nums[1];
        return nums;
    }
}
