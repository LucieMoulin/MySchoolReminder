/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 13.11.2019
 * Description : Event Data Access Object
 */

package com.example.myschoolreminder.DataAccessObjects;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myschoolreminder.Objects.Event;

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
     * Gets all the events by their id
     * @param ids The list of  ids of the wanted events
     * @return The list of events
     */
    @Query("SELECT idEvent, eveName, eveDescription, evePlace FROM t_event WHERE idEvent IN (:ids)")
    List<Event> getEventsByIds(List<Integer> ids);

    /**
     * Inserts an event
     * @param event
     */
    @Insert
    long insertEvent(Event event);

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

    /**
     * Deletes all events
     */
    @Query("DELETE FROM t_event")
    int deleteAllEvent();
}
