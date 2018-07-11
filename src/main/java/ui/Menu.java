package ui;

import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel {
    private Model model;
    private JToggleButton gridViewBtn;
    private JToggleButton listViewBtn;
    private JLabel title;
    private LoadBtn importBtn;
    private RatingPanel ratePanel;



    public Menu(Model model){
        //initialize
        this.model = model;

        //set gridView Btn
        gridViewBtn = new JToggleButton();
        gridViewBtn.setIcon(new ImageIcon(getClass().getResource("img/gridView.png")));
        gridViewBtn.setSelected(true);
        //gridViewBtn.setBorderPainted(false);
        //gridViewBtn.setFocusPainted(false);
        //gridViewBtn.setContentAreaFilled(false);
        gridViewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listViewBtn.setSelected(false);
                model.setListView(false);
            }
        });

        //set listView Btn
        listViewBtn = new JToggleButton();
        listViewBtn.setIcon(new ImageIcon(getClass().getResource("img/listView.png")));
        //listViewBtn.setBorderPainted(false);
        //listViewBtn.setFocusPainted(false);
        //listViewBtn.setContentAreaFilled(false);
        listViewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridViewBtn.setSelected(false);
                model.setListView(true);
            }
        });

        //set title
        title = new JLabel("FOTAG!");
        title.setFont(new Font("Times New Roman", Font.PLAIN, 36));

        //set load button
        importBtn = new LoadBtn(model);

        //set rating panel
        ratePanel = new RatingPanel(model);

        this.setBackground(Color.WHITE);
        this.setLayout(new BorderLayout());

        JPanel left = new JPanel();
        left.setBackground(Color.WHITE);
        left.add(gridViewBtn);
        left.add(listViewBtn);

        JPanel mid = new JPanel();
        mid.setBackground(Color.WHITE);
        mid.add(title);

        JPanel right = new JPanel();
        right.setBackground(Color.WHITE);
        right.add(importBtn);
        right.add(ratePanel);


        this.add(left, BorderLayout.WEST);
        this.add(mid, BorderLayout.CENTER);
        this.add(right, BorderLayout.EAST);

        this.setVisible(true);
    }


}
