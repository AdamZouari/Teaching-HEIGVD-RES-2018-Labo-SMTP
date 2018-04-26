package com.mycompany.labo03.mailrobot.smtp;

import com.mycompany.labo03.mailrobot.model.mail.Person;
import com.mycompany.labo03.mailrobot.model.prank.Prank;
import java.io.*;
import java.net.Socket;

/**
 * @author Adam Zouari
 * @author Walid Koubaa
 */
public class SmtpClient {

    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    String line = "";

    public SmtpClient() {
    }

    // We pen a connexion between a SMTP server and a client
    public void connect(String server, int port) throws IOException {

        socket = new Socket(server, port);
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

    }

    public void sendPrank(Prank prank) throws IOException {

        // Welcome message received
        line = reader.readLine();

        // EHLO Heading
        writer.write("EHLO localhost" + SmtpProtocol.EOL);
        writer.flush();

        while ((line = reader.readLine()).startsWith("250-"));

        writer.write(SmtpProtocol.MAIL_FROM + prank.getGroup().getVictimSender().getAddress() + SmtpProtocol.EOL);
        writer.flush();
        line = reader.readLine();

        for (Person to : prank.getGroup().getPersons()) {
            writer.write("RCPT TO: " + to.getAddress() + SmtpProtocol.EOL);
            writer.flush();
        }
        for (Person to : prank.getWitness()) {
            writer.write("RCPT TO: " + to.getAddress() + SmtpProtocol.EOL);
            writer.flush();
        }
        reader.readLine();
        // DATA Heading
        writer.write(SmtpProtocol.DATA + SmtpProtocol.EOL);
        writer.flush();

        reader.readLine();

        writer.write("Content-Type: text/plain; charset=UTF-8" + SmtpProtocol.EOL);
        writer.flush();
        
        writer.write("From: " + prank.getGroup().getVictimSender().getAddress() + SmtpProtocol.EOL);
        writer.flush();

        writer.write("To: " + prank.getGroup().getPersons().removeFirst().getAddress());
        for (Person p : prank.getGroup().getPersons()) {
            writer.write(", " + p.getAddress());
        }
        writer.write(SmtpProtocol.EOL);
        writer.flush();

        writer.write("Subject: " + prank.getMessage().getSubject() + SmtpProtocol.EOL + SmtpProtocol.EOL);
        writer.flush();

        writer.write(prank.getMessage().getData() + SmtpProtocol.END_OF_DATA);
        writer.flush();

        reader.readLine();
        

    }

    public void quit() throws IOException {

        if (socket == null || socket.isClosed()) {
            return;
        }

        // QUIT Heading
        writer.write(SmtpProtocol.QUIT + SmtpProtocol.EOL);
        writer.flush();
        reader.readLine();
    }

    public void close() throws IOException {

        if (socket == null || socket.isClosed()) {
            return;
        }

        // We close all buffers and connexion
        reader.close();
        writer.close();
        socket.close();

        socket = null;
        reader = null;
        writer = null;
    }

}
