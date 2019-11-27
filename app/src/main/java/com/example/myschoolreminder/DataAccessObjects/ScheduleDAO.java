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
     * Inserts a schedule
     * @param schedule
     */
    @Insert
    void insertSchedule(Schedule schedule);

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
