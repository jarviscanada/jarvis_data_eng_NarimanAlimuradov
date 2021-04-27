package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/jarvisdev/Sample-Check-if-a-number-is-even-or-odd-be5b83685526446b86f865a498c0a085
 */
public class EvenOdd {
    /**
     * Uses modulo to find remainder of dividing by 2 (if it has a remainder, must be odd)
     * Runtime will be O(1) as the time will be the same regardless of the input
     * @param num the number to check
     * @return 'even' if num is an even number, 'odd' if num is an odd number
     */
    public static String evenOddModulo(int num){
        if (num % 2 == 0){
            return "even";
        }
        return "odd";
    }

    /**
     * Converts the absolute value of the number to binary, then we check the right-most bit
     * If the right-most bit is a 1, it must be odd, otherwise it's even
     * Runtime will also be O(1) for the same reason as above
     * @param num the number to check
     * @return 'even' if num is an even number, 'odd' if num is an odd number
     */
    public static String evenOddBitOperator(int num){
        if (Integer.parseInt(Integer.toBinaryString(Math.abs(num))) % 10 == 0) {
            return "even";
        }
        return "odd";
    }
}
