package com.airport.system.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.airport.system.constants.Constants;
import com.airport.system.exceptions.AirportBaggageSystemException;
import com.airport.system.helper.log.LogHelper;
import com.airport.system.model.Bag;
import com.airport.system.model.FlightDepartureModel;
import com.airport.system.model.Gate;
import com.airport.system.model.AirportBaggageRoutingModel;
import com.airport.system.model.Path;

/**
* <h1>FileBasedProcessing</h1>
* This class parses the input file
*
* @author  Avishek Basak
* @version 1.0
*/
public class FileBasedProcessing implements InputProcessingSystem {

	private File file;
	
	private String lastLineRead;

	private static final Logger PERFLOGGER = Logger.getLogger(Constants.PERF_LOG_CLASS);

	/*
	 * (non-Javadoc)
	 * @see com.airport.system.util.InputProcessingSystem#processInput()
	 */
	public AirportBaggageRoutingModel processInput() throws AirportBaggageSystemException {

		AirportBaggageRoutingModel inputData = new AirportBaggageRoutingModel();
		int section = 1;
		long startTime = System.currentTimeMillis();
		if(file!=null) {
			try (Scanner lineScanner = new Scanner(file)) {
				lastLineRead = lineScanner.nextLine();
				//check if the file starts with "# Section:"
				//if not throw exception
				if (!lastLineRead.startsWith(Constants.INPUT_SECTION_PREFIX)) {
					throw new AirportBaggageSystemException(Constants.INVALID_INPUT_MESSAGE);
				}
				/*
				 * iterate through the file to cover all the three section:
				 * 1. Conveyor Network
				 * 2. Departure schedule
				 * 3. Bags
				 * in any order
				 */
				while (!(section > 3)) {
					if (Constants.CONVEYER_NETWORK_SECTION.equalsIgnoreCase(lastLineRead)) {
						inputData.setGateMap(constructConveyerSystem(lineScanner, section));
						section++;
					}
					if (Constants.DEPARTURES_SECTION.equalsIgnoreCase(lastLineRead)) {
						inputData.setDepartureMap(constructDepartureMap(lineScanner, section));
						section++;
					}
					if (Constants.BAGS_SECTION.equalsIgnoreCase(lastLineRead)) {
						inputData.setBags(constructBaggageModel(lineScanner, section));
						section++;
					}
				}

			}catch(FileNotFoundException ex) {
				throw new AirportBaggageSystemException(
						"Unable to find exception: "+ ex.getMessage());
			}finally {
				long endTime = System.currentTimeMillis();
				LogHelper.perfLog(PERFLOGGER, Level.INFO, this.getClass().getName(),"processInput()", "Time taken to process",endTime-startTime);
			}
		}else {
			throw new AirportBaggageSystemException(
					"No file present to process");
		}
		return inputData;
	}

	/**
	 * the method scans the input for Baggage Section. Based on the section id it determines whether to check for last line or next section for the end of input
	 * 
	 * @param lineScanner Scanner to scan through the file
	 * @param section determine what section of the file
	 * @return List of Bags 
	 * @throws AirportBaggageSystemException Input or processing error
	 */
	private List<Bag> constructBaggageModel(Scanner lineScanner, int section) throws AirportBaggageSystemException {
		List<Bag> bags = new ArrayList<>();
		if (section != 3) {
			// Check for the next section to determine end of data
			String nextLine = lineScanner.nextLine();
			while (!nextLine.startsWith(Constants.INPUT_SECTION_PREFIX)) {
				constructBaggageModel(nextLine, bags);
				nextLine = lineScanner.nextLine();
			}
			lastLineRead = nextLine;
		} else {
			// Check for the end of file to determine end of data
			while (lineScanner.hasNext()) {
				String nextLine = lineScanner.nextLine();
				constructBaggageModel(nextLine, bags);
			}
		}
		return bags;
	}
	
	/**
	 * the method takes each line and split it to create Bag object.
	 * @param nextLine each text line to be processed in the format <bag_number> <entry_point> <flight_id>
	 * @param bags list of bags to store the created Bag object
	 * @throws AirportBaggageSystemException Input or processing error
	 */
	private void constructBaggageModel(String nextLine, List<Bag> bags) throws AirportBaggageSystemException {
		String[] input = nextLine.trim().split("\\s+");
		if (input.length >= 3) {
			Bag bag = new Bag(input[0], input[1], input[2]);
			bags.add(bag);
		}else {
			throw new AirportBaggageSystemException(Constants.INVALID_INPUT_MESSAGE);
		}
	}

