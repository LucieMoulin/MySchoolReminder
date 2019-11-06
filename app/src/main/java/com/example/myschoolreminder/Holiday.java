/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 06.11.2019
 * Description : Holiday
 */

package com.example.myschoolreminder;

import java.util.Date;

/**
 * Holiday
 */
public class Holiday extends Event{
    /**
     * Constructor
     * @param name
     * @param startDate date de début
     * @param endDate date de fin
     */
    public Holiday(String name, Date startDate, Date endDate) {
        super(name, "");

        Repetition repetition = new Repetition(RepetitionType.None, 0, true);
        Schedule schedule = new Schedule(startDate, endDate, repetition);
        addSchedule(schedule);
    }
}
