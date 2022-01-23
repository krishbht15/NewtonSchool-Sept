package com.company.backtracking_hashmap;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }


    static List<List<String>> res;

    public static void nqueens(char[][] chess, int r, int c) {
        if (r == chess.length) {
            ArrayList<String> temp = new ArrayList<>();
            for (int i = 0; i < chess.length; i++) {
                StringBuilder sb = new StringBuilder("");
                for (int j = 0; j < chess.length; j++) {
                    sb.append(chess[i][j]);
                }
                temp.add(sb.toString());
            }
            res.add(temp);
            return;
        }
        boolean canPlace = canQueenBePlaced(chess, r, c);
        if (canPlace) {
            chess[r][c] = 'Q';
            nqueens(chess, r + 1, 0);
            chess[r][c] = '.';
        }
        if (c + 1 < chess.length) nqueens(chess, r, c + 1);
    }

    static int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}};

    public static boolean canQueenBePlaced(char[][] chess, int r, int c) {
        for (int i = 0; i < dir.length; i++) {
            int tr = r;
            int tc = c;
            int mr = dir[i][0];
            int mc = dir[i][1];
            while (tr + mr >= 0 && tc + mc >= 0 && tc + mc < chess.length) {
                if (chess[tr + mr][tc + mc] == 'Q') return false;
                tr += mr;
                tc += mc;
            }
        }
        return true;
    }

    public static boolean isValidSudoku(char[][] board) {
        for (int i = 0; i <= 80; i++) {
            int r = i / 9;
            int c = i % 9;
            if (board[r][c] != '.' && !isSudokuPosValid(board, r, c)) return false;
        }
        return true;
    }

    //    Sudoku valid
    public static boolean isSudokuPosValid(char[][] board, int r, int c) {
        for (int i = 0; i < 9; i++) {
            if (i != r && board[r][c] == board[i][c]) return false;
        }
        for (int i = 0; i < 9; i++) {
            if (i != c && board[r][c] == board[r][i]) return false;
        }
        int sr = r - (r % 3);
        int sc = c - (c % 3);
        for (int i = sr; i <= sr + 2; i++) {
            for (int j = sc; j <= sc + 2; j++) {
                if (i != r && j != c && board[r][c] == board[i][j]) return false;
            }
        }
        return true;
    }

    public static boolean isSudokuPosValid(char[][] board, int r, int c, char num) {
        for (int i = 0; i < 9; i++) {
            if (i != r && num == board[i][c]) return false;
        }
        for (int i = 0; i < 9; i++) {
            if (i != c && num == board[r][i]) return false;
        }
        int sr = r - (r % 3);
        int sc = c - (c % 3);
        for (int i = sr; i <= sr + 2; i++) {
            for (int j = sc; j <= sc + 2; j++) {
                if (i != r && j != c && num == board[i][j]) return false;
            }
        }
        return true;
    }

    public static boolean sudokuSolve(char[][] board, int n) {
        if (n == 81) return true;
        int r = n / 9;
        int c = n % 9;
        if (board[r][c] != '.') return sudokuSolve(board, n + 1);
        for (int i = 1; i <= 9; i++) {
            char num = (char) i;
            if (isSudokuPosValid(board, r, c, num)) {
                board[r][c] = num;
                if (sudokuSolve(board, n + 1)) return true;
                board[r][c] = '.';
            }
        }
        return false;
    }
}
