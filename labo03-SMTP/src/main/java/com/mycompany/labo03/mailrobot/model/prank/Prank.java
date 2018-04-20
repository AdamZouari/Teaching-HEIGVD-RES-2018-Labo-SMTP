

package com.mycompany.labo03.mailrobot.model.prank;

import com.mycompany.labo03.mailrobot.model.mail.Group;
import com.mycompany.labo03.mailrobot.model.mail.Message;
import com.mycompany.labo03.mailrobot.model.mail.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Adam Zouari
 */

public class Prank{

    private Message[] mails;
    private Group group;

    private Person victim;
    private String object;
    private String data;
    private final List<Person> victims = new ArrayList<>();
    private final List<Person> nonVictims = new ArrayList<>();

    public Prank(){
           

    }

    public Message[] getMails() {
        return mails;
    }


    public void setVictim(Person victim){
        this.victim = victim;
    }

    public Person getVictim() {
        return victim;
    }
    public String getObject(){
        return object;
    }

    public void setObject(String object){
        this.object = object;
    }

    public void addVictims(List<Person> victims){
        this.victims.addAll(victims);
    }
    public void addNonVictims(List<Person> nonVictims){
        this.nonVictims.addAll(nonVictims);
    }

    public List<Person> getNonVictims(){
        return new ArrayList<>(nonVictims);
    }
    public List<Person> getVictims(){
        return new ArrayList<>(victims);
    }

    public String getData(){
        return data;
    }

    public void setData(String data){
        this.data = data;
    }

    public Message createMessage(){
        ArrayList<String> toAddresses = new ArrayList<>();
        Message message = new Message();


        message.setFrom(victim.getAddress());
        for(Person victim : victims){
            toAddresses.add(victim.getAddress());
        }
        
        message.setTo(toAddresses);

        message.setObject(object);
        message.setData(data);

        return message;
    }

}
