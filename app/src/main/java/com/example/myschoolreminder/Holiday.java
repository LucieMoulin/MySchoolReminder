/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 06.11.2019
 * Description : Holiday
 */

package com.example.myschoolreminder;

import androidx.room.Entity;

import java.util.Date;

/**
 * Holiday
 */
@Entity(tableName = "t_holiday")
public class Holiday extends Event{
    /**
     * Constructor
     * @param name
     * @param startDate date de début
     * @param endDate date de fin
     */
    public Holiday(String name, Date startDate, Date endDate) {
        super(name, "");

        Schedule schedule = new Schedule(startDate, endDate, this);
        Repetition repetition = new Repetition(schedule, RepetitionType.None, 0, true);
    }
}
