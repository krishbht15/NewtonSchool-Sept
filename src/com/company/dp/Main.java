package com.company.dp;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] dp = new int[5];
        Arrays.fill(dp, -1);
//        System.out.println(fibDP(10, dp));
//        System.out.println(climbingStairsDp(4, dp));
        System.out.println(climbingStairsDp(4));
    }

    public static int fibDP(int n, int[] dp) {
        if (n == 0 || n == 1) return dp[n] = n;
        if (dp[n] != -1) return dp[n];
        return dp[n] = fibDP(n - 1, dp) + fibDP(n - 2, dp);
    }

    public static int climbingStairsDp(int n, int[] dp) {
        if (n < 0) return 0;
        if (n == 0) return 1;
        if (dp[n] != -1) return dp[n];
        return dp[n] = climbingStairsDp(n - 1, dp) + climbingStairsDp(n - 2, dp);
    }

    public static int climbingStairsDp(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            if (i - 2 >= 0) dp[i] += dp[i - 2];
        }
        return dp[n];
    }

    public static int knapSack(int[] v, int[] w, int size, int i) {
        if (i == w.length || size == 0) return Integer.MIN_VALUE;
        int selected = size - w[i] >= 0 ?
                knapSack(v, w, size - w[i], i + 1) + v[i]
                : Integer.MIN_VALUE;
        int notSelected = knapSack(v, w, size, i + 1);
        return Math.max(selected, notSelected);
    }

    public static int knapSackDp(int[] v, int[] w, int size, int i, int[][] dp) {
        if (i == v.length || size == 0) return 0;
        if (dp[i][size] != 0) return dp[i][size];
        int sel = size - w[i] >= 0 ? knapSackDp(v, w, size - w[i], i + 1, dp) + v[i]
                : 0;
        int notSel = knapSackDp(v, w, size, i + 1, dp);
        return dp[i][size] = Math.max(sel, notSel);
    }

    public static int knapsackUnbounded(int[] wt, int[] val, int size, int i) {
        if (size == 0 || i == wt.length) return 0;
        int sel = size - wt[i] >= 0 ? knapsackUnbounded(wt, val, size - wt[i], i) + val[i] : 0;
        int notSel = knapsackUnbounded(wt, val, size, i + 1);
        return Math.max(sel, notSel);
    }

    public static int knapsackUnboundedDP(int[] wt, int[] val, int size, int i, int[][] dp) {
        if (size == 0 || i == wt.length) return 0;
        if (dp[i][size] != 0) return dp[i][size];
        int sel = size - wt[i] >= 0 ? knapsackUnbounded(wt, val, size - wt[i], i) + val[i] : 0;
        int notSel = knapsackUnbounded(wt, val, size, i + 1);
        return dp[i][size] = Math.max(sel, notSel);
    }

    public static int coinChange(int[] arr, int amt, int i) {
        if (amt == 0) return 0;
        if (i == arr.length) return Integer.MAX_VALUE;
        int sel = amt - arr[i] >= 0 ? coinChange(arr, amt - arr[i], i) : Integer.MAX_VALUE;
        if (sel != Integer.MAX_VALUE) sel++;
        int notSel = coinChange(arr, amt, i + 1);
        return Math.min(sel, notSel);
    }

    public static int coinChangeDp(int[] arr, int amt, int i, int[][] dp) {
        if (amt == 0) return 0;
        if (i == arr.length) return Integer.MAX_VALUE;
        if (dp[i][amt] != 0) return dp[i][amt];
        int sel = amt - arr[i] >= 0 ? coinChangeDp(arr, amt - arr[i], i, dp) : Integer.MAX_VALUE;
        if (sel != Integer.MAX_VALUE) sel++;
        int notSel = coinChangeDp(arr, amt, i + 1, dp);
        return dp[i][amt] = Math.min(sel, notSel);
    }

    public static int totalDecodingMessage(String s) {
        if (s.length() == 0) return 1;
        int curr = s.charAt(0) - '0';
        if (curr == 0) return 0;
        int res = totalDecodingMessage(s.substring(1));
        if (s.length() > 1) {
            int sec = s.charAt(1) - '0';
            if (curr * 10 + sec < 27) res += totalDecodingMessage(s.substring(2));
        }
        return res;
    }

    public static int totalDecodingMessageDP(String s, int[] dp) {
        if (s.length() == 0) return 1;
        int curr = s.charAt(0) - '0';
        if (curr == 0) return dp[s.length()] = 0;
        if (dp[s.length()] != 0) return dp[s.length()];
        int res = totalDecodingMessageDP(s.substring(1), dp);
        if (s.length() > 1) {
            int sec = s.charAt(1) - '0';
            if (curr * 10 + sec < 27) res += totalDecodingMessageDP(s.substring(2), dp);
        }
        return dp[s.length()] = res;
    }

    public static int totalDecodingMessageDp(String s) {
        int[] dp = new int[s.length() + 1];
        dp[s.length()] = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i] = dp[i + 1];
            if (i + 1 < s.length()) {
                int curr = s.charAt(i) - '0';
                if (curr == 0) continue;
                int sec = s.charAt(i + 1) - '0';
                int val = curr * 10 + sec;
                if (val < 27) dp[i] += dp[i + 2];
            }
        }
        return dp[0];
    }
}
