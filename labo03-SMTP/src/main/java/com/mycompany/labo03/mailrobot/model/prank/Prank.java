package com.mycompany.labo03.mailrobot.model.prank;

import com.mycompany.labo03.mailrobot.model.mail.Group;
import com.mycompany.labo03.mailrobot.model.mail.Message;
import com.mycompany.labo03.mailrobot.model.mail.Person;
import java.util.LinkedList;
/**
 *
 * @author Adam Zouari
 */

public class Prank{

    private Message message;
    private LinkedList<Person> witness;
    private Group group;
    
    public Prank(Message message,Group group){
        this.message=message;
        this.group=group;
           
    }
    public LinkedList<Person> getWitness() {
        return witness;
    }

    public void setWitness(LinkedList<Person> witness) {
        this.witness = witness;
    }


    public Message getMessage() {
        return message;
    }

    public void setMail(Message message) {
        this.message = message;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }


}
