package ui;

import model.Action;
import model.ImageModel;
import model.Observer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageRate extends JPanel implements Observer {
    ImageModel imageModel;
    StarBtn[] stars = new StarBtn[5];

    public ImageRate(ImageModel im) {
        this.imageModel = im;
        imageModel.addObserver(this);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        boolean filled = true;
        for (int i=0; i<5; ++i) {
            if (this.imageModel.getRating() == -1 || i > this.imageModel.getRating()) {
                filled = false;
            }
            stars[i] = new StarBtn(filled, i);
            stars[i].setAlignmentX(Box.CENTER_ALIGNMENT);
            stars[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int rating = ((StarBtn)(e.getSource())).val;
                    if (rating == imageModel.getRating()){
                        imageModel.setRating(-1);
                    }
                    else {
                        imageModel.setRating(rating);
                    }
                }
            });
            this.add(stars[i]);
        }

    }

    @Override
    public void update(Action a) {
        switch(a) {
            case ChangeRating:
                for (int i = 0; i < 5; ++i) {
                    if (i <= this.imageModel.getRating()) {
                        stars[i].isFilled = true;
                    } else {
                        stars[i].isFilled = false;
                    }
                }
        }
        this.revalidate();
        this.repaint();
    }
}
