package ui;

import model.Action;
import model.ImageModel;
import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LoadBtn extends JButton {
    private JFileChooser fileChooser;
    private Model model;

    public LoadBtn(Model model) {
        this.model = model;

        this.fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);

        ImageIcon icon = null;
        try {
            icon = new ImageIcon(ImageIO.read(new File("img/import.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setIcon(icon);
        this.setPreferredSize(new Dimension(32, 32));

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int retval = fileChooser.showOpenDialog(LoadBtn.this);
                if (retval == JFileChooser.APPROVE_OPTION) {
                    File[] files = fileChooser.getSelectedFiles();
                    for (File file : files) {
                        model.addListOfImages(new ImageModel(file));
                        model.notifyObservers(Action.AddImage);
                    }
                }
            }
        });
    }
}
