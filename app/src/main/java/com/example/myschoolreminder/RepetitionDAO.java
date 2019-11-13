/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 13.11.2019
 * Description : Repetition Data Access Object
 */

package com.example.myschoolreminder;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Repetition Data Access Object
 */
@Dao
public interface RepetitionDAO {

    /**
     * Gets all the repetitions
     * @return
     */
    @Query("SELECT idRepetition, repAmount, repUntil, repMaximum, repIsActiveDuringHolidays, repRepetitionType, fkSchedule FROM t_repetition")
    List<Repetition> getRepetitions();

    /**
     * Inserts a repetition
     * @param repetition
     */
    @Insert
    void insertRepetition(Repetition repetition);

    /**
     * Updates a repetition
     * @param repetition
     */
    @Update
    void updateRepetition(Repetition repetition);

    /**
     * Deletes a repetition
     * @param repetition
     */
    @Delete
    void deleteRepetition(Repetition repetition);
}
