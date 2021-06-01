package ca.jrvs.practice.dataStructures.tests;

import ca.jrvs.practice.dataStructures.stackQueue.LinkedJListDeque;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedJListDequeTest {
    LinkedJListDeque deque;

    @Before
    public void setUp() throws Exception {
        deque = new LinkedJListDeque();
    }

    @Test
    public void add() {
        deque.add(1);
        deque.add(2);
        deque.add(3);
        assertEquals(1, deque.peek());
        assertEquals(3, deque.remove());
    }

    @Test
    public void remove() {
        deque.add(55);
        assertEquals(55, deque.peek());
        deque.remove();
        assertNotEquals(55, deque.peek());
    }

    @Test
    public void pop() {
        deque.push(90);
        deque.push(80);
        deque.push(54);
        assertEquals(54, deque.pop());
        assertEquals(80, deque.pop());
        assertEquals(90, deque.pop());
    }

    @Test
    public void push() {
        deque.push(40);
        deque.push(1);
        assertEquals(1, deque.pop());
        assertEquals(40, deque.pop());
    }

    @Test
    public void peek() {
        deque.push(10213);
        deque.add(31202);
        assertEquals(10213, deque.peek());
        deque.pop();
        assertEquals(31202, deque.peek());
    }
}