

package com.mycompany.labo03.mailrobot.model.prank;

import com.mycompany.labo03.mailrobot.config.ConfigurationManager;
import com.mycompany.labo03.mailrobot.model.mail.Group;
import com.mycompany.labo03.mailrobot.model.mail.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Adam Zouari
 */
public class PrankGenerator {

    private final ConfigurationManager config;

    public PrankGenerator(ConfigurationManager config){
        this.config = config;

    }

    public List<Group> createGroups(List<Person> persons, int nbOfGroup) throws IOException {
        List<Person> victims = new ArrayList(persons);
        List<Group> groups = new ArrayList<>();
        int bound = 0;

        for (int i=0; i< nbOfGroup; i++) {
            Group group = new Group();
            groups.add(group);
        }


        Group subGroup;
        while (victims.size() > 0) {
            subGroup = groups.get(bound);
            bound = (bound + 1) % groups.size();
            Person newVictim = victims.remove(0);
            subGroup.addPerson(newVictim) ;
        }

        return groups;
    }

    public List<Prank> createPranks() throws IOException{

        List<String> messages = config.loadMessages();
        List<Person> victims = config.loadVictims();
        int numberOfGroups = config.getNumberOfGroups();
        int numberOfPersonPerGroup = config.getNumberOfPersonPerGroup();
        List<Prank> pranks = new ArrayList<>();

        List<Group> groups = createGroups(victims, numberOfGroups);

        int index = 0;
        Random random = new Random();
        for(Group group : groups){
            Prank prank = new Prank();

            List<Person> members = group.getPersons();
            Person sender = members.remove(0);
            prank.setVictim(sender);
            prank.addVictims(members);


            index = random.nextInt(messages.size() / 2) * 2;
            String subject = messages.get(index);
            String message = messages.get(index + 1);

            prank.setObject(subject);

            prank.setData(message);

            pranks.add(prank);

        }
    return pranks;    
    }
    

}
