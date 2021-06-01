package ca.jrvs.practice.codingChallenges;

/**
 * https://www.notion.so/jarvisdev/Count-Primes-07c59c0159d14b349c77f96ec4f83996
 */
public class CountPrimes {

    /**
     * Find the number of primes by tracking the multiples of every integer below our target
     * Store the prime status of each value in an array and then sum up the result
     * No point to loop above the square root of N as it will have either already been assigned as a multiple or not
     * Runtime will be O(sqrt(N)*N) because of initial nested loops
     * @param n integer we are counting until
     * @return number of primes upto that integer
     */
    public static int numberOfPrimes(int n){
        boolean[] check = new boolean[n + 1];
        int count = 0;
        for (int i = 2; i <= Math.floor(Math.sqrt(n)); i++){
            for (int j = 2*i; j < n; j+=i){
                check[j] = true;
            }
        }
        for (int i = 2; i < n; i++){
            if (!check[i]){
                count += 1;
            }
        }
        return count;
    }
}