	/**
	 * the method scans the input for Departure Section. Based on the section id it determines whether to check for last line or 
	 * next section for the end of input
	 * 
	 * @param lineScanner Scanner to scan through the file
	 * @param section determine what section of the file
	 * @return List of Bags 
	 * @throws AirportBaggageSystemException Input or processing error
	 */
	private Map<String, FlightDepartureModel> constructDepartureMap(Scanner lineScanner, int section) throws AirportBaggageSystemException {
		Map<String, FlightDepartureModel> departureMap = null;

		departureMap = new HashMap<>();
		if (section != 3) {
			// Check for the next section to determine end of data
			String nextLine = lineScanner.nextLine();
			while (!nextLine.startsWith(Constants.INPUT_SECTION_PREFIX)) {
				constructDepartureMap(nextLine, departureMap);
				nextLine = lineScanner.nextLine();
			}
			lastLineRead = nextLine;
		} else {
			// Check for the end of file to determine end of data
			while (lineScanner.hasNext()) {
				String nextLine = lineScanner.nextLine();
				constructDepartureMap(nextLine, departureMap);

			}
		}

		return departureMap;
	}

	/**
	 * The method reads each line and split it to create the Flight Departure object and store the information in map with the flightNo as the key
	 * @param nextLine each text line to be processed in the format <flight_id> <flight_gate> <destination> <flight_time>
	 * @param departureMap store the Flight Departure Information in map with the flightNo as the key
	 * @throws AirportBaggageSystemException
	 */
	private void constructDepartureMap(String nextLine, Map<String, FlightDepartureModel> departureMap) throws AirportBaggageSystemException {
		String[] input = nextLine.trim().split("\\s+");
		if (input.length >= 4) {
			FlightDepartureModel departureModel = new FlightDepartureModel(input[0], input[1], input[2], input[3]);
			departureMap.put(departureModel.getFlightNo(), departureModel);
		} else {
			throw new AirportBaggageSystemException(Constants.INVALID_INPUT_MESSAGE);
		}
	}

	/**
	 * the method scans the input for Conveyor Network Section. Based on the section id it determines whether to check for last line or 
	 * next section for the end of input. This creates the graph representation of conveyer system 
	 * 
	 * @param lineScanner Scanner to scan through the file
	 * @param section determine what section of the file
	 * @return map of gateId and corresponding Gate object 
	 * @throws AirportBaggageSystemException Input or processing error
	 */
	private Map<String, Gate> constructConveyerSystem(Scanner lineScanner, int section) throws AirportBaggageSystemException {
		Map<String, Gate> gateMap = null;
		if (gateMap == null) {
			gateMap = new HashMap<>();
		}
		if (section != 3) {
			// Check for the next section to determine end of data
			String nextLine = lineScanner.nextLine();
			while (!nextLine.startsWith(Constants.INPUT_SECTION_PREFIX)) {
				constructConveyerSystem(nextLine, gateMap);
				nextLine = lineScanner.nextLine();
			}
			lastLineRead = nextLine;
		} else {
			// Check for the end of file to determine end of data
			while (lineScanner.hasNext()) {
				String nextLine = lineScanner.nextLine();
				constructConveyerSystem(nextLine, gateMap);
			}
		}

		return gateMap;

	}
	/**
	 * The method read each line and split it to create each Gate Object and the Path between them and 
	 * store each Gate object in a map corresponding to the GateId
	 * 
	 * @param nextLine each text line to be processed in the format <flight_id> <flight_gate> <destination> <flight_time>
	 * @param gateMap map to store each Gate object to corresponding to gateid.
	 * @throws AirportBaggageSystemException Input or processing error
	 */
	private void constructConveyerSystem(String nextLine, Map<String, Gate> gateMap) throws AirportBaggageSystemException {
		String[] input = nextLine.trim().split("\\s+");
		if (input.length >= 3) {
			try {
				/*
				 * Check if the Gate Object has already been created
				 * if yes fetch the object and store the new path and neighbor
				 * Else create a new object and store the new path and neighbor
				 * xx
				 */
				
				Gate sourceGate = null;
				if (gateMap.get(input[0]) == null) {
					sourceGate = new Gate(input[0]);
				} else {
					sourceGate = gateMap.get(input[0]);
				}
				Gate destGate = null;
				if (gateMap.get(input[1]) == null) {
					destGate = new Gate(input[1]);
				} else {
					destGate = gateMap.get(input[1]);
				}
				//Since the line reflects bidirectional relationship, storing the relationship in reverse way as well
				sourceGate.addNeighbour(new Path(sourceGate, destGate, Integer.valueOf(input[2])));
				destGate.addNeighbour(new Path(destGate, sourceGate, Integer.valueOf(input[2])));
				//Store both the gate in map
				gateMap.put(input[0], sourceGate);
				gateMap.put(input[1], destGate);
			} catch (NumberFormatException e) {
				throw new AirportBaggageSystemException(Constants.INVALID_INPUT_MESSAGE);
			}
		}else {
			throw new AirportBaggageSystemException(Constants.INVALID_INPUT_MESSAGE);
		}

	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see com.airport.system.util.InputProcessingSystem#setFile(java.io.File)
	 */
	public void setFile(File file) {
		this.file = file;

	}

}
