package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.notion.so/jarvisdev/How-to-compare-two-maps-11019c48753e474c8b0dbb0b093b4bde
 */
public class CompareMaps {
    public static void main(String[] args) {
        Map<String, Integer> m1 = new HashMap<>();
        m1.put("nariman", 1);
        m1.put("narimaa", 2);
        Map<String, Integer> m2 = new HashMap<>();
        m2.put("nariman", 1);
        m2.put("narimaa", 2);

        System.out.println(compareMapsEquals(m1, m2));
    }

    /**
     * Simple equals method that checks if two maps are equal
     * Will have runtime of O(n), where n is the length of the shortest map
     * If one is longer than the other, will return false once we reach the max length of the shorter one
     * @param m1 Hashmap
     * @param m2 Hashmap
     * @return true if the two maps are equal in keys and values, false otherwise
     */
    public static boolean compareMapsEquals(Map m1, Map m2){
        return m1.equals(m2);
    }

    public static boolean compareMaps(Map m1, Map m2){
        return true;
    }
}
