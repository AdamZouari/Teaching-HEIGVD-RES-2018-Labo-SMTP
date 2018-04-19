

package com.mycompany.labo03.mailrobot.model.mail;

/**
 *
 * @author Adam Zouari
 * @author Walid Koubaa
 */
public class Message {

    private Person from;
    private Group to;
    private String objet = "";
    private String data = "";

    private static int idMessage = 0;
    private static final int NB_MESSAGES = 5;



    public Message(Person from, Group to) {
        this.from = from;
        this.to = to;
        try {
            readMessageFromFile();
        } catch (IOException e) {
            System.out.println("Error while reading message from file...\n");
            objet = "";
            data = "";
        }
    }

    public Person getSender(){
        return from;
    }

    public Group getReceivers(){
        return to;
    }

    public getData(){
        return data;
    }

    public getObjet(){
        return objet;
    }

    public boolean readMMessageFromFile() throws IOException {
        int next_message = (idMessage++ % NB_MESSAGES) + 1;
        BufferedReader file = new BufferedReader(new FileReader("src/main/data/mail" + next_message + ".utf8"));

        String readline;

        // first we get the objet of the mail
        readline = file.readLine();

        if (!readline.contains("Objet")) {
            System.out.println("Error ! Wrong mail format ! No objet detected !\n" +
                    "Quitting...");
            return false;
        }

        objet = readline;

        // once the objet added we add each line to the data (content of the mail)
        while ((readline = file.readLine()) != null) {
            data += readline;
        }

        return true;
    }
}
