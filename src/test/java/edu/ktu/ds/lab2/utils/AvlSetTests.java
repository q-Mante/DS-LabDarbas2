package edu.ktu.ds.lab2.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AvlSetTests {
    private AvlSet<Integer> root;

    @Before
    public void setUp() {
        root = new AvlSet<>();
        root.add(10);
        root.add(5);
        root.add(15);
        root.add(2);
        root.add(7);
        root.add(12);
        root.add(17);
    }

    @Test
    public void RemoveTest() {
        Assert.assertEquals(7, root.size());
        root.remove(15);
        root.remove(7);
        root.remove(10);
        root.remove(17);
        root.remove(756);
        Assert.assertFalse(root.contains(15));
        Assert.assertTrue(root.contains(12));
        Assert.assertTrue(root.contains(2));
        Assert.assertTrue(root.contains(5));
        Assert.assertFalse(root.contains(17));
        Assert.assertEquals(3, root.size());
    }
}
