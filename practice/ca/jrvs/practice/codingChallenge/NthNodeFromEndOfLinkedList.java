package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/jarvisdev/Nth-Node-From-End-of-LinkedList-657366cc88604123a2e2b3be33b26aa0
 */
public class NthNodeFromEndOfLinkedList {
    static class Node<E> {
        E data;
        Node next;
        Node(E e){
            data = e;
            next = null;
        }
    }

    /**
     * Finds the Nth node from the end of a list by using a slow and fast pointer separated by N nodes
     * As such, when the fast pointer reaches the end, the slow pointer will be at our wanted node
     * Runtime will be O(N) as we go through the linked list once with each pointer
     * @param head the head node of the linked list
     * @param n the index from the end we are looking for
     * @return the Nth node from the end of the linked list
     */
    public static Node nthNodeFromEnd(Node head, int n){
        if (head == null || head.next == null){
            return head;
        }
        Node slow = head;
        Node fast = head;
        for (int i = 0; i < n; i++){
            fast = fast.next;
        }
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
