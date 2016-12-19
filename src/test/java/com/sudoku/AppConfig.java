package com.sudoku;

import com.sudoku.helper.BoardCreator;
import com.sudoku.helper.ResultBuilder;
import com.sudoku.helper.SudokuSolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * Created by kumar.ve.la on 12.12.2016.
 */
@Configuration
public class AppConfig {

    @Bean
    public ResultBuilder getResultBuilder(){
        return new ResultBuilder();
    }

    @Bean
    public BoardCreator getBoardCreator(){
        return new BoardCreator();
    }

    @Bean
    public SudokuSolver getSudokuSolver(){
        return new SudokuSolver();
    }

}
