package com.sudoku.dto;

/**
 *  Error DTO.
 *
 * Created by kumar.ve.la on 17.12.2016.
 */
public class Error {
    private String error;

    public Error(String error) {
        this.error = error;
    }

    /**
     *
     * @return returns error
     */
    public String getError() {
        return error;
    }

    /**
     *
     * @param error set error value
     */
    public void setError(String error) {
        this.error = error;
    }
}
