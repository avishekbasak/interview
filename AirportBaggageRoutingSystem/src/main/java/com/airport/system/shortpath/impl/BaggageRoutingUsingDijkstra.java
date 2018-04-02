/**
 * 
 */
package com.airport.system.shortpath.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.airport.system.constants.Constants;
import com.airport.system.model.Gate;
import com.airport.system.model.Path;
import com.airport.system.shortpath.BaggageRoutingSystem;



/**
 * <h1>BaggaeRoutingUsingDijkstra</h1>
 * The class uses Dijkstra Algorithm to determine the shortest path from source to target.
 * 
 * @author avishekbasak
 *
 */
public class BaggageRoutingUsingDijkstra implements BaggageRoutingSystem {

	/* (non-Javadoc)
	 * @see com.airport.system.model.BaggageRoutingSystem#computeShortestPath(com.airport.system.model.Gate)
	 */
	public void computeShortestPath(Gate gate) {
		
		//set timetaken of source node to 0
		gate.setTotalTravelTimeTaken(0);
		//Initialize priority queue
		PriorityQueue<Gate> priorityQueue = new PriorityQueue<>();
		//Add the source gate to the queue. Gate class implements the Comparable interface to prioritize the Gate with lowest time taken value
		priorityQueue.add(gate);
		//Iterate till the queue is empty
		while(!priorityQueue.isEmpty()){
			// poll the gate with least time taken value
			Gate actualGate = priorityQueue.poll();
			// iterate the neighbors of the polled gate
			for(Path route : actualGate.getNeighbors()){
				//Calculate the new time taken value by adding the source Timetaken value with the
				//time taken to cover the path from source to the neighbor in context
				//If new time taken value is less than the neighbor in context time taken value, then 
				// remove the neighbor from priorotiy queue  and set the new time taken value and add it back to the queue
				Gate  destinationGate = route.getDestinationGate();
				int newTime = actualGate.getTotalTravelTimeTaken() + route.getTravelTime();
				
				if(newTime < destinationGate.getTotalTravelTimeTaken()){
					priorityQueue.remove(destinationGate);
					destinationGate.setTotalTravelTimeTaken(newTime);
					destinationGate.setPredecessor(actualGate);
					priorityQueue.add(destinationGate);
				}
				
			}
		}

	}

	/* (non-Javadoc)
	 * @see com.airport.system.model.BaggageRoutingSystem#getShortestPath(com.airport.system.model.Gate)
	 */
	public List<Gate> getShortestPath(Gate gateTarget) {
		List<Gate> shortestPathToTarget = new ArrayList<>();
		//iterate through the predecessor till no predecessor left
		for(Gate tempGate = gateTarget; tempGate != null ; tempGate = tempGate.getPredecessor()){
			shortestPathToTarget.add(tempGate);
		}
		//reverse the list since the top element is target gate
		Collections.reverse(shortestPathToTarget);
		return shortestPathToTarget;
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see com.airport.system.shortpath.BaggageRoutingSystem#resetInfo(java.util.Map)
	 */
	public void resetInfo(Map<String, Gate> gateMap) {
		//reset the predecessor and time taken value for next computation.
		for(Gate gate:gateMap.values()) {
			gate.setPredecessor(null);
			gate.setTotalTravelTimeTaken(Integer.MAX_VALUE);
		}
	}
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see com.airport.system.shortpath.BaggageRoutingSystem#getStringFormat(java.lang.String, java.util.List)
	 * 
	 * Output format: <Bag_Number> <point_1> <point_2> [<point_3>, …] : <total_travel_time>
	 * 
	 */
	public String getStringFormat(String bagId,List<Gate> gates) {
		StringBuilder format = new StringBuilder();
		format.append(bagId).append(Constants.SPACE);
		Gate lastGate =null;
		for (Gate gate:gates) {
			lastGate = gate;
			format.append(gate).append(Constants.SPACE);
		}
		format.append(Constants.COLON).append(Constants.SPACE).append(lastGate.getTotalTravelTimeTaken());
		return format.toString();
	}

}
