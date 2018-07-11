package ui;


import model.Action;
import model.ImageModel;
import model.Model;
import model.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class ImageView extends JPanel implements Observer {
    private Model model;
    private ImageModel imgModel;
    private JButton picture;
    private JLabel imageName;
    private JLabel imageDate;
    private ImageRate imageRate;

    public ImageView(ImageModel im, Model model) {
        this.model = model;
        this.imgModel = im;
        this.imgModel.addObserver(this);

        picture = new JButton();

        Image oriImg = null;
        try {
            oriImg = ImageIO.read(new File(im.getImagePath()));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Address not valid");
        }
        Image img = oriImg.getScaledInstance(150,150, Image.SCALE_SMOOTH);
        picture.setIcon(new ImageIcon(img));
        Image finalOriImg = oriImg;
        picture.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ImageIcon icon = new ImageIcon(finalOriImg.getScaledInstance(600, 400, Image.SCALE_SMOOTH));
                JOptionPane.showMessageDialog(picture, new JLabel(icon), imageName.getText(), JOptionPane.PLAIN_MESSAGE);
            }
        });


        imageName = new JLabel();
        imageName.setText(this.imgModel.getFileName());


        imageDate = new JLabel();
        imageDate.setText(imgModel.getFileDate());


        imageRate = new ImageRate(this.imgModel);

        //gridlayout
        if(!model.getIsListView()){
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            picture.setAlignmentX(Component.LEFT_ALIGNMENT);
            this.add(picture);
            imageName.setAlignmentX(Component.LEFT_ALIGNMENT);
            this.add(imageName);
            imageDate.setAlignmentX(Component.LEFT_ALIGNMENT);
            this.add(imageDate);
            imageRate.setAlignmentX(Component.LEFT_ALIGNMENT);
            this.add(this.imageRate);
        }

        //list layout
        else{
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            this.setAlignmentX(Component.CENTER_ALIGNMENT);
            JPanel right = new JPanel();
            right.setLayout(new BoxLayout(right,BoxLayout.Y_AXIS));
            right.add(imageName);
            imageName.setAlignmentX(Component.CENTER_ALIGNMENT);
            right.add(imageDate);
            imageDate.setAlignmentX(Component.CENTER_ALIGNMENT);
            right.add(imageRate);
            right.setBackground(Color.WHITE);
            this.add(picture);
            this.add(right);
        }

        //this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setBackground(Color.white);
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }


    @Override
    public void update(Action a) {

    }
}
