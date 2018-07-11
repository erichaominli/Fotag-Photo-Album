package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ImageModel  implements Serializable {

    private String imagePath;
    private String fileName;
    private String fileDate;
    private int rating;
    public transient ArrayList<Observer> observers = new ArrayList<>();

    public ImageModel(File file) {
        imagePath = file.getPath();
        fileName = file.getName();
        rating = -1;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        fileDate = dateFormat.format(new Date());
    }

    public void setRating(int rate){
        this.rating = rate;
        this.notifyObservers(Action.ChangeRating);
    }

    public int getRating() {
        return rating;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileDate() {
        return fileDate;
    }


    public void notifyObservers(Action a) {
        for (Observer observer : this.observers) {
            observer.update(a);
        }
    }

    public void addObserver(Observer ob){
        this.observers.add(ob);
    }


}
