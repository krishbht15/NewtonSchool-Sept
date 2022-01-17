package com.company.priority_queue;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(mergeKSortedLists(arr));
    }

    public static void usePriorityQueue() {
        PriorityQueue pq = new PriorityQueue();
        pq.add(10);
        pq.add(50);
        pq.add(20);
        pq.add(40);
        pq.add(30);
        System.out.println("pq.remove() = " + pq.remove());
        System.out.println("pq.remove() = " + pq.remove());
        pq.add(5);
        System.out.println("pq.remove() = " + pq.remove());
        System.out.println("pq.remove() = " + pq.remove());
        System.out.println("pq.remove() = " + pq.remove());
        System.out.println("pq.remove() = " + pq.remove());
    }
//https://practice.geeksforgeeks.org/problems/merge-k-sorted-arrays/
    public static ArrayList<Integer> mergeKSortedLists(int[][] arr) {
        ArrayList<Integer> res = new ArrayList<>();
        java.util.PriorityQueue<DataIndex> pq = new java.util.PriorityQueue<>();
        for (int i = 0; i < arr.length; i++)
            pq.add(new DataIndex(0, i, arr[i][0]));

        while (!pq.isEmpty()) {
            DataIndex d = pq.remove();
            res.add(d.val);
            if (d.idx + 1 < arr[d.lidx].length)
                pq.add(new DataIndex(d.idx + 1, d.lidx, arr[d.lidx][d.idx+1]));
        }
        return res;
    }
}
