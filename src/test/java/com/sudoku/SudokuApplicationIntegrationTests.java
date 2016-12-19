package com.sudoku;

import com.sudoku.controller.SudokuController;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * MockMvc test cases for Sudoku solver api.
 * <p>
 * @author Veenit Kumar
 * @Since 10-11-2016
 * </p>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SudokuController.class })
@ComponentScan("com.sudoku")
@EnableWebMvc
@WebAppConfiguration
public class SudokuApplicationIntegrationTests {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	/**
	 * Initialises of the fields before test cast running.
	 * 
	 * @throws Exception - exception to be thrown
	 */
	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * Should return status code 200.
	 * 
	 * @throws Exception exception thrown
	 */
	@Test
	public void testControllerStatusOk() throws Exception {
		String board = "x,x,x,2,6,x,7,x,1,6,8,x,x,7,x,x,9,x,1,9,x,x,x,4,5,x,x,8,2,x,1,x,x,x,4,x,x,x,4,6,x,2,9,x,x,x,5,x,x,x,3,x,2,8,x,x,9,3,x,x,x,7,4,x,4,8,9,5,x,x,3,6,7,x,3,x,1,8,x,x,x";
		mockMvc.perform(get("/sudokuSolver")
				.param("board", board)).andExpect(status().isOk());
	}

	@Test
	public void testControllerTestResults() throws Exception {
		String resultBoard = "4,3,5,2,6,9,7,8,1,6,8,2,5,7,1,4,9,3,1,9,7,8,3,4,5,6,2,8,2,6,1,9,5,3,4,7,3,7,4,6,8,2,9,1,5,9,5,1,7,4,3,6,2,8,5,1,9,3,2,6,8,7,4,2,4,8,9,5,7,1,3,6,7,6,3,4,1,8,2,5,9";
		String board = "x,x,x,2,6,x,7,x,1,6,8,x,x,7,x,x,9,x,1,9,x,x,x,4,5,x,x,8,2,x,1,x,x,x,4,x,x,x,4,6,x,2,9,x,x,x,5,x,x,x,3,x,2,8,x,x,9,3,x,x,x,7,4,x,4,8,9,5,x,x,3,6,7,x,3,x,1,8,x,x,x";
		mockMvc.perform(get("/sudokuSolver")
				.param("board", board))
				.andExpect(jsonPath("$.solution", Matchers.equalTo(resultBoard)));
	}

}
