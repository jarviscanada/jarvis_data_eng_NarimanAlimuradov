package ca.jrvs.practice.codingChallenge;

/**
 * https://www.notion.so/jarvisdev/Reverse-Linked-List-e05b31dcfdcb4df39bcb464e7db35cb0
 */
public class ReverseLinkedList {

    static class Node<E> {
        E data;
        Node next;
        Node(E e){
            data = e;
            next = null;
        }
    }

    /**
     * Reverses a linked list by sequentially flipping the pointers of the nodes in the opposite direction
     * O(N) runtime because we have to touch each node once
     * @param head head node of the linked list
     * @return new head node of the reversed linked list
     */
    public static Node reverseIterative(Node head){
        if (head == null || head.next == null){
            return head;
        }
        Node prev = null;
        Node curr = head;
        while (curr != null){
            Node temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    /** Reverses a linked list recursively
     * O(N) runtime again because we still have to touch each node once
     * @param head head node of the linked list
     * @return new head node of the reversed linked list
     */
    public static Node reverseRecursive(Node head){
        if (head == null || head.next == null){
            return head;
        }
        Node recurse = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return recurse;
    }
}
