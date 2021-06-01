package ca.jrvs.practice.codingChallenges;

/**
 * https://www.notion.so/jarvisdev/String-to-Integer-atoi-9211dd0e635141369ec6c2950cad7cee
 */
public class StringToInteger {

    /**
     * Converts string to integer using the built-in parsing methods
     * Runtime will be O(N) where N is the length of the input String because the method will check each character one by one
     * We must also ensure that the input String is valid by checking the length and flagging non-integer values
     * @param text string to be converted to integer
     * @return the text converted to integer format
     */
    public static int builtIn(String text){
        int result = 0;
        if (!text.matches("\\d+")){
            return 0;
        }
        try {
            result = Integer.parseInt(text);
        } catch (NumberFormatException e){
            System.out.println("Value too large: " + e);
        }
        return result;
    }

    /**
     * Converts string to integer manually using ASCII values
     * Will also have a runtime of O(N) where N is length of input String because we must iterate through each character
     * @param text string to be converted to integer
     * @return the text converted to integer format
     */
    public static int manual(String text){
        int result = 0;
        if (!text.matches("\\d+")){
            return 0;
        }

        for (int i = 0; i < text.length(); i++) {
            int digit = (int) text.charAt(i) - 48;
            result = 10 * result + digit;
        }

        return result;
    }

}
