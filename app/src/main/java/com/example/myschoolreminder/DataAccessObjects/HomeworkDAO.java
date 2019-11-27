/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 13.11.2019
 * Description : Homework Data Access Object
 */

package com.example.myschoolreminder.DataAccessObjects;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myschoolreminder.Objects.Homework;

import java.util.List;

/**
 * Homework Data Access Object
 */
@Dao
public interface HomeworkDAO {

    /**
     * Gets all the homework
     * @return
     */
    @Query("SELECT idEvent, eveName, eveDescription, evePlace, remIsDone, fkClass FROM t_homework")
    List<Homework> getHomework();

    /**
     * Inserts homework
     * @param homework
     */
    @Insert
    void insertHomework(Homework homework);

    /**
     * Updates homework
     * @param homework
     */
    @Update
    void updateHomework(Homework homework);

    /**
     * Deletes homework
     * @param homework
     */
    @Delete
    void deleteHomework(Homework homework);
}
