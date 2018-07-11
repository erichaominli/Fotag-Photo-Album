package model;


/**
 * An interface that allows an object to receive updates from the object
 * they listen to.
 */
public interface Observer {
    void update(Action a);
}
