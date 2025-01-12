package com.mystore.utility;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
public class Res {
    private static final File[] parents =
            new File[] {
                    new File("."),                // Current directory
                    new File("./src/main/resources"), // Main resources directory
                    new File("./src/test/resources")  // Test resources directory
            };

// Method to get a file from the defined resource directories
public static File getFile(String resourcePath) throws IOException {
    for (File parent : parents) {
        File file = new File(parent, resourcePath);
        if (file.exists()) {
            return file; // Return the first file found
        }
    }
    throw new FileNotFoundException("Resource not found: " + resourcePath); // If file isn't found in any location
}

// Method to get a URL for resources (useful for resources that should be loaded as streams)
public static URL getResource(String resourcePath) {
    for (File parent : parents) {
        File file = new File(parent, resourcePath);
        if (file.exists()) {
            try {
                return file.toURI().toURL();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    return null; // If resource is not found
}}
