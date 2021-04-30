package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/jarvisdev/LinkedList-Cycle-e2a79f1ccaa044cca9a0a22ad5e2ee25
 */
public class LinkedListCycle {

    static class Node<E> {
        E data;
        Node next;
        Node(E e){
            data = e;
            next = null;
        }
    }

    /**
     * Checks if there is a cycle in a linked list by employing a fast and slow pointer
     * O(N) runtime as either the fast pointer will reach the end or it catches up to the slow pointer (both linear)
     * @param head head node of the linked list
     * @return whether or not a cycle was detected
     */
    public static boolean listCycle(Node head){
        if (head == null){
            return false;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null){
            if (fast == slow){
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}
