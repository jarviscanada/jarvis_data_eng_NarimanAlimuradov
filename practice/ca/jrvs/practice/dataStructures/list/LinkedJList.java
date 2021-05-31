package ca.jrvs.practice.dataStructures.list;

public class LinkedJList<E> implements JList<E>{

    Node head;

    public class Node {
        public E data;
        public Node next;
        Node(E e){
            data = e;
            next = null;
        }
    }

    @Override
    public boolean add(E e) {
        Node newNode = new Node(e);
        newNode.next = null;
        if (head == null){
            head = newNode;
        } else {
            Node node = head;
            while (node.next != null){
                node = node.next;
            }
            node.next = newNode;
        }
        return true;
    }

    @Override
    public Object[] toArray() {
        System.out.println(size());
        Object[] results = new Object[size()];
        Node node = head;
        for (int i = 0; i < size(); i++){
            results[i] = node.data;
            node = node.next;
        }
        return results;
    }

    @Override
    public int size() {
        if (head == null){
            return 0;
        }
        Node node = head;
        int counter = 1;
        while (node.next != null){
            counter += 1;
            node = node.next;
        }
        return counter;
    }

    @Override
    public boolean isEmpty() {
        if (head == null){
            return true;
        }
        return false;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        Node node = head;
        while (node != null){
            if (node.data == o){
                return index;
            } else {
                index += 1;
                node = node.next;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        Node node = head;
        for (int i = 0; i < size(); i++){
            if (node.data == o){
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public E get(int index) {
        if (index >= size()){
            return null;
        }
        Node node = head;
        for (int i = 0; i < index; i++){
            node = node.next;
        }
        return node.data;
    }

    @Override
    public E remove(int index) {
        if (index >= size()){
            return null;
        }
        Node node = head;
        if (index == 0){
            head = head.next;
            return node.data;
        } else {
            for (int i = 0; i < index - 1; i++){
                node = node.next;
            }
            Node returnNode = node.next;
            node.next = node.next.next;
            return returnNode.data;
        }
    }

    @Override
    public void clear() {
        while (head != null){
            remove(0);
        }
    }
}
