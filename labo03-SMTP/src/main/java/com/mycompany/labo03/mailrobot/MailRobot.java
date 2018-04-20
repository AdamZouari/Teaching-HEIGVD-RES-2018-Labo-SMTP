

package com.mycompany.labo03.mailrobot;

import com.mycompany.labo03.mailrobot.smtp.*;

import java.io.IOException;

/**
 *
 * @author Adam Zouari
 * @author Walid Koubaa
 */
public class MailRobot {

    public static void main (String[] args) throws IOException {

        //PrankGenerator manager = new PrankGenerator();
        SmtpClient client = new SmtpClient(SmtpConfigurations.SMTP_SERVER_ADRESS, SmtpConfigurations.SMTP_SERVER_PORT);

        try {

            client.connect();
            client.sendMessage();
            client.quit();

        } catch (IOException e) {
            System.err.println("Error in mail program");
        } finally {
            client.close();
        }

    }
}
