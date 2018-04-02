package com.airport.system.model;

import java.util.ArrayList;
import java.util.List;

/**
* <h1>Gate!</h1>
* Holds all the parsed Gate Information: gate Id and its neighbors
* Implements Comparable interface to sort gates based on total travel Time
*
* @author  Avishek Basak
* @version 1.0
*/
public class Gate implements Comparable<Gate> {
	
	private String gateId;
	private List<Path> neighbors;
	private Gate predecessor;
	private int totalTravelTimeTaken = Integer.MAX_VALUE;
	/**
	 * @return the gateId
	 */
	public String getGateId() {
		return gateId;
	}
	/**
	 * @param gateId the gateId to set
	 */
	public void setGateId(String gateId) {
		this.gateId = gateId;
	}
	/**
	 * @return the neighbors
	 */
	public List<Path> getNeighbors() {
		return neighbors;
	}
	/**
	 * @param neighbors the neighbors to set
	 */
	public void setNeighbors(List<Path> neighbors) {
		this.neighbors = neighbors;
	}
	/**
	 * @return the predecessor
	 */
	public Gate getPredecessor() {
		return predecessor;
	}
	/**
	 * @param predecessor the predecessor to set
	 */
	public void setPredecessor(Gate predecessor) {
		this.predecessor = predecessor;
	}
	/**
	 * @return the timeTaken
	 */
	public int getTotalTravelTimeTaken() {
		return totalTravelTimeTaken;
	}
	/**
	 * @param totalTravelTimeTaken the timeTaken to set
	 */
	public void setTotalTravelTimeTaken(int totalTravelTimeTaken) {
		this.totalTravelTimeTaken = totalTravelTimeTaken;
	}
	/**
	 * @param gateId
	 */
	public Gate(String gateId) {
		this.gateId = gateId;
		this.neighbors = new ArrayList<>();
	}
	
	public void addNeighbour(Path path) {
		this.neighbors.add(path);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gateId == null) ? 0 : gateId.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gate other = (Gate) obj;
		if (gateId == null) {
			if (other.gateId != null)
				return false;
		} else if (!gateId.equals(other.gateId))
			return false;
		return true;
	}
	@Override
	public int compareTo(Gate o) {
		return Integer.compare(this.totalTravelTimeTaken, o.totalTravelTimeTaken);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return gateId;
	}
	
	

}
