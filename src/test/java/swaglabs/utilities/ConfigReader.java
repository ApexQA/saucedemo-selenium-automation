package swaglabs.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            String configPath = "src/test/resources/config.properties";
            FileInputStream input = new FileInputStream(configPath);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
