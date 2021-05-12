package ca.jrvs.practice.dataStructures.stackQueue;

public class LinkedJListDeque<E> implements JDeque<E>{

    static class Node<E> {
        E data;
        Node next;
        Node(E e){
            data = e;
            next = null;
        }
    }

    private Node head;

    /**
     * Adds to the end of the deque (push will add to the beginning)
     */
    @Override
    public boolean add(E e) {
        Node node = new Node(e);
        if (head == null){
            head = node;
        } else {
            Node curr = head;
            while (curr != null && curr.next != null){
                curr = curr.next;
            }
            curr.next = node;
        }
        return true;
    }

    /**
     * Removes from the end of the deque (pop will remove from the beginning)
     */
    @Override
    public E remove() {
        if (head == null){
            return null;
        }
        Node curr = head;
        Node prev = null;
        while (curr != null && curr.next != null) {
            prev = curr;
            curr = curr.next;
        }

        if (prev != null) {
            prev.next = null;
        } else {
            head = null;
        }

        return (E) curr.data;
    }

    @Override
    public E pop() {
        if (head == null){
            return null;
        }
        Node node = head;
        head = head.next;
        return (E) node.data;
    }

    @Override
    public void push(E e) {
        Node node = new Node(e);
        node.next = head;
        head = node;
    }

    @Override
    public E peek() {
        if (head == null){
            return null;
        }
        return (E) head.data;
    }
}
