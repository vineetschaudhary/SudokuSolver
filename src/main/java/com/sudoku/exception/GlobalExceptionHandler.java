package com.sudoku.exception;

import com.sudoku.dto.Error;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

/**
 * Single point for all exceptions. We can define all user defined or container
 * exceptions here.
 * 
 * <p>
 * @author Veenit Kumar
 * @since 17-12-2016
 * </p>
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * Handler for MethodArgumentTypeMismatchException. If method accepts
	 * Integer value and string passed, this case will be handled in this
	 * method.
	 * <p>
	 * Actual error is logged as error in the log file.
	 * </p>
	 * <p>
	 * @param ex
	 *            MethodArgumentTypeMismatchException
	 * @return error information contains user defined message.
	 * </p>
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody
	Error handeMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		log.error(ex.getMessage());
		return getError(String.format("%s field type mismatch.", ex.getParameter().getParameterName()));
	}

	/**
	 * Handler for MissingServletRequestParameterException. If required
	 * parameter is not passed then that case will be handled here.
	 * <p>
	 * Actual error is logged as error in the log file.
	 * </p>
	 * <p>
	 * 
	 * @param ex
	 *            MissingServletRequestParameterException
	 * @return error information contains user defined message.
	 * </p>
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody Error handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
		log.error(ex.getMessage());
		return getError(String.format("Required parameter %s is not present.", ex.getParameterName()));
	}

	/**
	 * Handler for UnsolvedException. In case puzzle could not be solved.
	 * <p>
	 * Actual error is logged as error in the log file.
	 * </p>
	 * <p>
	 *
	 * @param ex
	 *            UnsolvedException
	 * @return error information contains user defined message.
	 * </p>
	 */
	@ExceptionHandler(UnsolvedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody Error handleUnsolvedException(UnsolvedException ex) {
		log.error(ex.getMessage());
		return getError(ex.getMessage());
	}

	/**
	 * Handler for ValidationException. It's called when argument value is not valid.
	 * <p>
	 * Actual error is logged as error in the log file.
	 * </p>
	 * <p>
	 *
	 * @param ex
	 *            ValidationException
	 * @return error information contains user defined message.
	 * </p>
	 */
	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody Error handleValidationException(ValidationException ex) {
		log.error(ex.getMessage());
		return getError("Please check input parameters.");
	}


	/**
	 * Handler for Exception. If no handler found for exception thrown then it
	 * will be handled in this handler method.
	 * 
	 * <p>
	 * Actual error is logged as error in the log file.
	 * </p>
	 * <p>
	 * @param ex
	 *            MissingServletRequestParameterException
	 * @return error information contains user defined message.
	 *         </p>
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody Error handleException(Exception ex) {
		log.error(ex.getMessage());
		return getError("Unexpected error occured.");
	}

	/**
	 * Creates ErrorInfo based on parameters passed.
	 * <p>
	 * @param message user defined message.
	 * @return error info object with values.
	 * </p>
	 */
	private Error getError(String message) {
		return new Error(message);
	}

}
