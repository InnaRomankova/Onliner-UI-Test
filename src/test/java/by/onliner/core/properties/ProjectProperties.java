package by.onliner.core.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ProjectProperties {

    private static Properties properties;

    static {
        initProperties();
    }

    private static void initProperties() {
        properties = new Properties();

        try {
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/properties.properties");
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String propertyName) {
        if (!properties.containsKey(propertyName) || properties.getProperty(propertyName) == null
                || properties.getProperty(propertyName).trim().isEmpty()
        ) {
            System.out.println(String.format("ERROR OCCURRED: \"%s\" property does not exist or it's value is invalid.", propertyName));
        }
        return properties.getProperty(propertyName).trim();
    }
}
