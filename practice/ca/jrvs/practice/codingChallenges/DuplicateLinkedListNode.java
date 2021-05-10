package ca.jrvs.practice.codingChallenges;

import java.util.HashSet;

/**
 * https://www.notion.so/jarvisdev/Duplicate-LinkedList-Node-fb798b1f85a14cecb7c0cad1f0a349d2
 */
public class DuplicateLinkedListNode {

    static class Node<E> {
        E data;
        Node next;
        Node(E e){
            data = e;
            next = null;
        }
    }

    /**
     * Solution that uses a set to store already visited nodes and changing the pointers if a duplicate is found
     * Runtime will be O(N) where N is the number of nodes as we have to iterate through them
     * @param head head node of the linked list
     * @return head node of the new linked list that doesn't have duplicates
     */
    public static Node removeDuplicates(Node head){
        HashSet set = new HashSet();
        Node curr = head;
        Node prev = null;
        while (curr != null){
            if (!set.contains(curr.data)){
                set.add(curr.data);
                prev = curr;
                curr = curr.next;
            } else {
                curr = curr.next;
                prev.next = curr;
            }
        }
        return head;
    }

}
