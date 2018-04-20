package com.mycompany.labo03.mailrobot.model.mail;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adam Zouari
 * @author Walid Koubaa
 */
public class Group {

    private final List<Person> persons;

    public Group(){
        persons = new ArrayList<>();
    }
    
    public void addPerson(Person person){
        persons.add(person);
    }
    
    public List<Person> getPersons(){
        return persons;
    }
    
}
