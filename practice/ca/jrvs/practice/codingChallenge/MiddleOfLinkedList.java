package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/jarvisdev/Middle-of-the-Linked-List-ea68dec0a2b74735b72a254f1b219e08
 */
public class MiddleOfLinkedList {
    static class Node<E> {
        E data;
        Node next;
        Node(E e){
            data = e;
            next = null;
        }
    }

    /**
     * Finds the middle of the linked list by using a slow pointer and a fast pointer
     * Fast pointer will increment twice as fast as the slow one so when it reaches the end, the slow pointer will be at our target
     * Runtime will be O(N) as we go through the linked list once with each pointer
     * @param head the head node of the linked list
     * @return the middle node
     */
    public static Node getMiddleNode(Node head){
        if (head == null || head.next == null){
            return head;
        }
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
