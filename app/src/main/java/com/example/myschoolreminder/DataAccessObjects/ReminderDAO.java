/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 13.11.2019
 * Description : Reminder Data Access Object
 */

package com.example.myschoolreminder.DataAccessObjects;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myschoolreminder.Objects.Reminder;

import java.util.List;

/**
 * Reminder Data Access Object
 */
@Dao
public interface ReminderDAO {

    /**
     * Gets all the reminders
     * @return
     */
    @Query("SELECT idEvent, eveName, eveDescription, evePlace, remIsDone FROM t_reminder")
    List<Reminder> getReminders();

    /**
     * Inserts a reminder
     * @param reminder
     */
    @Insert
    void insertReminder(Reminder reminder);

    /**
     * Updates a reminder
     * @param reminder
     */
    @Update
    void updateReminder(Reminder reminder);

    /**
     * Deletes a reminder
     * @param reminder
     */
    @Delete
    void deleteReminder(Reminder reminder);
}
