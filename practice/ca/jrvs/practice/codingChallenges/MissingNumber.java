package ca.jrvs.practice.codingChallenges;

import java.util.HashSet;
import java.util.stream.IntStream;

/**
 * https://www.notion.so/jarvisdev/Missing-Number-2173d39c10644d969907a4e258b93556
 */
public class MissingNumber {
    public static void main(String[] args) {

        int[] nums = {5, 2, 1, 0, 4};
        System.out.println(gauss(nums));
    }

    public static int sum(int[] nums){
        return -1;
    }

    /**
     * Place all integers into a set and then check if the final set is missing a value
     * Runtime will be O(N) as we need to add each value to the set, requiring us to go through the array
     * @param nums array of integers
     * @return the missing integer in the array
     */
    public static int set(int[] nums){
        HashSet<Integer> set = new HashSet();

        for (int num : nums){
            set.add(num);
        }
        for (int i = 0; i < set.size() + 1; i++){
            if (!set.contains(i)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Clever solution that uses the Gauss formula that calculates the sum of first n numbers
     * With this, we can sum our values then use the formula to find the difference so we know what number is missing
     * Runtime will still be O(N) as we have to sum the numbers, however we don't have to allocate space for a set
     * @param nums array of integers
     * @return the missing integer in the array
     */
    public static int gauss(int[] nums){
        int gaussSum = nums.length * (nums.length + 1) / 2;
        int sum = IntStream.of(nums).sum();
        return gaussSum - sum;
    }

}
