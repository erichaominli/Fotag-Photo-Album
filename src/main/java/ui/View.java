package ui;

import model.Action;
import model.Model;
import model.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;

public class View extends JFrame implements Observer {

    private Model model;
    private Menu menu;
    private ImageCollection imgCollection;
    private JScrollPane scrollPane;

    /**
     * Create a new View.
     */
    public View(Model model) {
        this.model = model;

        this.menu = new Menu(this.model);
        this.imgCollection = new ImageCollection(this.model);
        this.scrollPane = new JScrollPane(this.imgCollection);
        this.scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        // Set up the window.
        this.setTitle("Fotag");
        this.setLayout(new BorderLayout());

        this.add(this.menu, BorderLayout.PAGE_START);
        this.add(this.scrollPane, BorderLayout.CENTER);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if(!model.getIsListView()){
                    imgCollection.setLayout(new GridLayout(0, getWidth()/200));
                }
            }
        });

        this.setMinimumSize(new Dimension(200, 300));
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Hook up this observer so that it will be notified when the model
        // changes.

        setVisible(true);
    }


    @Override
    public void update(Action a) {
        this.revalidate();
        this.repaint();
    }
}
