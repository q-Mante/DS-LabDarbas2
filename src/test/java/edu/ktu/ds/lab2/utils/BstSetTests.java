package edu.ktu.ds.lab2.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class BstSetTests {

    private BstSet<Integer> root;
    private BstSet<Integer> test0;

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

        test0 = new BstSet<>();
        test0.add(6);
        test0.add(5);
        test0.add(10);
        test0.add(8);
        test0.add(7);
        test0.add(9);
        test0.add(11);
    }

    @Test
    public void CopyOfTest() {
        BstSet<Integer> copy = (BstSet<Integer>) test0.copyOf(test0);
        Assert.assertEquals(copy.size, test0.size);
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
    public void AddAllTest() {
        BstSet<Integer> set1 = new BstSet<Integer>();
        BstSet<Integer> set2 = new BstSet<Integer>();

        set1.add(10);
        set1.add(5);
        set1.add(15);

        set2.add(10);

        set2.addAll(set1);

        Assert.assertEquals(3, set2.size());
        Assert.assertTrue(set2.contains(10));
        Assert.assertTrue(set2.contains(5));
        Assert.assertTrue(set2.contains(15));
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

    @Test
    public void HeadSetTest() {
        root.add(11);
        Set<Integer> headset = root.headSet(12);
        Assert.assertEquals(5, headset.size());
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

    @Test
    public void iteratorRemoveTest() {
        Iterator<Integer> iterator = root.iterator();

        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == 15) {
                iterator.remove();
            }
        }
        Assert.assertEquals(6, root.size());

        Assert.assertFalse(root.contains(15));
    }
}
