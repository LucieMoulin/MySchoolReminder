/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 30.10.2019
 * Description : Reminder
 */

package com.example.myschoolreminder;

/**
 * Reminder
 */
public class Reminder extends Event {

    /**
     * Defines if the action in the reminder is isDone
     */
    private Boolean isDone;

    /**
     * Constructor
     * @param name
     */
    public Reminder(String name, String description) {
        super(name, description);
        this.isDone = false;
    }

    /**
     * Gets the state of the reminder
     * @return
     */
    public Boolean getIsDone() {
        return isDone;
    }

    /**
     * Sets the reminder as done
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets the reminder as not done
     */
    public void setNotDone() {
        this.isDone = false;
    }
}
