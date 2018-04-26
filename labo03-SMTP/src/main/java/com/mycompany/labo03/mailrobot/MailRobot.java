package com.mycompany.labo03.mailrobot;

import com.mycompany.labo03.mailrobot.config.ConfigurationManager;
import com.mycompany.labo03.mailrobot.model.prank.Prank;
import com.mycompany.labo03.mailrobot.model.prank.PrankGenerator;
import com.mycompany.labo03.mailrobot.smtp.SmtpClient;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Adam Zouari
 * @author Walid Koubaa
 */
public class MailRobot {

    public static void main(String[] args) throws IOException {

        ConfigurationManager config = new ConfigurationManager();
        SmtpClient client = new SmtpClient();

        try {
            client.connect(config.getSmtpServerAddress(), config.getSmtpServerPort());
            PrankGenerator gen = new PrankGenerator(config);
            List<Prank> prankCampaign = gen.createPranks();
            
            for (Prank p : prankCampaign) {
                client.sendPrank(p);
            }
            client.quit();

        } catch (IOException e) {
            System.err.println("Error in mail program");
        } finally {
            client.close();
        }

    }
}
