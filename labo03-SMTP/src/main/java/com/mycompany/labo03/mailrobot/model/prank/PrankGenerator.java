

package com.mycompany.labo03.mailrobot.model.prank;

/**
 *
 * @author Adam Zouari
 */
public class PrankGenerator {

    private Prank prank;
    private SmtpClient client;

    public PrankGenerator() throws IOException{
        client = new SmtpClient();
    }

    public void sendPrank() throws IOException{
        prank = new Prank();
        client.send(prank);
    }

    public void start() throws IOException {
        client.connect();
    }

    public void stop() throws IOException {
        client.quit();
    }

    public void close() throws IOException {
        client.close();
    }


}
