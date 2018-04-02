package com.airport.system.model;

/**
* <h1>Bag!</h1>
* Holds all the parsed Bag Information: Bag ID, source point and flight Id
*
* @author  Avishek Basak
* @version 1.0
*/
public class Bag {
	
	private String id;
	private String sourcePoint;
	private String flightId;
	
	/**
	 * 
	 */
	public Bag() {
		super();
	}
	
	/**
	 * @param id
	 * @param sourcePoint
	 * @param flightId
	 */
	public Bag(String id, String sourcePoint, String flightId) {
		this.id = id;
		this.sourcePoint = sourcePoint;
		this.flightId = flightId;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the sourceTerminal
	 */
	public String getSourcePoint() {
		return sourcePoint;
	}

	/**
	 * @param sourcePoint the sourceTerminal to set
	 */
	public void setSourcePoint(String sourcePoint) {
		this.sourcePoint = sourcePoint;
	}

	/**
	 * @return the destination
	 */
	public String getFlightId() {
		return flightId;
	}

	/**
	 * @param flightId the destination to set
	 */
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	
	
	
	

}
