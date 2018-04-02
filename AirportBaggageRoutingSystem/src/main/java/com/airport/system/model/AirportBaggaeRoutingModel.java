package com.airport.system.model;

import java.util.List;
import java.util.Map;

/**
* <h1>AirportBaggaeRoutingModel!</h1>
* Holds all the parsed input information 
* Departure map: Flight id to corresponding Departure object
* List of bags: All the Baggage Information for which optimal path needs to be determined
* Gate Map: Gate Id to corresponding gate object.
*
* @author  Avishek Basak
* @version 1.0
*/
public class AirportBaggaeRoutingModel {
	
	private Map<String,FlightDepartureModel> departureMap = null;
	private List<Bag> bags = null;
	private Map<String,Gate> gateMap = null;
	/**
	 * @return the departureMap
	 */
	public Map<String, FlightDepartureModel> getDepartureMap() {
		return departureMap;
	}
	/**
	 * @param departureMap the departureMap to set
	 */
	public void setDepartureMap(Map<String, FlightDepartureModel> departureMap) {
		this.departureMap = departureMap;
	}
	/**
	 * @return the bags
	 */
	public List<Bag> getBags() {
		return bags;
	}
	/**
	 * @param bags the bags to set
	 */
	public void setBags(List<Bag> bags) {
		this.bags = bags;
	}
	/**
	 * @return the gateMap
	 */
	public Map<String, Gate> getGateMap() {
		return gateMap;
	}
	/**
	 * @param gateMap the gateMap to set
	 */
	public void setGateMap(Map<String, Gate> gateMap) {
		this.gateMap = gateMap;
	}
	
	

}
