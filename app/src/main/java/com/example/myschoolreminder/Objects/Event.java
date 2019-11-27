/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 30.10.2019
 * Description : Event
 */

package com.example.myschoolreminder.Objects;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Event
 */
 @Entity(tableName = "t_event", indices = @Index(unique = true, value = "idEvent"))
public class Event {

    /**
     * Unspecified place
     */
    private static final String UNSPECIFIED_PLACE = "Non spécifié";

    /**
     * Id
     */
    @PrimaryKey(autoGenerate = true)
    private int idEvent;

    /**
     * Name
     */
    @ColumnInfo(name = "eveName")
    private String name;

    /**
     * Description
     */
    @ColumnInfo(name = "eveDescription")
    private String description;

    /**
     * Place where the event takes place
     */
    @ColumnInfo(name = "evePlace")
    private String place;

    /**
     * Constructor
     * @param name
     * @param description
     */
    public Event(String name, String description){
        this.name = name;
        this.description = description;
        this.place = UNSPECIFIED_PLACE;
    }

    /**
     * Constructor (with specified place)
     * @param name
     * @param description
     * @param place place where the event takes place
     */
    @Ignore
    public Event(String name, String description, String place){
        this.name = name;
        this.description = description;
        this.place = place;
    }

    /**
     * Gets the name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the event
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the place
     * @return
     */
    public String getPlace() {
        return place;
    }

    /**
     * Sets the place
     * @param place
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * Gets the id
     * @return
     */
    public int getIdEvent() {
        return idEvent;
    }

    /**
     * Sets the id
     * @param idEvent
     */
    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }
}
