package com.mycompany.labo03.mailrobot.config;

/**
 *
 * @author Adam Zouari
 * @author Walid Koubaa
 */
public interface IConfigurationManager {

    public String getSmtpServerAddress();

    public int getSmtpPort();

    public int getNumberOfGroups();

    public int getNumberOfPersonPerGroup();

}
