package com.sudoku.controller;

import com.sudoku.dto.Result;
import com.sudoku.exception.UnsolvedException;
import com.sudoku.helper.ResultBuilder;
import com.sudoku.logging.LoggingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;


/**
 * This is the controller or entry point for Sudoku solver API.
 *
 * Created by kumar.ve.la on 17.12.2016.
 */
@RestController
public class SudokuController {
    private static final Logger logger = LoggerFactory.getLogger(SudokuController.class);

    @Autowired
    private ResultBuilder resultBuilder;

    /**
     * Handles Sudoku puzzle request. It takes on parameter as input and returns Result containing result or error.
     *
     * @param board - Sudoku board to solve.
     * @return Result - Solved result.
     */
    @RequestMapping(path = "/sudokuSolver", method = RequestMethod.GET)
    public Result solve(String board) throws UnsolvedException {
        validateInput(board);
        LoggingUtil.logDebug(logger, "Input::" + board);
        return resultBuilder.processSolution(board);
    }

    /**
     * Validates the input and throws ValidationException.
     *
     * @param board - Sudoku board value
     */
    private void validateInput(String board) {

        if(StringUtils.isEmpty(board) || board.length() != 161)
            throw new ValidationException();
    }
}
