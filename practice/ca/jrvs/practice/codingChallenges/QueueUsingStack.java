package ca.jrvs.practice.codingChallenges;

import java.util.Stack;

/**
 * https://www.notion.so/jarvisdev/Implement-Queue-using-Stacks-d5684314a40b46bf872a75da05235c2e
 */
public class QueueUsingStack {

    /**
     * Implementation of a stack where we send values between two queues to position them as if it was a stack
     * Pushing to the queue will be O(N) where N is the number of values in the queue
     * Popping will be O(1) as we already arranged the first value inserted to be at the top of the first stack
     */
    public static class TwoStackQueue{
        private Stack<Integer> stack1 = new Stack<>();
        private Stack<Integer> stack2 = new Stack<>();

        public void push(int num){
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
            stack2.push(num);
            while (!stack2.isEmpty()){
                stack1.push(stack2.pop());
            }
        }

        public int pop(){
            return stack1.pop();
        }
    }

    /**
     * Implementation of a stack using two queues
     * When the second stack is empty during a pop, it will push everything from the first stack onto it
     * Until it is emptied again, both pushing and popping will be O(1) runtime operations
     * However in the worst-case (when the second stack has no values), popping can take O(N)
     */
    public static class EfficientTwoStackQueue{
        private Stack<Integer> stack1 = new Stack<>();
        private Stack<Integer> stack2 = new Stack<>();

        public void push(int num){
            stack1.push(num);
        }

        public int pop(){
            if (stack2.isEmpty()){
                while (!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }
    }
}
