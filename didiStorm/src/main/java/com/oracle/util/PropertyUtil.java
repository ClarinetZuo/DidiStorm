package com.oracle.util;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Clarinet
 * @since JDK8
 */
public class PropertyUtil {

    private static Properties properties;

    static {
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }


}
