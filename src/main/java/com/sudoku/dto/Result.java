package com.sudoku.dto;

/**
 * Result DTO.
 *
 * Created by kumar.ve.la on 17.12.2016.
 */
public class Result {
    private String solution;

    /**
     * Default constructor.
     *
     */
    public Result() {}
    /**
     * Constructor.
     *
     * @param solution to set
     */
    public Result(String solution) {
        this.solution = solution;
    }

    /**
     *
     * @return solution to return
     */
    public String getSolution() {
        return solution;
    }

    /**
     * set board
     * @param solution to set
     */
    public void setSolution(String solution) {
        this.solution = solution;
    }
}
