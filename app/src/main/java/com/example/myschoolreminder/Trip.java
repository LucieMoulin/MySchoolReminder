/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 06.11.2019
 * Description : Teacher
 */

package com.example.myschoolreminder;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Trip
 */
public class Trip extends Event{
    /**
     * Checklist
     */
    Map<String, Boolean> checklist;

    /**
     * Constructor
     * @param name
     * @param description
     * @param place
     * @param startDate
     * @param endDate
     * @param checklist
     */
    public Trip(String name, String description, String place, Date startDate, Date endDate, ArrayList<String> checklist) {
        super(name, description, place);

        //Creation of the schedule
        final Repetition repetition = new Repetition(RepetitionType.None, 0, true);
        Schedule schedule = new Schedule(startDate, endDate, repetition);
        addSchedule(schedule);

        //Creation of the checklist
        this.checklist = new HashMap<String, Boolean>();
        for(Iterator<String> iterator = checklist.iterator(); iterator.hasNext();){
            String string = iterator.next();
            this.checklist.put(string, false);
        }
    }
}
