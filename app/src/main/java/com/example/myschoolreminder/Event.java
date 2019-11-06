/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 30.10.2019
 * Description : Event
 */

package com.example.myschoolreminder;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Date;

/**
 * Event
 */
public class Event {

    /**
     * Unspecified place
     */
    private static final String UNSPECIFIED_PLACE = "Non spécifié";

    /**
     * Schedule
     */
    private ArrayList<Schedule> schedules;

    /**
     * Name
     */
    private String name;

    /**
     * Description
     */
    private String description;

    /**
     * Place where the event takes place
     */
    private String place;

    /**
     * Constructor
     * @param name
     * @param description
     */
    public Event(String name, String description){
        this.schedules = new ArrayList<Schedule>();
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
    public Event(String name, String description, String place){
        this.schedules = new ArrayList<Schedule>();
        this.name = name;
        this.description = description;
        this.place = place;
    }

    /**
     * Adds a schedule to the event
     * @param schedule
     */
    public void addSchedule(Schedule schedule){
        schedules.add(schedule);
    }

    /**
     * Getter for the schedules
     * @return
     */
    public ArrayList<Schedule> getSchedules(){
        return schedules;
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

    //TODO retirer/modifier un schedule
}
