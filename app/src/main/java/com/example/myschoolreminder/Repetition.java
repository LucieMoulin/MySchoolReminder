/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 30.10.2019
 * Description : Repetition
 */

package com.example.myschoolreminder;

import java.util.Date;

/**
 * Repetition
 */
public class Repetition {

    /**
     * Amount
     */
    private int amount;

    /**
     * Type (daily, weekly, etc)
     */
    private RepetitionType type;

    /**
     * Date when the repetition stops
     */
    private Date until;

    /**
     * Maximum of repetitions
     */
    private int maximum;

    /**
     * Defines if the repetition is active during the holidays
     */
    private Boolean isActiveDuringHolidays;

    /**
     * Constructor
     * @param type type of repetition
     * @param amount amount of type (days, weeks, months)
     * @param isActiveDuringHolidays Defines if it's active during the holidays
     */
    public Repetition(RepetitionType type, int amount, Boolean isActiveDuringHolidays){
        this.type = type;
        this.amount = amount;
        this.until = null;
        this.maximum = -1;
        this.isActiveDuringHolidays = isActiveDuringHolidays;
    }

    /**
     * Constructor (With end date)
     * @param type type of repetition
     * @param amount amount of type (days, weeks, months)
     * @param until Date when the repetition stops
     * @param isActiveDuringHolidays Defines if it's active during the holidays
     */
    public Repetition(RepetitionType type, int amount, Date until, Boolean isActiveDuringHolidays){
        this.type = type;
        this.amount = amount;
        this.until = until;
        this.maximum = -1;
        this.isActiveDuringHolidays = isActiveDuringHolidays;
    }

    /**
     * Constructor (With maximum of repetitions)
     * @param type type of repetition
     * @param amount amount of type (days, weeks, months)
     * @param maximum Maximum of repetitions
     * @param isActiveDuringHolidays Defines if it's active during the holidays
     */
    public Repetition(RepetitionType type, int amount, int maximum, Boolean isActiveDuringHolidays){
        this.type = type;
        this.amount = amount;
        this.until = null;
        this.maximum = maximum;
        this.isActiveDuringHolidays = isActiveDuringHolidays;
    }

    /**
     * Sets the amount
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Gets the amount
     * @return
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the type
     * @param type
     */
    public void setType(RepetitionType type) {
        this.type = type;
    }

    /**
     * Gets the type
     * @return
     */
    public RepetitionType getType() {
        return type;
    }

    /**
     * Sets the date when the repetition stops
     * @param until
     */
    public void setUntil(Date until) {
        this.until = until;
    }

    /**
     * Gets the date when the repetition stops
     * @return
     */
    public Date getUntil() {
        return until;
    }

    /**
     * Sets the maximum of repetitions
     * @param maximum
     */
    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    /**
     * Gets the maximum of repetitions
     * @return
     */
    public int getMaximum() {
        return maximum;
    }

    /**
     * Sets the boolean that defines if the repetition is active during the holidays
     * @param activeDuringHolidays
     */
    public void setActiveDuringHolidays(Boolean activeDuringHolidays) {
        isActiveDuringHolidays = activeDuringHolidays;
    }

    /**
     * Gets the boolean that defines if the repetition is active during the holidays
     * @return
     */
    public Boolean getActiveDuringHolidays() {
        return isActiveDuringHolidays;
    }
}