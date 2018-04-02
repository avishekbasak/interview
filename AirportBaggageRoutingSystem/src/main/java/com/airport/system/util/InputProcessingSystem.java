package com.airport.system.util;

import java.io.File;

import com.airport.system.exceptions.AirportBaggaeSystemException;
import com.airport.system.model.AirportBaggaeRoutingModel;
/**
* <h1>InputProcessingSystem</h1>
* This interface imposes the methods that a class needs to implement for parsing the input
*
* @author  Avishek Basak
* @version 1.0
*/
public interface InputProcessingSystem {
	/**
	 * The method sets the file that needs to be parsed
	 * @param file
	 */
	public void setFile(File file);
	
	/**
	 * The method parses the input and creates the object
	 * @return The data structure holds parsed data from input file.
	 * @exception AirportBaggaeSystemException Input or processing error.
	 */
	public AirportBaggaeRoutingModel processInput() throws AirportBaggaeSystemException;

}
