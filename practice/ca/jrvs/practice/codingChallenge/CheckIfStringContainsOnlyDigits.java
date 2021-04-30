package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/jarvisdev/Check-if-a-String-contains-only-digits-1e795508d2344e7f892692ca43b69b5b
 */
public class CheckIfStringContainsOnlyDigits {

    /**
     * Checks if a string has only numbers by comparing the ASCII values of each character
     * Digits will have ASCII values between 48 and 57
     * Runtime will be O(N) where N is the length of the string
     * @param text the sample text
     * @return whether or not the string contains only numbers
     */
    public static boolean asciiCheck(String text){
        for (int i = 0; i < text.length(); i++){
            if ((int) text.charAt(i) < 48 || (int) text.charAt(i) > 57){
                return false;
            }
        }
        return true;
    }

    /**
     * Implementation that uses the Java API's valueOf() method to check if the string can be converted
     * If it can't, catch the exception and return false
     * Runtime will remain O(N)
     * @param text the sample text
     * @return whether or not the string contains only numbers
     */
    public static boolean javaApiCheck(String text){
        try {
            Integer.valueOf(text);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    /**
     * Simple regex solution that checks if the text matches the specified regex for digits
     * Runtime will still be O(N)
     * @param text the sample text
     * @return whether or not the string contains only numbers
     */
    public static boolean regexCheck(String text){
        if (text.matches("\\d+")){
            return true;
        }
        return false;
    }
}
