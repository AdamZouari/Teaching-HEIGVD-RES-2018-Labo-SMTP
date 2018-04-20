/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labo03.mailrobot.smtp;

import java.io.*;
import java.net.Socket;

/**
 * @author Adam Zouari
 * @author Walid Koubaa
 */
public class SmtpClient {

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    String line, server;
    int port;

    public SmtpClient(String server, int port) {

        this.server = server;
        this.port = port;
    }

    // We pen a connexion between a SMTP server and a client
    public void connect() throws IOException {

        socket = new Socket(server, port);
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
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









        // Welcome message received
        /*String feedback;
        reader.readLine();

        writer.println();
        System.out.println("OKLM");

        // EHLO Heading
        sendToServer(SmtpProtocol.EHLO);
        feedback = reader.readLine();

        while(feedback.startsWith("250-")){
            feedback = reader.readLine();
            //LOG.info(line);
        }

        /*while(feedback !="250 0k"){
            feedback = reader.readLine();
        }*/
        // The server will send an unknown number of data in this format
        //while ((feedback = reader.readLine()).contains(SmtpProtocol.ACCEPTED+" "));
        //System.out.println("OKLM1");

        //  We reached end of data, so we can start writing an email
        /*if (!feedback.contains(SmtpProtocol.ACCEPTED+" ")) {
            System.out.println("Error at the beginning of the mail\n");
            sendToServer(SmtpProtocol.QUIT);

        }*/
    }

    // We send the prank that contains an email and a group of people to target

    public void sendMessage() throws IOException {

        String email = "walid.koubaa@heig-vd.ch";


        writer.write(SmtpProtocol.MAIL_FROM + "walid.koubaa@heig"  + SmtpProtocol.EOL);
        writer.flush();
        line = reader.readLine();


        writer.write(SmtpProtocol.RCPT_TO + "walid.koubaa@heig" + SmtpProtocol.EOL);
        writer.flush();
        line = reader.readLine();

        // DATA Heading
        writer.write(SmtpProtocol.DATA + SmtpProtocol.EOL);
        writer.flush();

        line = reader.readLine();

        writer.write("Content-Type: text/plain; charset=UTF-8" + SmtpProtocol.EOL);
        System.out.println("DATA START");

        writer.write("From: " + email + SmtpProtocol.EOL/*messages[i].getFrom().getAddress()*/);
        writer.flush();
        writer.write("To: " + /*messages[i].getTo().getGroup()*/email + SmtpProtocol.EOL);
        writer.flush();
        writer.write("Subject: Salut !"/*messages[i].getObjet()*/ + SmtpProtocol.EOL);
        writer.flush();
        writer.write("Salut ca va ?" + SmtpProtocol.EOL /*messages[i].getData()*/);
        writer.flush();

        System.out.println("BEFORE END OF DATA");
        writer.write(SmtpProtocol.END_OF_DATA);
        writer.flush();

        System.out.println("AFTER END OF DATA");

        reader.readLine();

        writer.write(SmtpProtocol.QUIT + SmtpProtocol.EOL);
        writer.flush();

        System.out.println("SEND MESSAGE");

    }


    //}


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

