package com.mycompany.labo03.mailrobot.config;

import com.mycompany.labo03.mailrobot.model.mail.Person;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Adam Zouari
 * @author Walid Koubaa
 */
public interface IConfigurationManager {

    public String getSmtpServerAddress();

    public int getSmtpServerPort();

    public int getNumberOfGroups();

    public int getNumberOfPersonPerGroup();

    public List<Person> loadVictims() throws IOException;

    public List<String> loadMessages() throws IOException;

    public List<String> getMessages();

    public List<Person> getVictims();
}
