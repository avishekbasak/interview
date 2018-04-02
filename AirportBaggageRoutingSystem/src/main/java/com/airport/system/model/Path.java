package com.airport.system.model;

/**
* <h1>Path!</h1>
* Holds all the information of the two connected Gates and their corresponding travel time
*
* @author  Avishek Basak
* @version 1.0
*/
public class Path {
	
	private Gate sourceGate;
	private Gate destinationGate;
	private int travelTime;
	
	/**
	 * @param sourceGate
	 * @param destinationGate
	 * @param travelTime
	 */
	public Path(Gate sourceGate, Gate destinationGate, int travelTime) {
		this.sourceGate = sourceGate;
		this.destinationGate = destinationGate;
		this.travelTime = travelTime;
	}
	
	/**
	 * @return the sourceGate
	 */
	public Gate getSourceGate() {
		return sourceGate;
	}
	/**
	 * @param sourceGate the sourceGate to set
	 */
	public void setSourceGate(Gate sourceGate) {
		this.sourceGate = sourceGate;
	}
	/**
	 * @return the destinationGate
	 */
	public Gate getDestinationGate() {
		return destinationGate;
	}
	/**
	 * @param destinationGate the destinationGate to set
	 */
	public void setDestinationGate(Gate destinationGate) {
		this.destinationGate = destinationGate;
	}
	/**
	 * @return the timeTaken
	 */
	public int getTravelTime() {
		return travelTime;
	}
	/**
	 * @param travelTime the timeTaken to set
	 */
	public void setTravelTime(int travelTime) {
		this.travelTime = travelTime;
	}
	
	
}
