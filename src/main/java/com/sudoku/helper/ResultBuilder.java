package com.sudoku.helper;

import com.sudoku.controller.SudokuController;
import com.sudoku.dto.Result;
import com.sudoku.exception.UnsolvedException;
import com.sudoku.logging.LoggingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Responsible to call the responsible classes to create board and provide solution.
 *
 * Created by kumar.ve.la on 17.12.2016.
 */
@Component
public class ResultBuilder {
    private static final Logger logger = LoggerFactory.getLogger(SudokuController.class);

    @Autowired
    private SudokuSolver sudokuSolver;

    @Autowired
    private BoardCreator boardCreator;

    /**
     * Pass the information to {@link BoardCreator} class to create board and process transformed board to
     * {@link SudokuSolver} Class to get the solution.
     *
     * <p>
     * @param strBoard board to be transformed
     * @return processed result.
     * @throws UnsolvedException in case could not find the solution for the board.
     * </p>
     */
    public Result processSolution(String strBoard) throws UnsolvedException {
        int[][] board = boardCreator.createBoard(strBoard);
        boolean isSolved = sudokuSolver.solve(board);
        if(isSolved){
            return createResult(board);
        }
        throw new UnsolvedException("Cannot be completed");
    }

    /**
     * Converts double dimension board to a comma separated string.
     *
     * @param board information.
     * @return Result object.
     */
    private Result createResult(int[][] board){
        String value = Arrays.stream(board)
                .map(a -> StringUtils.arrayToCommaDelimitedString(IntStream.of( a ).boxed().toArray( Integer[]::new )))
                .collect(Collectors.joining(","));
        LoggingUtil.logDebug(logger, "Result::" + value);
        return new Result(value);
    }
}
