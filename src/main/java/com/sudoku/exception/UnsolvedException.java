package com.sudoku.exception;

/**
 * Created by kumar.ve.la on 17.12.2016.
 */
public class UnsolvedException extends Exception {

    public UnsolvedException(Throwable e) {
        super(e);
    }

    public UnsolvedException(String s) {
        super(s);
    }

    public UnsolvedException(String s, Throwable e) {
        super(s, e);
    }
}
