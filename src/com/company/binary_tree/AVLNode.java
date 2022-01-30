package com.company.binary_tree;

public class AVLNode {

    int data;
    AVLNode left;
    AVLNode right;
    int height;
    int bf;

    public AVLNode(int data) {
        this.data = data;
        this.height = 0;
        this.bf = 0;
    }
}
