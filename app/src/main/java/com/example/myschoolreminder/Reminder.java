/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 30.10.2019
 * Description : Reminder
 */

package com.example.myschoolreminder;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

/**
 * Reminder
 */
@Entity(tableName = "t_reminder", inheritSuperIndices = true)
public class Reminder extends Event {

    /**
     * Defines if the action in the reminder is isDone
     */
    @ColumnInfo(name = "remIsDone")
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
     * Sets the state of the reminder
     * @param isDone
     */
    public void setIsDone(Boolean isDone){
        this.isDone = isDone;
    }
}
