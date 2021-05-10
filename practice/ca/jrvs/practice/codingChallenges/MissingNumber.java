package ca.jrvs.practice.codingChallenges;

import java.util.HashSet;
import java.util.stream.IntStream;

/**
 * https://www.notion.so/jarvisdev/Missing-Number-2173d39c10644d969907a4e258b93556
 */
public class MissingNumber {

    /**
     * Sum all values in the array and then subtract each value one by one
     * If we reach zero, there is no missing number, otherwise the missing number will be the remainder
     * Runtime will be O(N) where N is the length of the array
     * @param nums array of integers
     * @return the missing integer in the array
     */
    public static int sum(int[] nums){
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int num : nums){
            sum += num;
            max = Math.max(max, num);
        }
        for (int i = max; i > 0; i--){
            sum -= i;
        }
        return sum * -1;
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
        return 0;
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
