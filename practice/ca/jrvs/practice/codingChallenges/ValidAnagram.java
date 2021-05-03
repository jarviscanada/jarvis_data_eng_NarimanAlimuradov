package ca.jrvs.practice.codingChallenges;

import java.util.Arrays;
import java.util.HashMap;

/**
 * https://www.notion.so/jarvisdev/Valid-Anagram-2e028e9e6aaa42de8923130a215fc1f8
 */
public class ValidAnagram {

    /**
     * Finds whether two strings are anagr ams by sorting the string characters and comparing the result
     * Runtime will be O(NlogN) as it is bottlenecked by the two sorts
     * @param text1 the first string
     * @param text2 the second string
     * @return whether or not the two strings are anagrams of each other
     */
    public static boolean isValidAnagramSorting(String text1, String text2){
        char[] charArray1 = text1.toCharArray();
        char[] charArray2 = text2.toCharArray();
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);
        if (String.valueOf(charArray1).equals(String.valueOf(charArray2))){
            return true;
        }
        return false;
    }

    /**
     * Linear solution that uses a hash map to store character occurrences to ensure each word contains the same characters
     * Check to make sure the sum of each dictionary value is 0, implying that the characters from each string cancel out
     * Runtime will be O(N) as we iterate through the characters several times
     * @param text1 the first string
     * @param text2 the second string
     * @return whether or not the two strings are anagrams of each other
     */
    public static boolean isValidAnagramDict(String text1, String text2){
        HashMap<Character, Integer> dict = new HashMap<>();
        for (int i = 0; i < text1.length(); i++){
            if (dict.containsKey(text1.charAt(i))){
                dict.put(text1.charAt(i), dict.get(text1.charAt(i)) + 1);
            } else {
                dict.put(text1.charAt(i), 1);
            }
        }
        for (int i = 0; i < text2.length(); i++){
            if (dict.containsKey(text2.charAt(i))) {
                dict.put(text2.charAt(i), dict.get(text2.charAt(i)) - 1);
            } else {
                dict.put(text2.charAt(i), -1);
            }
        }
        for (Character key : dict.keySet()){
            if (dict.get(key) != 0){
                return false;
            }
        }
        return true;
    }
}
