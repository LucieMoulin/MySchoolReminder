/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 04.12.2019
 * Description : Types of events
 */
package com.example.myschoolreminder.Objects;

/**
 * Types of event (name and the class corresponding to it)
 */
public enum EventType {
    TRIP("Voyage", Trip.class),
    HOLIDAY("Vacances", Holiday.class),
    CLASS("Cours", Class.class),
    HOMEWORK("Devoirs", Homework.class),
    REMINDER("Rappel", Reminder.class),
    EVENT("Événement", Event.class);

    /**
     * The event's name
     */
    private String name;

    /**
     * The class corresponding to the event
     */
    private java.lang.Class class_;

    /**
     * Constructor
     * @param name The event's name
     * @param class_ The name of the class corresponding
     */
    EventType(String name, java.lang.Class class_){
        this.name = name;
        this.class_ = class_;
    }

    /**
     * Get the name
     * @return the name
     */
    public String getName(){return name;}

    /**
     * Get the class
     * @return the class
     */
    public java.lang.Class getClass_(){return class_;}

}

