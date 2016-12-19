package com.sudoku.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Replaces board values from x to 0 so that can be easily handled while transforming the solution.
 *
 * Created by kumar.ve.la on 17.12.2016.
 */
@Component
public class BoardCreator {
    private Logger logger = LoggerFactory.getLogger(BoardCreator.class);

    /**
     * Create board by replacing value x with 0.
     *
     * @param board board sent via controller.
     * @return converted board.
     */
    public int[][] createBoard(String board){
        String[] splitBoard = board.split(",");
        int[][] boardArray = new int[9][9];
        int index = 0;
        for(int i = 0; i<9; i++){
            for(int j = 0; j<9; j++){
                String value = splitBoard[index];
                boardArray[i][j]  = value.equalsIgnoreCase("x") ? 0 : Integer.parseInt(value);
                index++;
            }
        }
        return boardArray;
    }
}
