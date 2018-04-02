package com.airport.system.helper;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.airport.system.constants.Constants;
import com.airport.system.exceptions.AirportBaggageSystemException;
import com.airport.system.helper.log.LogHelper;
import com.airport.system.main.Application;
import com.airport.system.model.AirportBaggageRoutingModel;
import com.airport.system.model.Bag;
import com.airport.system.model.Gate;
import com.airport.system.shortpath.BaggageRoutingSystem;
import com.airport.system.shortpath.impl.BaggageRoutingUsingDijkstra;
import com.airport.system.util.FileBasedProcessing;
import com.airport.system.util.InputProcessingSystem;
/**
* <h1>AirportBaggageSystem</h1>
* Read the input data and process to get the optimal path
*
* @author  Avishek Basak
* @version 1.0
*/
public class AirportBaggageSystem {

	private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
	private static final Logger PERFLOGGER = Logger.getLogger(Constants.PERF_LOG_CLASS);
	
	private InputProcessingSystem inputProcessingSystem;

	/**
	   * This method call the helper methods to parse the input and find the optimal path
	   * @param args This is the string array and the 1st element of the array is the file name
	   * @return Nothing.
	   */
	public void processInput(String[] args){
		AirportBaggageRoutingModel inputData = null;
		if (args!=null && args.length > 0) {
			long startTime = System.currentTimeMillis();
			try {
				inputData = processInputFile(args[0]);
			} catch (AirportBaggageSystemException ex) {
				System.err.println("Unable to process request:: " +ex.getMessage());
			} finally {
				long endTime = System.currentTimeMillis();
				LogHelper.perfLog(PERFLOGGER, Level.INFO, this.getClass().getName(),"processInput(String[] args)", "Time taken to process input",endTime-startTime);
			}
		}else {
			System.err.println(Constants.INPUT_NOT_SUPPORTED);
		}
		if(inputData != null) {
			long startTime = System.currentTimeMillis();
			try {
				processInputData(inputData);
			} finally {
				long endTime = System.currentTimeMillis();
				LogHelper.perfLog(PERFLOGGER, Level.INFO, this.getClass().getName(),"processInput(String[] args)", "Time taken to find optimal path",endTime-startTime);
			}
		}
	}

	/**
	   * This method is used to parse the input based on File Based input
	   * @param fileName File name to be processed
	   * @return AirportBaggaeRoutingModel The data structure holds parsed data from input file.
	   * @exception AirportBaggageSystemException Input or processing error.
	   */
	private AirportBaggageRoutingModel processInputFile(String fileName) throws AirportBaggageSystemException {
		AirportBaggageRoutingModel inputData = null;
		LogHelper.logInfo(LOGGER, this.getClass().getName(), "processInputFile(String fileName)" , "File:: "+fileName);
		if(fileName!=null && fileName.trim().length()!=0){
			File inputDataFile = new File(fileName.trim());
			inputProcessingSystem = new FileBasedProcessing();
			inputProcessingSystem.setFile(inputDataFile);
			try {
				inputData = inputProcessingSystem.processInput();
			} catch (AirportBaggageSystemException ex) {
				LogHelper.logError(LOGGER, this.getClass().getName(), "processInputFile(String fileName)" , "File Not Found Exception", ex);
				throw ex;
			}
			
		}else {
			LogHelper.logError(LOGGER, this.getClass().getName(), "processInputFile(String fileName)" , "File Name is either empty or not provided");
			throw new AirportBaggageSystemException("File Name is either empty or not provided");
		}
		return inputData;
	}

	/**
	   * This method is used to parse the input based on File Based input
	   * @param inputData The data structure holds parsed data from input file
	   * @return Nothing
	   */
	private void processInputData(AirportBaggageRoutingModel inputData) {
		BaggageRoutingSystem baggageRoutingSystem = new BaggageRoutingUsingDijkstra();
		for (Bag baggageModel : inputData.getBags()) {
			Gate sourceGate = inputData.getGateMap().get(baggageModel.getSourcePoint());

			baggageRoutingSystem.computeShortestPath(sourceGate);
			LogHelper.logInfo(LOGGER, this.getClass().getName(), "processInputFile(String fileName)" , "Destination:: "+baggageModel.getFlightId());
			//if FlightID is equal to Arrival, it means baggage needs to be routed to BaggageClaim sector
			if (Constants.ARRIVAL_TEXT.equalsIgnoreCase(baggageModel.getFlightId())) {
				List<Gate> gates = baggageRoutingSystem
						.getShortestPath(inputData.getGateMap().get(Constants.BAGGAGECLAIM_TEXT));
				System.out.println(baggageRoutingSystem.getStringFormat(baggageModel.getId(), gates));
			} else {
				List<Gate> gates = baggageRoutingSystem.getShortestPath(inputData.getGateMap()
						.get(inputData.getDepartureMap().get(baggageModel.getFlightId()).getFlightGate()));
				System.out.println(baggageRoutingSystem.getStringFormat(baggageModel.getId(), gates));
			}
			baggageRoutingSystem.resetInfo(inputData.getGateMap());
		}
	}

}
