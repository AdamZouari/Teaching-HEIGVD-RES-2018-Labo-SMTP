

package com.mycompany.labo03.mailrobot.model.prank;

/**
 *
 * @author Adam Zouari
 */

public class Prank{

    private Mail[] mails;
    private Group group;

    private static final GROUP_SIZE = 4;

    public Prank(){

        superGroup = new Group();

        mails = new Mail[GROUP_SIZE];

        for (int i = 0; i < GROUP_SIZE - 1; ++i) {
            Person victim = chooseSender(subgroup);
            mails[i] = new Mail(victim, subgroup);
        }
    }

    public Mail[] getMails() {
        return mails;
    }

    public Person chooseSender(Group group) {

        List<Person> listOfPeople = group.getGroup();

        int bound = group.size();
        Random random = new Random();
        int index = random.nextInt(bound);
        Person victim = listOfPeople.get(index);
        group.remove(index);
        return victim;
    }
}
