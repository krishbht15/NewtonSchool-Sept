package com.company.binary_tree;

public class AVLTree {
    public static void main(String[] args) {
//        https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
        int[] arr = {10, 20, 30, 40, 50, 60, 70};
        AVLNode root = null;
        for (int i = 0; i < arr.length; i++) {
            root = add(root, arr[i]);
//            display(root);
//            System.out.println();
//            System.out.println("==================================================");
//            System.out.println();

        }
        display(root);
        System.out.println();
        System.out.println("==================================================");
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            root = remove(root, arr[i]);
            display(root);
            System.out.println();
            System.out.println("==================================================");
            System.out.println();
        }
    }

    public static void display(AVLNode root) {
        if (root == null) return;
        System.out.println((root.left != null ? root.left : "null") + " <= " + (root) + " => " + (root.right != null ? root.right : "null"));
        display(root.left);
        display(root.right);
    }

    public static AVLNode add(AVLNode root, int data) {
        if (root == null) return new AVLNode(data);
        if (root.val > data) {
            root.left = add(root.left, data);
        } else if (root.val < data) {
            root.right = add(root.right, data);
        }
        return balance(root);
    }

    public static AVLNode remove(AVLNode root, int data) {
        if (root.val < data) {
            root.right = remove(root.right, data);
        } else if (root.val > data) {
            root.left = remove(root.left, data);
        } else {
            if (root.left == null && root.right == null) return null;
            else if (root.left != null && root.right == null) return root.left;
            else if (root.right != null && root.left == null) return root.right;
            else {
                int max = getMax(root.left);
                root.val = max;
                root.left = remove(root.left, max);
            }
        }
        return balance(root);
    }

    private static int getMax(AVLNode node) {
        if (node.right == null) return node.val;
        return getMax(node.right);
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
                return LL(node);
            } else if (node.left.bf == -1) {
//LR
                return LR(node);
            }
        } else if (node.bf == -2) {
            if (node.right.bf == 1) {
//RL
                return RL(node);
            } else if (node.right.bf == -1) {
//RR
                return RR(node);
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

    public static AVLNode LR(AVLNode A) {
        AVLNode B = A.left;
        RR(B);
        return LL(A);
    }

    public static AVLNode RL(AVLNode A) {
        AVLNode B = A.left;
        LL(B);
        return RR(A);
    }
}
