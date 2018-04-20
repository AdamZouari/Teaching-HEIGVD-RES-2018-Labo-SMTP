package com.mycompany.labo03.mailrobot.config;

import com.mycompany.labo03.mailrobot.model.mail.Person;
import com.mycompany.labo03.mailrobot.smtp.SmtpProtocol;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Adam Zouari
 * @author Walid Koubaa
 */
public final class ConfigurationManager implements IConfigurationManager{

    private final Properties confProperties;
    private final FileInputStream confPropertiesFile;
    private final BufferedReader victimsFile;
    private final BufferedReader messagesFile;
    private static final String MAIL_SEPARATOR = "===";
    private List<String> messages = new ArrayList<>();
    private List<Person> victims = new ArrayList<>();



    public ConfigurationManager() throws IOException {
        confProperties = new Properties();
        confPropertiesFile =  new FileInputStream("config/config.properties");
        victimsFile  = new BufferedReader(new InputStreamReader(new FileInputStream("config/victims.utf8"), "UTF-8")); 
        messagesFile = new BufferedReader(new InputStreamReader(new FileInputStream("config/messages.utf8"), "UTF-8"));
        
        confProperties.load(confPropertiesFile);
        victims = loadVictims();
        messages = loadMessages();
    }
    
    

    @Override
    public String getSmtpServerAddress() {
        return confProperties.getProperty("smtpServerAddress");
    }
    
   
    @Override
    public int getSmtpServerPort() {
        return Integer.valueOf(confProperties.getProperty("smtpServerPort"));
    }

    @Override
    public int getNumberOfGroups() {
        return Integer.valueOf(confProperties.getProperty("numberOfGroups"));
    }

    @Override
    public int getNumberOfPersonPerGroup() {
        return Integer.valueOf(confProperties.getProperty("numberOfPersonPerGroup"));
    }

    @Override
    public List<Person> loadVictims() throws IOException
    {
        String address;
        while((address = victimsFile.readLine()) != null )
        {
            victims.add(new Person(address));
        }
        return victims;
    }
    
    @Override
    public List<String> loadMessages() throws IOException
    {
        String message = "", line;
        while((line = messagesFile.readLine()) != null )
        {
            if(!line.equals(MAIL_SEPARATOR))
                message += line + SmtpProtocol.EOL;
            else
                messages.add(message);
        }
        return messages;
    }
    
    public List<Person> getVictims(){return victims;}
    public List<String> getMessages(){return messages;}

}
