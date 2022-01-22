package com.company.binary_tree;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
//        display(constructBT(arr, 0));
//        Node n = constructBT(arr, 0);
//        System.out.println(n);
//        Arrays.toString(arr);
//        Arrays.toString();
//        display(n);
        Node n1=new Node(50);
        Node n2=new Node(50);
        System.out.println(n1==n2);
     }

    public static Node constructBT(int[] arr, int i) {
        Node n = new Node(arr[i]);
        int li = 2 * i + 1;
        if (li < arr.length && arr[li] != -1) {
            n.left = constructBT(arr, li);
        }
        int ri = 2 * i + 2;
        if (ri < arr.length && arr[ri] != -1) {
            n.right = constructBT(arr, ri);
        }
        return n;
    }

    public static void display(Node root) {
        if (root == null) return;
        System.out.print(root.left);
        System.out.print(" <- " + root + " -> ");
        System.out.print(root.right);
        System.out.println();
        display(root.left);
        display(root.right);
    }
}
