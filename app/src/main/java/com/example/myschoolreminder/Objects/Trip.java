/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 06.11.2019
 * Description : Teacher
 */

package com.example.myschoolreminder.Objects;

import androidx.room.Entity;
import androidx.room.Ignore;

import java.util.ArrayList;
import java.util.Date;

/**
 * Trip
 */
@Entity(tableName = "t_trip", inheritSuperIndices = true)
public class Trip extends Event {

    /**
     * Constructor
     * @param name
     * @param description
     * @param place
     */
    public Trip(String name, String description, String place){
        super(name, description, place);
    }

    /**
     * Constructor (with schedule and checklist)
     * @param name
     * @param description
     * @param place
     * @param startDate
     * @param endDate
     * @param checklist
     */
    @Ignore
    public Trip(String name, String description, String place, Date startDate, Date endDate, ArrayList<String> checklist) {
        super(name, description, place);

        //Creation of the schedule
        Schedule schedule = new Schedule(startDate, endDate, getIdEvent());
        Repetition repetition = new Repetition(schedule.getIdSchedule(), RepetitionType.NONE, 0, true);

        //Creation of the checklist
        for (String string : checklist) {
            new CheckListElement(false, string, getIdEvent());
        }
    }
}
