package ca.jrvs.practice.codingChallenges;

import java.util.HashSet;

/**
 * https://www.notion.so/jarvisdev/Duplicate-Characters-7b08f4858f2a41d699e8e6c12dab9913
 */
public class DuplicateCharacters {

    /**
     * Finds all duplicate characters in an array by storing visited characters in a set
     * Characters that were already in the initial set were added to the result set as they are duplicates
     * Excluded ASCII value under which includes whitespace and other unneeded characters
     * Runtime will be O(N) where N is the length of the input string
     * @param text the input string
     * @return set containing duplicate characters
     */
    public static HashSet<Character> getDuplicates(String text){
        HashSet<Character> results = new HashSet<>();
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            if (!set.contains(c)){
                set.add(c);
            } else {
                if ((int) c > 32){
                    results.add(c);
                }
            }
        }
        return results;
    }
}
