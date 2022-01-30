package com.company.binary_tree;

public class AVLTree {
    public static void main(String[] args) {

    }

    private static void updateHeightAndBf(AVLNode node) {
        int l = node.left != null ? node.left.height : -1;
        int r = node.right != null ? node.right.height : -1;
        node.height = Math.max(l, r) + 1;
        node.bf = l - r;
    }

    public static AVLNode balance(AVLNode node) {
        updateHeightAndBf(node);

        if (node.bf == 2) {
            if (node.left.bf == 1) {
//LL
            } else if (node.left.bf == -1) {
//LR
            }
        } else if (node.bf == -2) {
            if (node.right.bf == 1) {
//RL
            } else if (node.right.bf == -1) {
//RR
            }
        }
        return node;
    }

    public static AVLNode LL(AVLNode A) {
        AVLNode B = A.left;
        A.left = B.right;
        B.right = A;
        updateHeightAndBf(A);
        updateHeightAndBf(B);
        return B;
    }

    public static AVLNode RR(AVLNode A) {
        AVLNode B = A.right;
        A.right = B.left;
        B.left = A;
        updateHeightAndBf(A);
        updateHeightAndBf(B);
        return B;
    }
}
