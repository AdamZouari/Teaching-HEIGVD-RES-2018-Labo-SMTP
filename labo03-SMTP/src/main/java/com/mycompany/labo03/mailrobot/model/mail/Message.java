

package com.mycompany.labo03.mailrobot.model.mail;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adam Zouari
 * @author Walid Koubaa
 */
public class Message {

    private String from;
    private List<String> to;
    private String objet = "";
    private String data = "";

    public Message(String from, List<String> to,String object, String data) {
        this.from = from;
        this.to = to;
        this.objet = object;
        this.data = data;
    }
    public Message(){
    
    }

    public String getFrom(){
        return from;
    }

    public List<String> getTo(){
        return to;
    }

    public String getData(){
        return data;
    }

    public String getObjet(){
        return objet;
    }

    public void setTo(ArrayList<String> to){
        this.to= to;
    }

    public void setFrom(String from){
        this.from= from;
    }

    public void setObject(String object) {this.objet = object;}
    public void setData(String data) {this.data = data;}

}
