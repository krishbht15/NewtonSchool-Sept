package com.company.dp;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] dp = new int[11];
        Arrays.fill(dp, -1);
        System.out.println(fibDP(10, dp));
    }

    public static int fibDP(int n, int[] dp) {
        if (n == 0 || n == 1) return dp[n] = n;
        if (dp[n] != -1) return dp[n];
        return dp[n] = fibDP(n - 1, dp) + fibDP(n - 2, dp);
    }
}
