package ca.jrvs.practice.dataStructures.tests;

import ca.jrvs.practice.dataStructures.list.ArrayJList;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Tests for dataStructures.list.ArrayJList methods
 */
public class ArrayJListTest {
    ArrayJList arrayList;

    @Before
    public void setUp() throws Exception {
        arrayList = new ArrayJList();
    }

    @Test
    public void add() {
        arrayList.add(1);
        assertEquals(1, arrayList.get(0));
        arrayList.add(3);
        assertEquals(3, arrayList.get(1));
    }

    @Test
    public void toArray() {
        Object[] array = arrayList.toArray();
        assertNotNull(array);
    }

    @Test
    public void size() {
        assertEquals(0, arrayList.size());
        arrayList.add(1);
        assertEquals(1, arrayList.size());
        arrayList.add(2);
        arrayList.add(3);
        assertEquals(3, arrayList.size());
    }

    @Test
    public void isEmpty() {
        assertTrue(arrayList.isEmpty());
        arrayList.add(1);
        assertFalse(arrayList.isEmpty());
    }

    @Test
    public void indexOf() {
        arrayList.add(1);
        arrayList.add(5);
        arrayList.add(9);
        assertEquals(2, arrayList.indexOf(9));
    }

    @Test
    public void contains() {
        assertFalse(arrayList.contains(9));
        arrayList.add(1);
        arrayList.add(5);
        arrayList.add(9);
        assertTrue(arrayList.contains(9));
    }

    @Test
    public void get() {
        arrayList.add(11);
        assertEquals(11, arrayList.get(0));
        arrayList.add(111);
        assertEquals(111, arrayList.get(1));
    }

    @Test
    public void remove() {
        arrayList.add(15);
        assertEquals(15, arrayList.get(0));
        arrayList.remove(0);
        assertNull(arrayList.get(0));
    }

    @Test
    public void clear() {
        arrayList.add(1);
        arrayList.add(1);
        arrayList.add(1);
        assertEquals(3, arrayList.size());
        arrayList.clear();
        assertEquals(0, arrayList.size());
    }
}