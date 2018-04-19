

package com.mycompany.labo03.mailrobot;

/**
 *
 * @author Adam Zouari
 * @author Walid Koubaa
 */
public class MailRobot {

    public static void main (String[] args) throws IOException {

        ConfigurationManager manager = new ConfigurationManager();

        try {
            manager.start();

            manager.sendPrank();

            manager.stop();

        } catch (IOException e) {
            System.err.println("Error in mail program");
        } finally {
            manager.close();
        }

    }
}
