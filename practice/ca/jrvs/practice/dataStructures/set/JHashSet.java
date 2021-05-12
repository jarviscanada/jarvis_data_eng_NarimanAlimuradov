package ca.jrvs.practice.dataStructures.set;

public class JHashSet<E> implements JSet<E>{
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public void clear() {

    }
}
