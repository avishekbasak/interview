package com.airport.system.helper.log;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.airport.system.constants.Constants;
/**
* <h1>LogHelper</h1>
* Standardize the log to maintain a fixed format
* Format: <class_name>~<method_name>~<message>
*
* @author  Avishek Basak
* @version 1.0
*/
public class LogHelper {
	
	/**
	   * This is the method used to log info level log
	   * @param logger logger class object used for logging.
	   * @param className class name from where method is called.
	   * @param methodName method name from where method is called.
	   * @param message Message to be logged.
	   * @return Nothing.
	   */
	public static void logInfo(Logger logger,String className, String methodName, String message) {
		logger.info(className+Constants.TILT+methodName+Constants.TILT+message);
	}
	
	/**
	   * This is the method used to log fine level log
	   * @param logger logger class object used for logging.
	   * @param className class name from where method is called.
	   * @param methodName method name from where method is called.
	   * @param message Message to be logged.
	   * @return Nothing.
	   */
	public static void logDebug(Logger logger,String className, String methodName, String message) {
		logger.fine(className+Constants.TILT+methodName+Constants.TILT+message);
	}
	
	/**
	   * This is the method used to log severe level log
	   * @param logger logger class object used for logging.
	   * @param className class name from where method is called.
	   * @param methodName method name from where method is called.
	   * @param message Message to be logged.
	   * @param throwable Exception to be logged
	   * @return Nothing.
	   */
	public static void logError(Logger logger,String className, String methodName, String message, Throwable throwable) {
		logger.log(Level.SEVERE,className+Constants.TILT+methodName+Constants.TILT+message,throwable);
	}
	
	/**
	   * This is the method used to log severe level log
	   * @param logger logger class object used for logging.
	   * @param className class name from where method is called.
	   * @param methodName method name from where method is called.
	   * @param message Message to be logged.
	   * @return Nothing.
	   */
	public static void logError(Logger logger,String className, String methodName, String message) {
		logger.log(Level.SEVERE,className+Constants.TILT+methodName+Constants.TILT+message);
	}
	
	/**
	   * This is the method used to log performance log
	   * @param logger logger class object used for logging.
	   * @param level Log level to be logged.
	   * @param className class name from where method is called.
	   * @param methodName method name from where method is called.
	   * @param message Message to be logged.
	   * @param timeTaken time taken to execute code block
	   * @return Nothing.
	   */
	public static void perfLog(Logger logger,Level level,String className, String methodName, String message, long timeTaken) {
		logger.log(level,className+Constants.TILT+methodName+Constants.TILT+message+Constants.TILT+timeTaken+"ms");
	}
}
