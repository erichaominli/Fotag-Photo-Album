import model.Model;
import ui.View;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);
        loadState(new File("./settings"), model);
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveState(new File("./settings"), model);
            }
        });
    }

    public static void loadState(File file, Model model) {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Model deserializedModel = (Model) objectIn.readObject();
            fileIn.close();
            objectIn.close();
            model.load(deserializedModel);
        } catch (Exception exception) {
            System.out.println("Couldn't load state");
        }
    }

    public static void saveState(File file, Model model) {
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(model);
            objectOut.close();
            fileOut.close();
        } catch (Exception exception) {
            System.out.println("Couldn't save state");
        }
    }
}
