package ui;

import model.Action;
import model.Model;
import model.Observer;

import javax.swing.*;
import java.awt.*;

public class RatingPanel extends JPanel implements Observer {
    private JLabel filterLabel;
    StarBtn[] stars = new StarBtn[5];
    Model model;

    public RatingPanel(Model model) {
        filterLabel = new JLabel("Filter by: ");
        this.add(filterLabel);

        this.model = model;
        this.model.addObserver(this);


        for (int i =0; i < 5; ++i) {
            stars[i] = new StarBtn(false, i);
            stars[i].addActionListener(e -> {
                    int clickNum = ((StarBtn) e.getSource()).val;
                    if (clickNum == model.getCurFilter()){
                        model.setCurFilter(-1);
                    }
                    else {
                        model.setCurFilter(clickNum);
                    }
            });
            this.add(stars[i]);
        }

        this.setBackground(Color.WHITE);
    }


    @Override
    public void update(Action a) {
        switch (a) {
            case SetFilter:
                for (int i = 0; i < 5; ++i) {
                    if (i <= this.model.getCurFilter()) {
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
