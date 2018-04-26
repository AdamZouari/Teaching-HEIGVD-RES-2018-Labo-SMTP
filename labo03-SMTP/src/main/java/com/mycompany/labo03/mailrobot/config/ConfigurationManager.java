package com.mycompany.labo03.mailrobot.config;

import com.mycompany.labo03.mailrobot.model.mail.Message;
import com.mycompany.labo03.mailrobot.model.mail.Person;
import com.mycompany.labo03.mailrobot.smtp.SmtpProtocol;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Properties;

/**
 *
 * @author Adam Zouari
 * @author Walid Koubaa
 */
public final class ConfigurationManager implements IConfigurationManager {

    private static final String MAIL_SEPARATOR = "===";
    private String smtpServerAddress;
    private int smtpServerPort;
    private int numberOfGroups;
    private LinkedList<Message> messages = new LinkedList<>();
    private LinkedList<Person> victims = new LinkedList<>();
    private LinkedList<Person> witnessCC = new LinkedList<>();

    public ConfigurationManager() throws IOException {
        loadProperties();
        loadVictims();
        loadMessages();
    }

    public String getSmtpServerAddress() {
        return smtpServerAddress;
    }

    public void setSmtpServerAddress(String smtpServerAddress) {
        this.smtpServerAddress = smtpServerAddress;
    }

    public int getSmtpServerPort() {
        return smtpServerPort;
    }

    public void setSmtpServerPort(int smtpServerPort) {
        this.smtpServerPort = smtpServerPort;
    }

    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    public void setNumberOfGroups(int numberOfGroups) {
        this.numberOfGroups = numberOfGroups;
    }

    public LinkedList<Message> getMessages() {
        return messages;
    }

    public void setMessages(LinkedList<Message> messages) {
        this.messages = messages;
    }

    public LinkedList<Person> getVictims() {
        return victims;
    }

    public void setVictims(LinkedList<Person> victims) {
        this.victims = victims;
    }

    public LinkedList<Person> getWitnessCC() {
        return witnessCC;
    }

    public void setWitnessCC(LinkedList<Person> witnessCC) {
        this.witnessCC = witnessCC;
    }

    @Override
    public void loadVictims() throws IOException {
        BufferedReader victimsFile;
        victimsFile = new BufferedReader(new InputStreamReader(new FileInputStream("config/victims.utf8"), "UTF-8"));
        String address;
        while ((address = victimsFile.readLine()) != null) {
            victims.add(new Person(address));
        }
        victimsFile.close();
    }

    @Override
    public void loadMessages() throws IOException {

        BufferedReader messagesFile;
        messagesFile = new BufferedReader(new InputStreamReader(new FileInputStream("config/messages.utf8"), "UTF-8"));
        String line;
        while ((line = messagesFile.readLine()) != null) {
            String subject = line;
            String data = "";
            while (!(line=messagesFile.readLine()).equals(MAIL_SEPARATOR)) {
                data += line + SmtpProtocol.EOL;
            }
            messages.add(new Message(subject, data));
        }
        messagesFile.close();
    }

    @Override
    public void loadProperties() throws IOException {
        Properties confProperties = new Properties();
        FileInputStream confPropertiesFile = new FileInputStream("config/config.properties");
        confProperties.load(confPropertiesFile);
        smtpServerAddress = confProperties.getProperty("smtpServerAddress");
        smtpServerPort = Integer.valueOf(confProperties.getProperty("smtpServerPort"));
        numberOfGroups = Integer.valueOf(confProperties.getProperty("numberOfGroups"));
        String str = confProperties.getProperty("witness");
        String[] tabWitnesses = str.split(",");
        for (String x : tabWitnesses) {
            witnessCC.add(new Person(x));
        }
        confPropertiesFile.close();
    }

}
