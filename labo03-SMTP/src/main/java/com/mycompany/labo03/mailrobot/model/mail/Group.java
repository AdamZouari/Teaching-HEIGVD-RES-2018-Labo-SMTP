package com.mycompany.labo03.mailrobot.model.mail;

import java.util.LinkedList;

/**
 *
 * @author Adam Zouari
 * @author Walid Koubaa
 */
public class Group {
    private Person victemSender;
    private final LinkedList<Person> persons;

    public Group(){
        persons = new LinkedList<Person>();
    }

    public Person getVictimSender() {
        return victemSender;
    }

    public void setVictemSender(Person victemSender) {
        this.victemSender = victemSender;
    }
    
    public void addPerson(Person person){
        persons.add(person);
    }
    
    public LinkedList<Person> getPersons(){
        return persons;
    }
    
}
