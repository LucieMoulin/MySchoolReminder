/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 06.11.2019
 * Description : Holiday
 */

package com.example.myschoolreminder;

import androidx.room.Entity;
import androidx.room.Ignore;

import java.util.Date;

/**
 * Holiday
 */
@Entity(tableName = "t_holiday", inheritSuperIndices = true)
public class Holiday extends Event{

    /**
     * Constructor
     * @param name
     */
    public Holiday(String name){
        super(name, "");
    }

    /**
     * Constructor (with start and end date)
     * @param name
     * @param startDate date de début
     * @param endDate date de fin
     */
    @Ignore
    public Holiday(String name, Date startDate, Date endDate) {
        super(name, "");

        Schedule schedule = new Schedule(startDate, endDate, this.getIdEvent());
        Repetition repetition = new Repetition(schedule.getIdSchedule(), RepetitionType.None, 0, true);
    }
}
