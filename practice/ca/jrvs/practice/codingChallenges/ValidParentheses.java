package ca.jrvs.practice.codingChallenges;

import java.util.Stack;

/**
 * https://www.notion.so/jarvisdev/Valid-Parentheses-073eb4aeff764ee3aed2f8d9fa7a7b3e
 */
public class ValidParentheses {

    /**
     * Uses a stack to store closing parentheses when the opening parentheses are visited
     * Ensures that if we pop from our stack and it's not the proper closing one, they are not valid
     * Runtime will be O(N) where N is the length of the input string
     * @param parentheses string that contains opening and closing parentheses
     * @return whether or not the parentheses are valid
     */
    public static boolean valid(String parentheses){
        Stack<Character> stack = new Stack();
        for (char c : parentheses.toCharArray()){
            if (c == '{'){
                stack.push('}');
            } else if (c == '('){
                stack.push(')');
            } else if (c == '['){
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c){
                return false;
            }
        }
        return true;
    }
}
