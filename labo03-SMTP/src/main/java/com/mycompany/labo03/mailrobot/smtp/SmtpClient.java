/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labo03.mailrobot.smtp;

/**
 *
 * @author Adam Zouari
 * @author Walid Koubaa
 */
public class SmtpClient {

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private File configFile;

    public SmtpClient() {

    }

    // We pen a connexion between a SMTP server and a client
    public void connect() throws IOException {
        socket = new Socket(SmtpServSmtpConfigurationser.SMTP_SERVER_ADRESS, SmtpServSmtpConfigurationser.SMTP_SERVER_PORT);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

        // Welcome message received
        String feedback = reader.readLine();

        // EHLO Heading
        writer.println(SmtpHeading.HELLO + " you");
        writer.flush();

        // The server will send an unknown number of data in this format
        while ((feedback = reader.readLine()).contains(SmtpHeading.ACCEPTED+"-"));

        //  We reached end of data, so we can start writing an email
        if (!feedback.contains(SmtpHeading.ACCEPTED+" ")) {
            System.out.println("Error at the beginning of the mail\n");
            writer.println(SmtpHeading.QUIT);
            writer.flush();
        }
    }

    // We send the prank that contains an email and a group of people to target

    public void send(Prank prank) throws IOException {

        Mail[] mails = prank.getMails();


        for (int i = 0; i < mails.length; i++) {
            String feedback;

            // MAIL FROM Heading
            writer.print(SmtpHeading.FROM);
            writer.flush();
            writer.println(mails[i].getFrom().getAddress());
            writer.flush();

            System.out.println("FROM: " + mails[i].getFrom().getAddress());

            reader.readLine();


            // RCPT TO Heading
            List<Person> to = new ArrayList<>(mails[i].getTo().getGroup());

            for (int j = 0; j < to.size(); ++j) {
                writer.print(SmtpHeading.TO);
                writer.flush();
                writer.println(to.get(j).getAddress());
                writer.flush();

                feedback = reader.readLine();

                if (!feedback.contains(String.valueOf(SmtpHeading.ACCEPTED))) {
                    System.out.print(to.get(j).getAddress() +" not accepted");
                }
            }

            System.out.println("TO: " + mails[i].getTo().group());

            // DATA Heading
            writer.println(SmtpHeading.DATA);
            writer.flush();

            reader.readLine();

            writer.println("From: "+ mails[i].getFrom().getAddress());
            writer.flush();
            writer.println("To: " + mails[i].getTo().group());
            writer.println(mails[i].getSubject() + System.lineSeparator());
            writer.flush();
            writer.println(mails[i].getBody());
            writer.flush();

            writer.print(SmtpHeading.END_OF_DATA);
            writer.flush();

            reader.readLine();
        }


    }

    public void quit() throws IOException {

        if (socket == null || socket.isClosed()) {
            return;
        }

        // QUIT Heading
        writer.println(SmtpHeading.QUIT);
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

