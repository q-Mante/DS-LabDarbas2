package edu.ktu.ds.lab2.demo;

import edu.ktu.ds.lab2.utils.BstSet;

import java.util.ArrayList;

public class Task1 {
    public static void main(String[] args) {
        //           10
        //          /  \
        //	       /    \
        //	      /      \
        //       /        \
        //      /          \
        //     4           14
        //    / \         /  \
        //   /   \       /    \
        //  2     6     12    16
        // / \   / \   / \    / \
        //1   3 5   7 11  13 15  17
        BstSet<Integer> bstSet1 = new BstSet<>();
        bstSet1.add(10);
        bstSet1.add(14);
        bstSet1.add(16);
        bstSet1.add(17);
        bstSet1.add(15);
        bstSet1.add(12);
        bstSet1.add(13);
        bstSet1.add(11);
        bstSet1.add(4);
        bstSet1.add(6);
        bstSet1.add(7);
        bstSet1.add(5);
        bstSet1.add(2);
        bstSet1.add(3);
        bstSet1.add(1);

        BstSet<Integer> bstSet2 = new BstSet<>();
        bstSet2.add(15);
        bstSet2.add(23);
        bstSet2.add(7);
        bstSet2.add(9);
        bstSet2.add(4);
        bstSet2.add(1);
        bstSet2.add(2);

        System.out.println(bstSet1.isBalanced(0));
        System.out.println(bstSet2.isBalanced(3));
        System.out.println(bstSet2.isBalanced(2));
    }
}
