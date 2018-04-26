package com.mycompany.labo03.mailrobot.smtp;

/**
*
* Class that contains the same key words (headings) as the protocol SMTP
*
* @ author Adam Zouari
* @ author Walid Koubaa
*/

public class SmtpProtocol {
	
    public static final String EHLO = "EHLO ";
    public static final String MAIL_FROM = "MAIL FROM: ";
    public static final String RCPT_TO = "RCPT TO: ";
    public static final String DATA = "DATA";
    public static final String QUIT = "QUIT";
    public static final int ACCEPTED = 250;
    public static final int SERVER_READY = 220;
    public static final String EOL = "\r\n";
    public static final String END_OF_DATA = EOL + "." +EOL;



}
