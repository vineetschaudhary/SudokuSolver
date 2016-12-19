package com.sudoku.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;

/**
 * Writes the data to the log file.
 * 
 * @author Veenit Kumar
 * @since 17-12-2016
 */
public class LoggingUtil {
	/**
	 * Logs simple string messages.
	 * <p>
	 * @param logger - logger object
	 * @param message - message to be logged.
	 * </p>
	 */
	public static void logDebug(Logger logger, String message){
		if(logger.isDebugEnabled()) logger.debug(message);
	}
	
	/**
	 * Logs object in the form of json.
	 * <p>
	 * @param logger - logger object
	 * @param objectToWrite - object to be written.
	 * </p>
	 */
	public static void logJsonDebug(Logger logger, Object objectToWrite) {
		ObjectMapper mapper = new ObjectMapper();
		if(logger.isDebugEnabled()) {
			try{
				logger.debug(mapper.writeValueAsString(objectToWrite));
			}catch(Exception ex){
				logger.error("Exception while converting object to json while logging. Class name" + objectToWrite.getClass());
			}
		}
	}
	
}
