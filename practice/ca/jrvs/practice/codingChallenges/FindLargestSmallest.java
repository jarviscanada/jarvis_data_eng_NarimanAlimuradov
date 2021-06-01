package ca.jrvs.practice.codingChallenges;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

/**
 * https://www.notion.so/jarvisdev/Find-Largest-Smallest-e0e0963ead1e4e45a7dececb1701e4fc
 */
public class FindLargestSmallest {

    /**
     * Returns the largest number in an array by looping through and comparing against the current maximum
     * Runtime will be O(N) because we iterate through each number in the array
     * Can return the smallest by simply comparing for the minimum value instead
     * @param nums array of integers
     * @return biggest value in the array
     */
    public static int loop(int[] nums){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] > max){
                max = nums[i];
            }
        }
        return max;
    }

    /**
     * Uses stream operations to return the maximum (or minimum value)
     * Must provide a default value so that the stream knows what to return if there is no data
     * Runtime will also be O(N), same as above
     * @param nums array of integers
     * @return biggest value in the array
     */
    public static int stream(int[] nums){
        return IntStream.of(nums).max().orElse(Integer.MIN_VALUE);
    }

    /**
     * Alternative that uses the collections class which allows us to compare objects, rather than primitives
     * Another O(N) runtime solution
     * @param nums array of integers
     * @return biggest value in the array
     */
    public static int collection(int[] nums){
        Integer[] wrapper = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++){
            wrapper[i] = nums[i];
        }
        return Collections.max(Arrays.asList(wrapper));
    }
}
