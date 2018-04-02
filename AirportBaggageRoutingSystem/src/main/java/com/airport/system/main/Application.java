package com.airport.system.main;

import com.airport.system.helper.AirportBaggageSystem;
import com.airport.system.util.ConfigProperties;

/**
* <h1>Find optimal path to transfer baggage in Airport!</h1>
* The program implement a system that will route bags to their flights or the proper baggage claim.  
* The input describes the airport conveyer system, the departing flights, and the bags to be routed.  
* The output is the optimal routing to get bags to their destinations.
* <p>
* <b>Note:</b> The program takes a file as input in the following pattern:
* Input: The input consists of several sections.  The beginning of each section is marked by a line starting: “# Section:”
*		Section 1: A weighted bi-directional graph describing the conveyor system.
*					Format: <Node 1> <Node 2> <travel_time>
*       Section 2: Departure list Format:
*					Format:	<flight_id> <flight_gate> <destination> <flight_time>
*       Section 3: Bag list Format:
*                   Format: <bag_number> <entry_point> <flight_id>
*
* @author  Avishek Basak
* @version 1.0
*/
public class Application {

	/**
	   * This is the main method which makes use of addNum method.
	   * @param args 1st element correspond to the file name.
	   * @return Nothing.
	   */
	public static void main(String[] args) {
		
		//Configure logging properties
		ConfigProperties.getInstance();
		//Initiate the application
		AirportBaggageSystem airportBaggageSystem = new AirportBaggageSystem();
		airportBaggageSystem.processInput(args);
	}
}
