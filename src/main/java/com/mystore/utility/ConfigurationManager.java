package com.mystore.utility;

import com.mystore.customException.MissingConfigurationException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {

	private static Properties properties = new Properties();

    // Load the properties file once during class initialization
    // Load the properties file once during class initialization
    static {
        try {
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "\\Configuration\\Config.properties");
            properties.load(ip);
        } catch (FileNotFoundException e) {
            // Print the full exception message to the console
            System.out.println("Error: Configuration file not found. Please check the file path.");
            e.printStackTrace();  // Print the full stack trace
            throw new RuntimeException("Configuration file not found. Please check the file path.", e);
        } catch (IOException e) {
            // Print the full exception message to the console
            System.out.println("Error: Error reading the configuration file.");
            e.printStackTrace();  // Print the full stack trace
            throw new RuntimeException("Error reading the configuration file.", e);
        }
    }

    // Method to get a property value by key
    public static String getProperty(String key) throws MissingConfigurationException {
        String value= properties.getProperty(key);
        // If the property is not found, throw the custom exception
        // If the property is not found, throw the custom exception
        if (value == null) {
            System.out.println("Error: Property '" + key + "' not found in the configuration file.");
            throw new MissingConfigurationException("Property '" + key + "' not found in the configuration file.");
        }

        // If the value is empty, throw the custom exception
        if (value.trim().isEmpty()) {
            System.out.println("Error: Property '" + key + "' has an empty value in the configuration file.");
            throw new MissingConfigurationException("Property '" + key + "' has an empty value in the configuration file.");
        }

        return value;
    }

    // Optional: Method to get a default value if property is not found
//    public static String getProperty(String key, String defaultValue) {
//        return properties.getProperty(key, defaultValue);
//    }
}
