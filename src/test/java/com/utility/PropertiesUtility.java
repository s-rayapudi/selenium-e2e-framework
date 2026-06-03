package com.utility;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import com.constants.Environments;

public class PropertiesUtility {
    public static String readProperties(Environments environment, String propertyName) throws Exception {

        File propertiesFile = new File(System.getProperty("user.dir") + "//config//" + environment + ".properties");
        Properties properties = new Properties();
        try (FileReader fileReader = new FileReader(propertiesFile)) {
            properties.load(fileReader);
        }
        catch(Exception e){
            throw new Exception("Error loading properties file: " + e.getMessage());
        }
        return properties.getProperty(propertyName.toUpperCase());
    }
}
