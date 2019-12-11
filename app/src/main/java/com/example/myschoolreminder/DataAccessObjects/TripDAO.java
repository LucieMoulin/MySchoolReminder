/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 13.11.2019
 * Description : Trip Data Access Object
 */

package com.example.myschoolreminder.DataAccessObjects;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myschoolreminder.Objects.Trip;

import java.util.List;

/**
 * Trip Data Access Object
 */
@Dao
public interface TripDAO {

    /**
     * Gets all the trips
     * @return
     */
    @Query("SELECT idEvent, eveName, eveDescription, evePlace FROM t_trip")
    List<Trip> getTrips();

    /**
     * Inserts a trip
     * @param trip
     */
    @Insert
    long insertTrip(Trip trip);

    /**
     * Updates a trip
     * @param trip
     */
    @Update
    void updateTrip(Trip trip);

    /**
     * Deletes a trip
     * @param trip
     */
    @Delete
    void deleteTrip(Trip trip);
}
