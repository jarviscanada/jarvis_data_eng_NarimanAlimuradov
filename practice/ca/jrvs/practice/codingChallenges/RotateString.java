package ca.jrvs.practice.codingChallenges;

/**
 * https://www.notion.so/jarvisdev/Rotate-String-93aa7774d5c4407dac1ce978ce1c51fe
 */
public class RotateString {

    /**
     * Clever solution that appends two copies of the first string together to check if the second appears in it
     * Runtime will be O(N^2) where N is the length of both strings because of the .contains() method
     * @param text1 input string
     * @param text2 input string
     * @return whether or not the strings are rotations of each other
     */
    public static boolean isRotated(String text1, String text2){
        if (text1.length() == text2.length()){
            if ((text1 + text1).contains(text2)){
                return true;
            }
        }
        return false;
    }
}
