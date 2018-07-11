package model;

import ui.ImageView;
import ui.RatingPanel;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Model implements Serializable {
    private transient ArrayList<Observer> observers;
    private int curFilter;
    private boolean isListView;
    private  ArrayList<ImageModel> listOfImages;

    public Model(){
        this.observers = new ArrayList<>();
        this.curFilter = -1;
        this.isListView = false;
        listOfImages = new ArrayList<>();
    }

    public boolean getIsListView() {
        return isListView;
    }

    public void setListView(boolean b) {
        boolean cur = isListView;
        this.isListView = b;
        if (cur != b) {
            notifyObservers(Action.ChangeLayout);
        }
    }

    public int getCurFilter() {
        return curFilter;
    }

    public void setCurFilter(int i){
        this.curFilter = i;
        this.notifyObservers(Action.SetFilter);
    }

    public ArrayList<ImageModel> getListOfImages() {
        return listOfImages;
    }

    public void addListOfImages(ImageModel imageModel) {
        listOfImages.add(imageModel);
    }

    public void notifyObservers(Action a){
        for (Observer observer : this.observers) {
            observer.update(a);
        }
    }

    public void addObserver(Observer ob){
        this.observers.add(ob);
    }

    public void load(Model model) throws IOException {
        this.listOfImages = model.listOfImages;
        for (ImageModel im: this.listOfImages) {
            im.observers = new ArrayList<>();
                System.out.format("add!");
                ImageView imageView = new ImageView(im, model);
                im.addObserver(imageView);
        }
        this.notifyObservers(Action.AddImage);
    }


}
