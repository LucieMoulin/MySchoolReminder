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

    /**
     * Gets the start date
     * @param startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the start date
     * @return
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the end date
     * @param endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the end date
     * @return
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Gets the repetition
     * @return
     */
    public Repetition getRepetition() {
        return repetition;
    }
}
