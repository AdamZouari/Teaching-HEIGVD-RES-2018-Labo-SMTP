

package com.mycompany.labo03.mailrobot;

import com.mycompany.labo03.mailrobot.config.ConfigurationManager;
import com.mycompany.labo03.mailrobot.model.mail.Message;
import com.mycompany.labo03.mailrobot.model.prank.Prank;
import com.mycompany.labo03.mailrobot.model.prank.PrankGenerator;
import com.mycompany.labo03.mailrobot.smtp.*;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Adam Zouari
 * @author Walid Koubaa
 */
public class MailRobot {

    public static void main (String[] args) throws IOException {

        //PrankGenerator manager = new PrankGenerator();
        ConfigurationManager manager = new ConfigurationManager();
        SmtpClient client = new SmtpClient(SmtpConfigurations.SMTP_SERVER_ADRESS, SmtpConfigurations.SMTP_SERVER_PORT);

        try {

            PrankGenerator prankGenerator = new PrankGenerator(manager);

            List<Prank> pranks = prankGenerator.createPranks();

            Message message;

            client.connect();

            for(Prank prank : pranks){
                message = prank.createMessage();
                client.sendMessage(message);
            }

            client.quit();

        } catch (IOException e) {
            System.err.println("Error in mail program");
        } finally {
            client.close();
        }

    }
}
