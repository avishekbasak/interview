package com.airport.system.shortpath;

import java.util.List;
import java.util.Map;

import com.airport.system.model.Gate;

/**
* <h1>BaggageRoutingSystem</h1>
* This interface imposes the methods that a class needs to implement for computing the optimal path for baggage routing
*
* @author  Avishek Basak
* @version 1.0
*/
public interface BaggageRoutingSystem {
	/**
	 * The method computes the shortest distance from source gate
	 * @param gate The start or source gate from which the shortest distance needs to be computed
	 */
	public void computeShortestPath(Gate gate);

	/**
	 * The method finds the shortest path from source gate to the target gate and returns the list of gate in order from source to target
	 * @param gateTarget The target gate to which the shortest distance needs to be computed
	 * @return List<Gate> The list of gate in order from source to target
	 */
	public List<Gate> getShortestPath(Gate gateTarget);
	
	/**
	 * The method resets the shortest time calculated to default value for recalculation
	 * @param gateMap The map of node gateId to corresponding Gate object 
	 */
	public void resetInfo(Map<String, Gate> gateMap);
	
	/**
	 * The method returns the shortest path in predefined format.
	 * Output format: <Bag_Number> <point_1> <point_2> [<point_3>, …] : <total_travel_time>
	 * @param bagId The id of the bag
	 * @param gates The list of gate in order from source to target
	 */
	public String getStringFormat(String bagId, List<Gate> gates);

}
