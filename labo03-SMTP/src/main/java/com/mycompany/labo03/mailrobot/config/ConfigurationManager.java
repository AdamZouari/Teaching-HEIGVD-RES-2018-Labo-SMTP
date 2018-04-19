package com.mycompany.labo03.mailrobot.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Adam Zouari
 * @author Walid Koubaa
 */
public class ConfigurationManager {
    
    Properties properties = new Properties();
    InputStream file;

    public ConfigurationManager() throws IOException{
        file = new FileInputStream("config.propreties");
        properties.load(file);
    }
    
    public String getSmtpServerAdress()
    {
        return properties.getProperty("smtpServerAdress");
    }
    
    public int getSmtpServerPort()
    {
        return Integer.valueOf(properties.getProperty("smtpServerPort"));
    }
                
    public int getNumberOfGroups()
    {
        return Integer.valueOf(properties.getProperty("numberOfGroups"));
    }
    
    public int getNumberOfPersonPerGroup()
    {
        return Integer.valueOf(properties.getProperty("numberOfPersonPerGroup"));
    }
}
