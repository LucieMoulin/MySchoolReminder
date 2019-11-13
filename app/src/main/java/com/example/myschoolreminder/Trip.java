/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 06.11.2019
 * Description : Teacher
 */

package com.example.myschoolreminder;

import android.provider.CalendarContract;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Trip
 */
@Entity(tableName = "t_trip")
public class Trip extends Event{
    /**
     * Checklist
     */
    @ColumnInfo(name = "triCheckList")
    private Map<String, Boolean> checklist;

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
        Schedule schedule = new Schedule(startDate, endDate, this);
        Repetition repetition = new Repetition(schedule, RepetitionType.None, 0, true);

        //Creation of the checklist
        this.checklist = new HashMap<>();
        for (String string : checklist) {
            this.checklist.put(string, false);
        }
    }
}
