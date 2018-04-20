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
public class ConfigurationManager {

    private Properties confProperties;
    private final FileInputStream confPropertiesFile;
    private final BufferedReader victimsFile, messagesFile;
    private static final String MAIL_SEPARATOR = "===";
    private List<String> messages;
    private List<Person> victims;



    public ConfigurationManager() throws IOException {
        confPropertiesFile = new FileInputStream("config.propreties");
        confProperties.load(confPropertiesFile);

        victimsFile = new BufferedReader(new InputStreamReader(new FileInputStream("victims.utf8"), "UTF-8"));
        messagesFile = new BufferedReader(new InputStreamReader(new FileInputStream("messages.utf8"), "UTF-8"));

        messages = new ArrayList<>();
        victims = new ArrayList<>();
    }

    public String getSmtpServerAddress() {
        return confProperties.getProperty("smtpServerAddress");
    }

    public int getSmtpServerPort() {
        return Integer.valueOf(confProperties.getProperty("smtpServerPort"));
    }

    public int getNumberOfGroups() {
        return Integer.valueOf(confProperties.getProperty("numberOfGroups"));
    }

    public int getNumberOfPersonPerGroup() {
        return Integer.valueOf(confProperties.getProperty("numberOfPersonPerGroup"));
    }

    public List<Person> loadVictims() throws IOException
    {
        String address;
        while((address = victimsFile.readLine()) != null )
        {
            victims.add(new Person(address));
        }
        return victims;
    }
    
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
