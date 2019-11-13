/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 30.10.2019
 * Description : Schedule
 */

package com.example.myschoolreminder;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Schedule
 */
@Entity(tableName = "t_schedule", foreignKeys = @ForeignKey(entity = Event.class, parentColumns = "idEvent", childColumns = "fkEvent", onDelete = CASCADE))
public class Schedule {

    /**
     * Id
     */
    @PrimaryKey(autoGenerate = true)
    private int idSchedule;

    /**
     * Start date
     */
    @ColumnInfo(name = "schStartDate")
    private Date startDate;

    /**
     * End date
     */
    @ColumnInfo(name = "schEndDate")
    private Date endDate;

    /**
     * Event id
     */
    @ColumnInfo(name = "fkEvent")
    private int eventId;

    /**
     * Constructor
     * @param startDate Start date
     * @param endDate End date
     * @param event
     */
    public Schedule(Date startDate, Date endDate, Event event){
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventId = event.getIdEvent();
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
     * Gets the id
     * @return
     */
    public int getIdSchedule() {
        return idSchedule;
    }

    /**
     * Gets the event id
     * @return
     */
    public int getEventId() {
        return eventId;
    }
}
