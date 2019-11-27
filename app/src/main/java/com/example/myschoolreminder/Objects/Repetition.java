/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 30.10.2019
 * Description : Repetition
 */

package com.example.myschoolreminder.Objects;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Repetition
 */
@Entity(tableName = "t_repetition", foreignKeys = @ForeignKey(entity = Schedule.class, parentColumns = "idSchedule", childColumns = "fkSchedule", onDelete = CASCADE), indices = {@Index(unique = true, value = "idRepetition"), @Index(value = "fkSchedule")})
public class Repetition {

    /**
     * id
     */
    @PrimaryKey(autoGenerate = true)
    private int idRepetition;

    /**
     * Amount
     */
    @ColumnInfo(name = "repAmount")
    private int amount;

    /**
     * Date when the repetition stops
     */
    @ColumnInfo(name = "repUntil")
    private Date until;

    /**
     * Maximum of repetitions
     */
    @ColumnInfo(name = "repMaximum")
    private int maximum;

    /**
     * Defines if the repetition is active during the holidays
     */
    @ColumnInfo(name = "repIsActiveDuringHolidays")
    private Boolean isActiveDuringHolidays;

    /**
     * Type (daily, weekly, etc)
     */
    @ColumnInfo(name = "repRepetitionType")
    private RepetitionType type;

    /**
     * Schedule id
     */
    @ColumnInfo(name = "fkSchedule")
    private int scheduleId;

    /**
     * Constructor
     * @param scheduleId
     * @param type type of repetition
     * @param amount amount of type (days, weeks, months)
     * @param isActiveDuringHolidays Defines if it's active during the holidays
     */
    public Repetition(int scheduleId, RepetitionType type, int amount, Boolean isActiveDuringHolidays){
        this.scheduleId = scheduleId;
        this.type = type;
        this.amount = amount;
        this.until = null;
        this.maximum = -1;
        this.isActiveDuringHolidays = isActiveDuringHolidays;
    }

    /**
     * Constructor (With end date)
     * @param scheduleId
     * @param type type of repetition
     * @param amount amount of type (days, weeks, months)
     * @param until Date when the repetition stops
     * @param isActiveDuringHolidays Defines if it's active during the holidays
     */
    @Ignore
    public Repetition(int scheduleId, RepetitionType type, int amount, Date until, Boolean isActiveDuringHolidays){
        this.scheduleId = scheduleId;
        this.type = type;
        this.amount = amount;
        this.until = until;
        this.maximum = -1;
        this.isActiveDuringHolidays = isActiveDuringHolidays;
    }

    /**
     * Constructor (With maximum of repetitions)
     * @param scheduleId
     * @param type type of repetition
     * @param amount amount of type (days, weeks, months)
     * @param maximum Maximum of repetitions
     * @param isActiveDuringHolidays Defines if it's active during the holidays
     */
    @Ignore
    public Repetition(int scheduleId, RepetitionType type, int amount, int maximum, Boolean isActiveDuringHolidays){
        this.scheduleId = scheduleId;
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

    /**
     * Gets the schedule id
     * @return
     */
    public int getScheduleId() {
        return scheduleId;
    }

    /**
     * Gets the id
     * @return
     */
    public int getIdRepetition() {
        return idRepetition;
    }

    /**
     * Sets the id
     * @param idRepetition
     */
    public void setIdRepetition(int idRepetition) {
        this.idRepetition = idRepetition;
    }
}