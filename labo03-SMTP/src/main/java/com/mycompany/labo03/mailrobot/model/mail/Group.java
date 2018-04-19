

package com.mycompany.labo03.mailrobot.model.mail;

/**
 *
 * @author Adam Zouari
 * @author Walid Koubaa
 */
public class Group {

// recupere depuis properties

    private final ArrayList<Person> arraylistOfPersons;

    public  Group(List<Person> list) {
        arraylistOfPersons = new ArrayList<>(list);
    }

    public Group() {
        arraylistOfPersons = new ArrayList<>();
        try {
            readgGroupFile();
        } catch (IOException e) {
            System.out.println("Error while reading group file... Group is empty !\n");
        }
    }

    public void readgGroupFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/data/victims.utf8"));
        String line;

        while ((line = reader.readLine()) != null) {
            arraylistOfPersons.add(new Person(line));
        }

    }

    public ArrayList<Person> getGroup() {
       return arraylistOfPersons;
    }

    public Group getGroup() {
        return this;
    }

    public int size() {
        return arraylistOfPersons.size();
    }

    public String toString() {
        // StringBuilder is much faster to concatenate strings
        StringBuilder s = "";
        int size = arraylistOfPersons.size();

        for (int i = 0; i < size; ++i) {
            s.append(arraylistOfPersons.get(i));
            if (i != arraylistOfPersons.size() - 1) {
                s.append( ", ");
            }
        }
        return s.toString();
    }

}
