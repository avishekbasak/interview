package com.airport.system.test.unit;

import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.airport.system.main.Application;

/**
 * This class unit test the application
 * @author avishekbasak
 *
 */
public class ApplicationShortestPathTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void restoreStreams() {
	    System.setOut(System.out);
	    System.setErr(System.err);
	}
	
	
	
	@Test
	/**
	 * The method test main method for null input
	 */
	public void test_Main_NullInput() {
		//Given
		String expectedResponse="Currently the application support file based input\n";
		//When
        Application.main(null);
        String output=errContent.toString();
        //Then
        assertThat(output, CoreMatchers.is(CoreMatchers.equalTo(expectedResponse)));
	}
	
	@Test
	/**
	 * The method test main method for no input file provided
	 */
	public void test_Main_NoFileProvided() {
		//Given
		String expectedResponse="Currently the application support file based input\n";
		//When
        Application.main(new String [] {});
        String output=errContent.toString();
        //Then
        assertThat(output, CoreMatchers.is(CoreMatchers.equalTo(expectedResponse)));
	}
	
	@Test
	/**
	 * The method test main method for valid input and determines if it can successfully rendered the optimal path
	 */
	public void test_Main_ValidShortstPath() {
		//Given
		String expectedResponse="0001 Concourse_A_Ticketing A5 A1 : 11\n" +
                "0002 A5 A1 A2 A3 A4 : 9\n" +
                "0003 A2 A1 : 1\n" +
                "0004 A8 A9 A10 A5 : 6\n" +
                "0005 A7 A8 A9 A10 A5 BaggageClaim : 12\n";
		//When
        Application.main(new String [] {"src/test/resources/data/Input.txt"});
        String output=outContent.toString();
        //Then
        assertThat(output, CoreMatchers.is(CoreMatchers.equalTo(expectedResponse)));
	}
	
	
}
