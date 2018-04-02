package com.airport.system.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
* <h1>FlightDepartureModel!</h1>
* Holds all the parsed Departure Information: flight No, flight gate, destination and flight time
*
* @author  Avishek Basak
* @version 1.0
*/
public class FlightDepartureModel {
	
	private String flightNo;
	private String flightGate;
	private String destination;
	private Calendar flightTime;
	/**
	 * @param flightNo
	 * @param flightGate
	 * @param destination
	 * @param input
	 */
	public FlightDepartureModel(String flightNo, String flightGate, String destination, String departureTime) {
		this.flightNo = flightNo;
		this.flightGate = flightGate;
		this.destination = destination;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		try {
			calendar.setTime(simpleDateFormat.parse(departureTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.flightTime = calendar;
	}
	/**
	 * @return the flightNo
	 */
	public String getFlightNo() {
		return flightNo;
	}
	/**
	 * @param flightNo the flightNo to set
	 */
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	/**
	 * @return the flightGate
	 */
	public String getFlightGate() {
		return flightGate;
	}
	/**
	 * @param flightGate the flightGate to set
	 */
	public void setFlightGate(String flightGate) {
		this.flightGate = flightGate;
	}
	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}
	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	/**
	 * @return the flightTime
	 */
	public Calendar getFlightTime() {
		return flightTime;
	}
	/**
	 * @param flightTime the flightTime to set
	 */
	public void setFlightTime(Calendar flightTime) {
		this.flightTime = flightTime;
	}
	
	
}
