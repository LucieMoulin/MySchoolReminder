/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 30.10.2019
 * Description : Schedule
 */

package com.example.myschoolreminder;

import java.util.Date;

/**
 * Schedule
 */
public class Schedule {
    /**
     * Start date
     */
    private Date startDate;

    /**
     * End date
     */
    private Date endDate;

    /**
     * Repetition
     */
    private Repetition repetition;

    /**
     * Constructor
     * @param startDate Start date
     * @param endDate End date
     * @param repetition Repetition
     */
    public Schedule(Date startDate, Date endDate, Repetition repetition){
        this.startDate = startDate;
        this.endDate = endDate;
        this.repetition = repetition;
    }
}
