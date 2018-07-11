package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class StarBtn extends JButton {
    ImageIcon filled;

    {
        try {
            filled = new ImageIcon(ImageIO.read(new File("img/starFill.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ImageIcon unfilled;

    {
        try {
            unfilled = new ImageIcon(ImageIO.read(new File("img/star.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int val;
    public boolean isFilled;

    public StarBtn(boolean isfilled, int val){
        this.val = val;
        this.isFilled = isfilled;

        if (isFilled) {
            this.setIcon(filled);
        }
        else {
            this.setIcon(unfilled);
        }

        this.setPreferredSize(new Dimension(32,32));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.isFilled) {
            this.setIcon(filled);
        }
        else{
            this.setIcon(unfilled);
        }
    }
}
