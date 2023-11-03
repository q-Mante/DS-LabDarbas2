package edu.ktu.ds.lab2.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BstSetTests {

    private BstSet<Integer> root;

    @Before
    public void setUp() {
        root = new BstSet<>();
        root.add(10);
        root.add(5);
        root.add(15);
        root.add(2);
        root.add(7);
        root.add(12);
        root.add(17);
    }

    @Test
    public void ContainsAllTest() {
        BstSet<Integer> set1 = new BstSet<>();
        BstSet<Integer> set2 = new BstSet<>();

        set1.add(10);
        set1.add(5);
        set1.add(15);

        set2.add(5);
        set2.add(10);

        Assert.assertTrue(set1.containsAll(set2));
        set2.add(20);
        Assert.assertFalse(set1.containsAll(set2));
    }

    @Test
    public void RetainAllTest() {
        BstSet<Integer> set1 = new BstSet<>();
        BstSet<Integer> set2 = new BstSet<>();

        set1.add(10);
        set1.add(5);
        set1.add(15);
        set1.add(2);
        set1.add(7);

        set2.add(10);
        set2.add(5);
        set2.add(12);
        set2.add(17);

        set1.retainAll(set2);
        Assert.assertEquals(2, set1.size());
        Assert.assertTrue(set1.contains(10));
        Assert.assertTrue(set1.contains(5));
    }

    @Test
    public void HeadSetTest() {
        Set<Integer> headset = root.headSet(12);
        Assert.assertEquals(4, headset.size());
        Assert.assertTrue(headset.contains(2));
        Assert.assertTrue(headset.contains(5));
        Assert.assertTrue(headset.contains(7));
        Assert.assertTrue(headset.contains(10));
        Assert.assertFalse(headset.contains(12));
    }

    @Test
    public void TailSetTest() {
        Set<Integer> tailset = root.tailSet(12);
        Assert.assertEquals(3, tailset.size());
        Assert.assertTrue(tailset.contains(12));
        Assert.assertTrue(tailset.contains(15));
        Assert.assertTrue(tailset.contains(17));
        Assert.assertFalse(tailset.contains(5));
    }

    @Test
    public void SubSetTest() {
        Set<Integer> subset = root.subSet(5, 15);
        Assert.assertEquals(4, subset.size());
        Assert.assertTrue(subset.contains(5));
        Assert.assertTrue(subset.contains(7));
        Assert.assertTrue(subset.contains(10));
        Assert.assertTrue(subset.contains(12));
        Assert.assertFalse(subset.contains(15));
    }
}
