package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.notion.so/jarvisdev/How-to-compare-two-maps-11019c48753e474c8b0dbb0b093b4bde
 */
public class CompareMaps {
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

    /**
     * Manual implementation of the above method. Also O(n) runtime.
     * Take all the keys of the first map and compare values of two maps one by one.
     * If at any point the keys or values don't match, we break out.
     * @param m1 Hashmap
     * @param m2 Hashmap
     * @return true if the two maps are equal in keys and values, false otherwise
     */
    public static boolean compareMapsManual(Map m1, Map m2){
        for (Object key : m1.keySet()){
            if (m1.get(key) != m2.get(key)){
                return false;
            }
        }
        return true;
    }
}
