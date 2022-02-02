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

    public static int knapSackDp(int[] v, int[] w, int size, int i,   int[][] dp) {
        if (i == v.length || size == 0) return 0;
        if (dp[i][size] != 0) return dp[i][size];
        int sel = size - w[i] >= 0 ? knapSackDp(v, w, size - w[i], i + 1, dp)  + v[i]
                :0;
        int notSel = knapSackDp(v, w, size, i + 1,  dp);
        return dp[i][size] = Math.max(sel, notSel);
    }
}
