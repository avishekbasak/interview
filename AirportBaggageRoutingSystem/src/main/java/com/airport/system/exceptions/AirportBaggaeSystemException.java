package com.airport.system.exceptions;

/**
* <h1>AirportBaggaeSystemException!</h1>
* Custom Exception to handle all the exception related to AirportBaggageSystem Application
*
* @author  Avishek Basak
* @version 1.0
*/
public class AirportBaggaeSystemException extends Exception {

	private static final long serialVersionUID = 1L;
	
	//error code
	private String errorCode;
	//error message
	private String errorMessage;
	
	public AirportBaggaeSystemException(String errorCode,String errorMessage) {
		super(errorMessage);
	}
	public AirportBaggaeSystemException(String errorMessage) {
		super(errorMessage);
	}
	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
