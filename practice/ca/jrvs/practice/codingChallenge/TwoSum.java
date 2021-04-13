package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    /**
     * Brute force attempt where we double loop and find all pairs that sum to the target
     * Runtime will be O(n^2) where n is the length of the array due to the nested loops
     * @param arr array of integers
     * @param target target integer to sum to
     * @return the indices of the two values that sum to target, otherwise null
     */
    public static int[] twoSumBrute(int[] arr, int target){
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr.length; j++){
                if (arr[i] + arr[j] == target){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * More efficient solution that uses a map to store values we already visited
     * This way, we can check if the difference of the target and value were at has already been seen
     * Runtime will be O(n) where n is the length of the array, as we only do one pass through
     * @param arr array of integers
     * @param target target value to sum to
     * @return the indices of the two values that sum to target, otherwise null
     */
    public static int[] twoSumEfficient(int[] arr, int target){
        Map<Integer, Integer> dict = new HashMap<>();
        for (int i = 0; i < arr.length; i++){
            if (dict.containsKey(target - arr[i])){
                return new int[]{dict.get(target - arr[i]), i};
            } else {
                dict.put(arr[i], i);
            }
        }
        return null;
    }
}
