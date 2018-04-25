package com.mycompany.labo03.mailrobot.smtp;

import com.mycompany.labo03.mailrobot.model.mail.Message;
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
    String line, server;
    int port;

    public SmtpClient(String server, int port) {

        this.server = server;
        this.port = port;
    }

    // We pen a connexion between a SMTP server and a client
    public void connect() throws IOException {

        socket = new Socket(server, port);
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

        // Welcome message received
        line = reader.readLine();
        System.out.println(line);

        // EHLO Heading
        writer.write("EHLO localhost" + SmtpProtocol.EOL);
        writer.flush();


        while ((line = reader.readLine()).startsWith("250-")) {
            System.out.println(line);

        }
        System.out.println(line);
    }

    public void sendMessage(Message message) throws IOException {

        writer.write(SmtpProtocol.MAIL_FROM + message.getFrom() + SmtpProtocol.EOL);
        writer.flush();
        line = reader.readLine();

        for(String to : message.getTo()){
            writer.write("RCPT TO: " + to + SmtpProtocol.EOL);
            writer.flush();
            line = reader.readLine();
        }
        
        writer.write(SmtpProtocol.RCPT_TO + message.getFrom() + SmtpProtocol.EOL);
        writer.flush();
        line = reader.readLine();

        // DATA Heading
        writer.write(SmtpProtocol.DATA + SmtpProtocol.EOL);
        writer.flush();

        line = reader.readLine();

        writer.write("Content-Type: text/plain; charset=UTF-8" + SmtpProtocol.EOL);
        System.out.println("DATA START");

        writer.write("From: " + message.getFrom() + SmtpProtocol.EOL);
        writer.flush();
        
        writer.write("To: " + message.getTo().get(0));
        for(int i = 1; i < message.getTo().size(); i++)
        {
            writer.write(", " + message.getTo().get(i));
        }
        writer.write(SmtpProtocol.EOL);
        writer.flush();
        
        writer.write("Subject: " + message.getObjet() + SmtpProtocol.EOL +  SmtpProtocol.EOL);
        writer.flush();
        
        writer.flush();

        writer.write(SmtpProtocol.END_OF_DATA);
        writer.flush();

        reader.readLine();

        writer.write(SmtpProtocol.QUIT + SmtpProtocol.EOL);
        writer.flush();

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

