package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    //this class will read from Configuration.properties
    //then it will pass those values to our test

    //this variable will store all information from properties file
    private static Properties properties;
    //we need to load our configuration.properties into properties variable

    static {
        //first of all we need to provide a path to our properties file
        String path = "src/test/resources/configuration.properties";
        //read from the file
        try {
            FileInputStream inputStream = new FileInputStream(path);
            //now we need to load into the properties variable
            properties = new Properties();
            properties.load(inputStream);
            inputStream.close();

        } catch (IOException e) {
            //in case this exception happens we want to know where it is coming from and the reason for it
            e.printStackTrace();
        }

    }
    //
    //properties.getProperty(browser)
    public static String getProperty(String key){
        return properties.getProperty(key).trim();
    }

}
