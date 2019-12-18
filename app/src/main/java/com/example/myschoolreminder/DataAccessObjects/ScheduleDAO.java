/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 13.11.2019
 * Description : Schedule Data Access Object
 */

package com.example.myschoolreminder.DataAccessObjects;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myschoolreminder.Objects.Schedule;

import java.util.Date;
import java.util.List;

/**
 * Schedule Data Access Object
 */
@Dao
public interface ScheduleDAO {

    /**
     * Gets all the schedules
     * @return
     */
    @Query("SELECT idSchedule, schStartDate, schEndDate, fkEvent FROM t_schedule")
    List<Schedule> getSchedules();

    /**
     * Gets all the schedules before the selected date
     * @param selectedDate
     * @return
     */
    @Query("SELECT idSchedule, schStartDate, schEndDate, fkEvent FROM t_schedule WHERE schStartDate <= (:selectedDate)")
    List<Schedule> getScheduleBeforeDate(Date selectedDate);

    /**
     * Get the start day of the next holidays
     * @param currentDate the current date
     * @return The date
     */
    @Query("SELECT schStartDate FROM t_schedule INNER JOIN t_holiday ON idEvent = fkEvent WHERE schStartDate > (:currentDate) ORDER BY schStartDate ASC LIMIT 1")
    Date getNextHolidayStartDay(Date currentDate);

    /**
     * Inserts a schedule
     * @param schedule
     */
    @Insert
    long insertSchedule(Schedule schedule);

    /**
     * Updates a schedule
     * @param schedule
     */
    @Update
    void updateSchedule(Schedule schedule);

    /**
     * Deletes a schedule
     * @param schedule
     */
    @Delete
    void deleteSchedule(Schedule schedule);
}
