package ca.jrvs.practice.codingChallenges;

/**
 * https://www.notion.so/jarvisdev/Print-letter-with-number-c9a4a970411f4d88a3c76f22776efa24
 */
public class PrintLetterWithNumber {

    /**
     * Prints the corresponding number of each letter in the input string by subtracting from the ASCII value
     * Must check if it's lowercase or uppercase beforehand as uppercase letters appear first in ASCII
     * Runtime will be O(N) where N is each character in the input text
     * @param text the input string
     * @return string containing the corresponding number of each character
     */
    public static String printLetter(String text){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++){
            sb.append(text.charAt(i));
            if (Character.isLowerCase(text.charAt(i))){
                sb.append((int) text.charAt(i) - 96);
            } else {
                sb.append((int) text.charAt(i) - 38);
            }
        }
        return sb.toString();
    }
}
