package edu.ktu.ds.lab2.demo;

import edu.ktu.ds.lab2.utils.BstSet;

import java.util.ArrayList;

public class Task2 {
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
        BstSet<Integer> bstSet = new BstSet<>();
        bstSet.add(10);
        bstSet.add(14);
        bstSet.add(16);
        bstSet.add(17);
        bstSet.add(15);
        bstSet.add(12);
        bstSet.add(13);
        bstSet.add(11);
        bstSet.add(4);
        bstSet.add(6);
        bstSet.add(7);
        bstSet.add(5);
        bstSet.add(2);
        bstSet.add(3);
        bstSet.add(1);

        ArrayList<Integer> elements2 = (ArrayList<Integer>) bstSet.insideElementsOfFullTree();
        System.out.println(elements2);
    }
}
