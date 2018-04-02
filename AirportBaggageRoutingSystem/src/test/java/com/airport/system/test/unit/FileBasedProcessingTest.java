package com.airport.system.test.unit;

import static org.junit.Assert.assertThat;

import java.io.File;

import org.hamcrest.CoreMatchers;
import org.hamcrest.core.IsNull;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.airport.system.exceptions.AirportBaggageSystemException;
import com.airport.system.model.AirportBaggageRoutingModel;
import com.airport.system.util.FileBasedProcessing;
import com.airport.system.util.InputProcessingSystem;

/**
 * This class unit test the FileBasedProcessing class
 * @author avishekbasak
 *
 */
public class FileBasedProcessingTest {
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Test
	/**
	 * The method test processInput method for no file set
	 */
	public void test_processInput_NullInput() throws AirportBaggageSystemException {
		//Given
		thrown.expect(AirportBaggageSystemException.class);
        thrown.expectMessage(CoreMatchers.is("No file present to process"));
		//When
		InputProcessingSystem inputProcessing = new FileBasedProcessing();
		inputProcessing.processInput();
        //Then
		
	}
	
	@Test
	/**
	 * The method test processInput method for no file set
	 */
	public void test_processInput_UnknownFileInput() throws AirportBaggageSystemException {
		//Given
		thrown.expect(AirportBaggageSystemException.class);
        thrown.expectMessage(CoreMatchers.containsString("Unable to find exception: "));
		//When
		InputProcessingSystem inputProcessing = new FileBasedProcessing();
		File file = new File("a.txt");
		inputProcessing.setFile(file);
		inputProcessing.processInput();
        //Then
	}
	
	@Test
	/**
	 * The method test processInput method for valid file
	 */
	public void test_processInput_ValidInput() throws AirportBaggageSystemException {
		//Given
		InputProcessingSystem inputProcessing = new FileBasedProcessing();
		File file = new File("src/test/resources/data/TestParsingInput.txt");
		inputProcessing.setFile(file);
		//When
		AirportBaggageRoutingModel airportBaggaeRoutingModel = inputProcessing.processInput();
        //Then
		assertThat(airportBaggaeRoutingModel, CoreMatchers.is(IsNull.notNullValue()));
		assertThat(airportBaggaeRoutingModel.getBags(), CoreMatchers.is(IsNull.notNullValue()));
		assertThat(airportBaggaeRoutingModel.getBags().size(), CoreMatchers.is(CoreMatchers.equalTo(2)));
		assertThat(airportBaggaeRoutingModel.getDepartureMap(), CoreMatchers.is(IsNull.notNullValue()));
		assertThat(airportBaggaeRoutingModel.getDepartureMap().size(), CoreMatchers.is(CoreMatchers.equalTo(2)));
		assertThat(airportBaggaeRoutingModel.getGateMap(), CoreMatchers.is(IsNull.notNullValue()));
		assertThat(airportBaggaeRoutingModel.getGateMap().size(), CoreMatchers.is(CoreMatchers.equalTo(3)));
		assertThat(airportBaggaeRoutingModel.getGateMap().get("A5"), CoreMatchers.is(IsNull.notNullValue()));
		assertThat(airportBaggaeRoutingModel.getGateMap().get("A5").getNeighbors(), CoreMatchers.is(IsNull.notNullValue()));
		assertThat(airportBaggaeRoutingModel.getGateMap().get("A5").getNeighbors().size(), CoreMatchers.is(CoreMatchers.equalTo(2)));
	}
}
