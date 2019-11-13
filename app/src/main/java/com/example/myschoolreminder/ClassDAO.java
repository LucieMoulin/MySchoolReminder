/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 13.11.2019
 * Description : Class Data Access Object
 */

package com.example.myschoolreminder;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Class Data Access Object
 */
@Dao
public interface ClassDAO {

    /**
     * Gets the classes
     * @return
     */
    @Query("SELECT idEvent, eveName, evePlace, eveDescription, fkTeacher FROM t_class")
    List<Class> getClasses();

    /**
     * Inserts a class
     * @param classToInsert
     */
    @Insert
    void insertClass(Class classToInsert);

    /**
     * Updates a class
     * @param classToUpdate
     */
    @Update
    void updateClass(Class classToUpdate);

    /**
     * Deletes a class
     * @param classToDelete
     */
    @Delete
    void deleteClass(Class classToDelete);
}
