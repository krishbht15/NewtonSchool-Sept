package com.company.binary_tree;

public class AVLNode {

    int val;
    AVLNode left;
    AVLNode right;
    int height;
    int bf;

    public AVLNode(int data) {
        this.val = data;
        this.height = 0;
        this.bf = 0;
    }

    @Override
    public String toString() {
        return val + "";
    }
}
