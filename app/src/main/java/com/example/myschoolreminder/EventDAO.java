/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 13.11.2019
 * Description : Event Data Access Object
 */

package com.example.myschoolreminder;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Event Data Access Object
 */
@Dao
public interface EventDAO {

    /**
     * Gets all the events
     * @return
     */
    @Query("SELECT idEvent, eveName, eveDescription, evePlace FROM t_event")
    List<Event> getEvents();

    /**
     * Inserts an event
     * @param event
     */
    @Insert
    void insertEvent(Event event);

    /**
     * Updates an event
     * @param event
     */
    @Update
    void updateEvent(Event event);

    /**
     * Deletes an event
     * @param event
     */
    @Delete
    void deleteEvent(Event event);
}