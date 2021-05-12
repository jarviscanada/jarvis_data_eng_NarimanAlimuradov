package ca.jrvs.practice.dataStructures.tests;

import ca.jrvs.practice.dataStructures.list.LinkedJList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for dataStructures.list.LinkedJList methods
 */
public class LinkedJListTest {
    LinkedJList linkedList;

    @Before
    public void setUp() throws Exception {
        linkedList = new LinkedJList();
    }

    @Test
    public void add() {
        linkedList.add(3);
        assertEquals(3, linkedList.get(0));
    }

    @Test
    public void toArray() {
        Object[] results = linkedList.toArray();
        assertNotNull(results);
    }

    @Test
    public void size() {
        assertEquals(linkedList.size(), 0);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        assertEquals(linkedList.size(), 3);
    }

    @Test
    public void isEmpty() {
        assertTrue(linkedList.isEmpty());
        linkedList.add(1);
        assertFalse(linkedList.isEmpty());
    }

    @Test
    public void indexOf() {
        linkedList.add(1);
        linkedList.add(5);
        linkedList.add(9);
        assertEquals(2, linkedList.indexOf(9));
    }

    @Test
    public void contains() {
        assertFalse(linkedList.contains(9));
        linkedList.add(1);
        linkedList.add(5);
        linkedList.add(9);
        assertTrue(linkedList.contains(9));
    }

    @Test
    public void get() {
        linkedList.add(7);
        assertEquals(7, linkedList.get(0));
        linkedList.add(77);
        assertEquals(77, linkedList.get(1));
    }

    @Test
    public void remove() {
        linkedList.add(7);
        linkedList.add(8);
        linkedList.add(9);
        assertEquals(linkedList.size(), 3);
        linkedList.remove(1);
        assertEquals(linkedList.size(), 2);
        assertFalse(linkedList.contains(8));
    }

    @Test
    public void clear() {
        linkedList.add(7);
        linkedList.add(7);
        linkedList.add(7);
        assertEquals(linkedList.size(), 3);
        linkedList.clear();
        assertEquals(linkedList.size(), 0);
    }
}