package com.test.config;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class ConfigReader {
    private static Properties properties;
    private static final String configPath = "src/main/java/com/test/resources/config.properties";

    static {
    try(FileInputStream fis = new FileInputStream(configPath)){
        properties = new Properties();
        properties.load(fis);
    }catch(IOException e){
        throw new RuntimeException("Failed to load the config.properties" + e.getMessage());
    }
   }

    public static String  getProperty(String key){
        String value = properties.getProperty(key);
        if(value == null){
            throw new RuntimeException("Property "+key+" is not found in the config.properties");
        }
        return value.trim();
    }

    public static String getBaseURL(){
        return getProperty("baseURL");
    }

    public static String getBrowser(){
        return getProperty("browser");
    }

    public static String getUserName(){
        return getProperty("userName");
    }

    public static String getPassword(){
        return getProperty("password");
    }

    public static int getImplicitWait(){
        return Integer.parseInt(getProperty("implictWait"));
    }

    public static int getExplictWait(){
        return Integer.parseInt(getProperty("explictWaits"));
    }

    public static int getPageLoadTimeouts(){
        return Integer.parseInt(getProperty("pageLoadTimeouts"));
    }

    public static String getInvalidUsername(){
        return getProperty("invalidUsername");
    }

    public static String getInvalidPassword(){
        return getProperty("invalidPassword");
    }
}
