package ui;

import model.Action;
import model.ImageModel;
import model.Model;
import model.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.ArrayList;


public class ImageCollection extends JPanel implements Observer {
    private ArrayList<ImageView> images;
    private Model model;
    private GridLayout gridLayout;

    public ImageCollection(Model model) {
        this.model = model;
        this.model.addObserver(this);
        this.gridLayout = new GridLayout(0, 3);
        this.images = new ArrayList<>();


    }


    public void setup() {
        this.images = new ArrayList<>();
        for (ImageModel imgModel : model.getListOfImages()) {
            if (imgModel.getRating() >= model.getCurFilter()) {
                ImageView imageView = new ImageView(imgModel, this.model);
                this.images.add(imageView);
            }
        }
        if (!model.getIsListView()) {
            this.setLayout(gridLayout);
        } else {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        }

        for (ImageView imgView : images) {
            this.add(imgView);
        }

        this.setBackground(Color.WHITE);
    }

    @Override
    public void update(Action a) {
        switch (a) {
            case AddImage:
            case SetFilter:
            case ChangeLayout:
                this.removeAll();
                setup();
                this.revalidate();
                this.repaint();
                break;
        }
    }
}
