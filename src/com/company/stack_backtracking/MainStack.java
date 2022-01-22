package com.company.stack_backtracking;

import java.util.ArrayList;
import java.util.Stack;

public class MainStack {
    public static void main(String[] args) {
        int[] arr = {6, 2, 5, 4, 5, 1, 6};
        System.out.println(largestHistogram(arr));
    }

    public static int celebrityProblem(int[][] m, int n) {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            st.push(i);
        }
        while (!st.isEmpty() && st.size() > 1) {
            int j = st.pop();
            int i = st.pop();
            if (m[i][j] == 1) st.push(j);
            else if (m[j][i] == 1) st.push(i);
        }
        if (!st.isEmpty()) {
            int candidate = st.pop();
            for (int i = 0; i < n; i++) {
                if (m[candidate][i] == 1) return -1;
            }
            for (int i = 0; i < n; i++) {
                if (i != candidate && m[i][candidate] == 0) return -1;
            }
            return candidate;
        }
        return -1;
    }

    public static int largestHistogram(int[] arr) {
        int[] lsi = lsi(arr);
        int[] rsi = rsi(arr);
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            int c = (lsi[i] + rsi[i] - 1) * arr[i];
            res = Math.max(c, res);
        }
        return res;
    }

    public static int[] lsi(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] res = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            if (st.isEmpty() || arr[st.peek()] < arr[i]) st.push(i);
            else {
                while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                    int idx = st.pop();
                    res[idx] = idx - i;
                }
                st.push(i);
            }
        }
        while (!st.isEmpty()) {
            int idx = st.pop();
            res[idx] = idx + 1;
        }
        return res;
    }

    public static int[] rsi(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (st.isEmpty() || arr[st.peek()] < arr[i]) st.push(i);
            else {
                while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                    int idx = st.pop();
                    res[idx] = i - idx;
                }
                st.push(i);
            }
        }
        while (!st.isEmpty()) {
            int idx = st.pop();
            res[idx] = arr.length - idx;
        }
        return res;
    }

    public static ArrayList<String> res;

    public static void ratInMaze(int[][] arr, int r, int c, String path) {
        if (r == arr.length - 1 && c == arr[0].length - 1) {
            res.add(path);
            return;
        }
        arr[r][c] = 0;
        //up
        if (r - 1 >= 0 && arr[r - 1][c] == 1)
            ratInMaze(arr, r - 1, c, path + "U");
        //left
        if (c - 1 >= 0 && arr[r - 1][c - 1] == 1)
            ratInMaze(arr, r, c - 1, path + "L");
        //down
        if (r + 1 < arr.length && arr[r + 1][c] == 1)
            ratInMaze(arr, r + 1, c, path + "D");
        //right
        if (c + 1 < arr[0].length && arr[r][c + 1] == 1)
            ratInMaze(arr, r, c + 1, path + "R");
        arr[r][c] = 1;
    }
}
