package ca.jrvs.practice.codingChallenges;

/**
 * https://www.notion.so/jarvisdev/Valid-Palindrome-178deca6a5514c01ac3c6f01c3dbdf59
 */
public class ValidPalindrome {

    /**
     * Determines if a string is a palindrome by comparing the characters at each end
     * This implementation excludes non-alphabetic characters as per the LeetCode scope
     * Runtime will be O(N) as we are simply looping across the characters a constant number of times
     * @param text the sample string
     * @return whether or not the string is a palindrome
     */
    public static boolean isValidPalindromePointers(String text){
        int start = 0;
        int end = text.length() - 1;
        while (start < end){
            while (!Character.isAlphabetic(text.charAt(start))){
                start += 1;
            }
            while (!Character.isAlphabetic(text.charAt(end))){
                end -= 1;
            }
            if (text.charAt(start) == text.charAt(end)){
                start += 1;
                end -= 1;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Recursive solution that follows a similar approach to the above answer
     * Uses a helper method to recursively obtain the answer after filtering for alphabetic characters
     * Runtime will still be O(N) for the same reasons as above
     * @param text the sample string
     * @return whether or not the string is a palindrome
     */
    public static boolean isValidPalindromeRecursion(String text){
        String filteredText = text.replaceAll("[^a-zA-z]", "");
        return recurse(filteredText, 0, filteredText.length());
    }

    private static boolean recurse(String text, int start, int end){
        if (start >= end){
            return true;
        }
        if (text.charAt(start) == text.charAt(end - 1)){
            start += 1;
            end -= 1;
            return recurse(text, start, end);
        } else {
            return false;
        }
    }
}
