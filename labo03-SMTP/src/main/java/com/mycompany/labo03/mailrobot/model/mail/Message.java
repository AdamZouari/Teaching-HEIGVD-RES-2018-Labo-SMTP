package com.mycompany.labo03.mailrobot.model.mail;

/**
 *
 * @author Adam Zouari
 * @author Walid Koubaa
 */
public class Message {
    private String subject = "";
    private String data = "";

    public Message( String object, String data) {
        this.subject = object;
        this.data = data;
    }

    public Message() {

    }

    public String getData() {
        return data;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String object) {
        this.subject = object;
    }

    public void setData(String data) {
        this.data = data;
    }

}
