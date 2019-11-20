/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 20.11.2019
 * Description : Checklist element Data Access Object
 */

package com.example.myschoolreminder;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Checklist element Data Access Object
 */
@Dao
public interface CheckListElementDAO {

    /**
     * Gets the checklist elements
     * @return
     */
    @Query("SELECT idCheckListElement, cheIsChecked, cheDescription, fkTrip FROM t_checkListElement")
    List<CheckListElement> getClasses();

    /**
     * Inserts a checklist element
     * @param checkListElementToInsert
     */
    @Insert
    void insertCheckListElement(CheckListElement checkListElementToInsert);

    /**
     * Updates a checklist element
     * @param checkListElementToUpdate
     */
    @Update
    void updateCheckListElement(CheckListElement checkListElementToUpdate);

    /**
     * Deletes a checklist element
     * @param checkListElementToDelete
     */
    @Delete
    void deleteCheckListElement(CheckListElement checkListElementToDelete);
}
