package com.sudoku.helper;

import org.springframework.stereotype.Component;

/**
 * Solves sudoku board.
 *
 * @author Veenit Kumar
 * @since 17-12-2016
 */
@Component
public class SudokuSolver {
    /**
     * It sets fills status array places to 2 where board already contains values.
     * Calls Solve method by passing status array and board with starting x and y position.
     * x - row and y -column
     *
     * @param board -board to be solved
     * @return boolean value if board is solved or not.
     */
    public boolean solve(int[][] board){
        int[][] status = new int[board.length][board[0].length];
        for(int i = 0; i<9; i++){
            for(int j = 0; j<9; j++){
                status[i][j] = board[i][j]>0?2:0;
            }
        }
        return solve(board, status, 0, 0);
    }

    /**
     *
     * @param board unsolved board
     * @param status status array containing already defined value as 2.
     * @param x row
     * @param y column
     * @return true or false.
     */
    private boolean solve(int[][] board, int[][] status, int x, int y){
        if(x==9){
            int count = setRowStatus(status);
            return (count == 81);
        }
        if(status[x][y] >= 1){ // If already set then move to the next cell.
            return moveNext(board, status, x, y);
        }else   //check row, column and 3*3 box to decide all possible values.
        {
            boolean[] used = new boolean[9];
            checkRow(board[x], status[x], used);
            checkColumn(board, status, y, used);
            checkBlock(board, status, x, y, used);

            for(int i = 0; i<used.length; i++){
                if(!used[i]){
                    status[x][y] = 1;
                    board[x][y] = i+1;
                    int nextX = x;
                    int nextY = y + 1;
                    if(nextY == 9){
                        nextX = x +1; nextY = 0;
                    }
                    if(solve(board, status, nextX, nextY))
                        return true;

                    for(int m = 0; m<9; m++){
                        for(int n = 0; n<9; n++){
                            if(m>x || (m==x&&n>y)){
                                if(status[m][n]==1){
                                    status[m][n] = 0;
                                    board[m][n] = 0;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     *  counts no of solved blocks.
     *
     * @param status statsu array
     * @return no of solved blocks.
     */
    private int setRowStatus(int[][] status) {
        int count = 0;
        for(int i = 0; i<9; i++){
            for(int j = 0; j<9; j++){
                count += status[i][j] > 0?1:0;
            }
        }
        return count;
    }

    /**
     * Moves to the next block to fill with value.
     *
     * @param board board to solve
     * @param status status array
     * @param x row
     * @param y column
     * @return true or false
     */
    private boolean moveNext(int[][] board, int[][] status, int x, int y) {
        int nextX = x;
        int nextY = y + 1;
        if(nextY == 9){
            nextX = x +1; nextY = 0;
        }
        return solve(board, status, nextX, nextY);
    }

    /**
     * set true in case 3*3 box has been set already.
     *
     * @param board
     * @param status
     * @param x
     * @param y
     * @param used
     */
    private void checkBlock(int[][] board, int[][] status, int x, int y, boolean[] used) {
        for(int i=x-(x%3); i<x-(x%3)+3; i++){
            for(int j=y-(y%3); j<y-(y%3)+3; j++){
                if(status[i][j] >= 1){
                    used[board[i][j]-1] = true;
                }

            }
        }
    }

    /**
     * Set true in case column has been set already.
     *
     * @param board
     * @param status
     * @param y
     * @param used
     */
    private void checkColumn(int[][] board, int[][] status, int y, boolean[] used) {

        for(int i = 0; i<9; i++){
            if(status[i][y] >= 1){
                used[board[i][y]-1] = true;
            }
        }
    }

    /**
     * Set true in case row has been set already.
     *
     * @param ints  row array
     * @param statu status
     * @param used row status
     */
    private void checkRow(int[] ints, int[] statu, boolean[] used) {
        for(int i = 0; i<9; i++){
            if(statu[i] >= 1){
                used[ints[i]-1] = true;
            }
        }
    }

}
