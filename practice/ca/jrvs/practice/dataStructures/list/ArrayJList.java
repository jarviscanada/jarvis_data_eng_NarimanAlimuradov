package ca.jrvs.practice.dataStructures.list;

import java.util.Arrays;

public class ArrayJList<E> implements JList<E> {

    private static final int DEFAULT_CAPACITY = 10;
    transient Object[] elementData;
    private int size = 0;

    public ArrayJList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public ArrayJList() {
        this(DEFAULT_CAPACITY);
    }

    public boolean add(E e) {
        if (elementData.length == size){
            elementData = Arrays.copyOf(elementData, elementData.length * 2);
        }
        elementData[size] = e;
        size += 1;
        return true;
    }

    public Object[] toArray() {
        return elementData;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size > 0){
            return false;
        }
        return true;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < size; i++){
            if (o.equals(elementData[i])){
                return i;
            }
        }
        return -1;
    }

    public boolean contains(Object o) {
        for (int i = 0; i < size; i++){
            if (o.equals(elementData[i])){
                return true;
            }
        }
        return false;
    }

    public E get(int index) {
        return (E) elementData[index];
    }

    public E remove(int index) {
        Object result = elementData[index];
        for (int i = index; i < size; i++){
            elementData[i] = elementData[i + 1];
        }
        size -= 1;
        return (E) result;
    }

    public void clear() {
        for (int i = 0; i < size; i++){
            elementData[i] = null;
        }
        size = 0;
    }
}