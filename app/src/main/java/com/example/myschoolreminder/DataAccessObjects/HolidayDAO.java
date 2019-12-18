/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 13.11.2019
 * Description : Holiday Data Access Object
 */

package com.example.myschoolreminder.DataAccessObjects;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myschoolreminder.Objects.Holiday;

import java.util.Date;
import java.util.List;

/**
 * Holiday Data Access Object
 */
@Dao
public interface HolidayDAO {

    /**
     * Gets all the holidays
     * @return
     */
    @Query("SELECT idEvent, eveName, eveDescription, evePlace FROM t_holiday")
    List<Holiday> getHolidays();


    /**
     * Select the amount of holidays for a date (Basically it's to check is a date is during holidays or not)
     * @param selectedDate
     * @return
     */
    @Query("SELECT COUNT(*) FROM t_holiday INNER JOIN t_schedule ON idEvent = fkEvent WHERE schStartDate <= (:selectedDate) AND schEndDate >= (:selectedDate)")
    int isSelectedDateDuringHolidays(Date selectedDate);

    /**
     * Inserts a holiday
     * @param holiday
     */
    @Insert
    long insertHoliday(Holiday holiday);

    /**
     * Updates a holiday
     * @param holiday
     */
    @Update
    void updateHoliday(Holiday holiday);

    /**
     * Deletes a holiday
     * @param holiday
     */
    @Delete
    void deleteHoliday(Holiday holiday);
}
