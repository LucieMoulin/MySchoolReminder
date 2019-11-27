/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 13.11.2019
 * Description : Teacher Data Access Object
 */

package com.example.myschoolreminder.DataAccessObjects;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myschoolreminder.Objects.Teacher;

import java.util.List;

/**
 * Teacher Data Access Object
 */
@Dao
public interface TeacherDAO {

    /**
     * Gets all the teachers
     * @return
     */
    @Query("SELECT idTeacher, teaFirstName, teaLastName FROM t_teacher")
    List<Teacher> getTeachers();

    /**
     * Inserts a teacher
     * @param teacher
     */
    @Insert
    void insertTeacher(Teacher teacher);

    /**
     * Updates a teacher
     * @param teacher
     */
    @Update
    void updateTeacher(Teacher teacher);

    /**
     * Deletes a teacher
     * @param teacher
     */
    @Delete
    void deleteTeacher(Teacher teacher);
}
