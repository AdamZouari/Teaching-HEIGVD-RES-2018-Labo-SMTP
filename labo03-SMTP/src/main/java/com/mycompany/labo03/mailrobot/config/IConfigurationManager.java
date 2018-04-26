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

    public void loadProperties() throws IOException;

    public void loadVictims() throws IOException;

    public void loadMessages() throws IOException;
}
