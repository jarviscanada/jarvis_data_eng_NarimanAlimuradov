package ca.jrvs.practice.codingChallenge;

public class Fibonacci {
    /**
     * Standard but time-inefficient approach to solving Fibonacci
     * Recursively calculate the nth Fibonacci number by going down until we reach the base case
     * Time complexity will be O(2^num) because we branch off for each recursion, repeating calculations
     * Space complexity will be O(num) as we have use space on the stack for storing these recursive calls
     * @param num
     * @return the nth Fibonacci number, where n = num
     */
    public static int fibRecursive(int num){
        if (num <= 1) {
            return num;
        }
        return fibRecursive(num - 1) + fibRecursive(num - 2);
    }

    /**
     * Created a dp array to store all Fibonacci numbers below and including the target number
     * Compute each dp value by adding the two previous dp values below it
     * Runtime will be O(num), as we will have to loop through each number below num until we reach it
     * Space complexity will also be O(num), because we have to create the dp array to store the values
     * @param num integer
     * @return the nth Fibonacci number, where n = num
     */
    public static int fibDynamic(int num){
        int[] dp = new int[num + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= num; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[num];
    }
}
