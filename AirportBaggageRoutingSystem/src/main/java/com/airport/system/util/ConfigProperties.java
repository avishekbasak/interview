package com.airport.system.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.LogManager;

import com.airport.system.constants.Constants;
import com.airport.system.main.Application;

/**
 * This class load Log properties for logging.
 * @author avishekbasak
 *
 */
public class ConfigProperties {
	
	private static ConfigProperties configProperties = null;
	
	private Properties configProp = null;
	
	private ConfigProperties() {
		loadLogProperties() ;
		//configProp = loadProperties();
	}

	/**
	 * The method loads the logging properties and set the LogManager
	 */
	private void loadLogProperties() {
		try {
			LogManager.getLogManager().readConfiguration(ConfigProperties.class.getResourceAsStream("/logging.properties"));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	/*private Properties loadProperties() {

		InputStream input = null;
		configProp = new Properties();
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(Constants.CONFIG_PROPERTIES).getFile());
			input = new FileInputStream(file);

			// load a properties file
			configProp.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return configProp;
	}*/

	public static ConfigProperties getInstance() {
		if (configProperties == null) {
			synchronized (ConfigProperties.class) {
				if (configProperties == null) {
					configProperties = new ConfigProperties();
				}
			}
		}
		return configProperties;
	}
	
	
	/**
	 * @return the configProp
	 */
	/*public Properties getConfigProperties() {
		return configProp;
	}*/
	
	

}
