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
     * Inserts a holiday
     * @param holiday
     */
    @Insert
    void insertHoliday(Holiday holiday);

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
