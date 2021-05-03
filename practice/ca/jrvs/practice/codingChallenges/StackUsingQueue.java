package ca.jrvs.practice.codingChallenges;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.notion.so/jarvisdev/Implement-Stack-using-Queue-163a2cc1f3b24e8a8b520e419874e235
 */
public class StackUsingQueue {

    /**
     * Implementation of a stack where we shuffle values between two queues to simulate a stack
     * Every pop we reshuffle until we reach the most recently added value to be returned (like a stack)
     * Pushing to the stack is constant O(1) runtime, however popping from the stack is linear O(N)
     */
    public static class TwoQueueStack{
        private Queue<Integer> queue1 = new LinkedList<>();
        private Queue<Integer> queue2 = new LinkedList<>();

        public void push(int num){
            queue1.add(num);
        }

        public int pop(){
            while (queue1.size() > 1){
                queue2.add(queue1.remove());
            }
            int result = queue1.remove();
            Queue<Integer> tempQueue = queue1;
            queue1 = queue2;
            queue2 = tempQueue;
            return result;
        }
    }

    /**
     * Solution that uses only one queue which shuffles the values into order every time we add to it
     * Pushing is now O(N) where N is the number of values in the stack, but popping is O(1) runtime
     */
    public static class OneQueueStack{
        private Queue<Integer> queue = new LinkedList<>();

        public void push(int num){
            queue.add(num);
            for (int i = 1; i < queue.size(); i++){
                queue.add(queue.remove());
            }
        }

        public int pop(){
            return queue.remove();
        }
    }

}
