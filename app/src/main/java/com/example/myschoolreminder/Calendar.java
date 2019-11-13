/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 30.10.2019
 * Description : Calendar
 */

package com.example.myschoolreminder;

import java.util.ArrayList;

/**
 * Calendar
 */
public class Calendar {
    /**
     * Events of the reminder
     */
    private ArrayList<Event> events;

    /**
     * Constructor
     */
    public Calendar(){
        events = new ArrayList<Event>();
    }

    /**
     * Get events
     * @return
     */
    public ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * Adds an event to the reminder
     * @param event
     */
    public void addEvent(Event event){
        events.add(event);
    }

    /**
     * Removes an event form the reminder
     * @param event
     */
    public void removeEvent(Event event){
        if(events.contains(event)){
            events.remove(event);
        }
    }
}
