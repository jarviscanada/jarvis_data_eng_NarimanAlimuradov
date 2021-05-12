package ca.jrvs.practice.dataStructures.tree;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class JBSTree<E> implements JTree<E> {

    public static void main(String[] args) {
        Node node = new Node(1, null);
        JBSTree<Integer> jbs = new JBSTree<>(Comparator.comparingInt(i -> i));

        System.out.println(jbs.insert(5));
        System.out.println(jbs.insert(6));
        System.out.println(jbs.insert(4));
        System.out.println(jbs.search(3));


    }

    private Comparator<E> comparator;
    public JBSTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }
    Node head;
    int size = 0;

    @Override
    public E insert(E object) {
        Node node = new Node(object, null);
        Node curr = head;

        if (head == null){
            head = new Node(object, null);
            return (E) head.value;
        }

        while (curr != null){
            if (comparator.compare((E) curr.value, (E) node.value) == 0){
                return null;
            } else if (comparator.compare((E) curr.value, (E) node.value) < 0){
                if (curr.right == null){
                    curr.right = new Node(object, curr);
                    return object;
                } else {
                    curr = curr.right;
                }
            } else {
                if (curr.left == null){
                    curr.left = new Node(object, curr);
                    return object;
                } else {
                    curr = curr.left;
                }
            }
        }
        return null;
    }

    @Override
    public E search(E object) {
        if (head == null || object == null){
            return null;
        }
        Node curr = head;

        while (curr != null){
            if (comparator.compare((E) curr.value, object) == 0){
                return (E) curr.value;
            } else if (comparator.compare((E) curr.value, object) < 0){
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }

        return null;
    }

    @Override
    public E remove(E object) {
        return null;
    }

    /**
     * traverse the tree recursively
     *
     * @return all objects in pre-order
     */
    @Override
    public E[] preOrder() {
        return null;
    }

    private List<E> preOrder(Node head){
        return null;
    }

    /**
     * traverse the tree recursively
     *
     * @return all objects in-order
     */
    @Override
    public E[] inOrder() {
        E[] results = (E[]) new Object[size];
        if (head == null){
            return null;
        }


        return results;
    }

    /**
     * traverse the tree recursively
     *
     * @return all objects pre-order
     */
    @Override
    public E[] postOrder() {
        E[] results = (E[]) new Object[size];
        if (head == null){
            return null;
        }



        return results;

    }

    @Override
    public int findHeight() {
        int height = 1;
        Node node = head;
        while (node.left != null || node.right != null){
            if (node.left != null){
                node = node.left;
            } else {
                node = node.right;
            }
            height += 1;
        }
        return height;
    }

    static final class Node<E> {

        E value;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E value, Node<E> parent) {
            this.value = value;
            this.parent = parent;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Node)) {
                return false;
            }
            Node<?> node = (Node<?>) o;
            return getValue().equals(node.getValue()) &&
                    Objects.equals(getLeft(), node.getLeft()) &&
                    Objects.equals(getRight(), node.getRight()) &&
                    getParent().equals(node.getParent());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getValue(), getLeft(), getRight(), getParent());
        }
    }

}