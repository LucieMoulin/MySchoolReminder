/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 30.10.2019
 * Description : Class
 */

package com.example.myschoolreminder;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import static androidx.room.ForeignKey.SET_DEFAULT;

/**
 * Class
 */
@Entity(tableName = "t_class", foreignKeys = @ForeignKey(entity = Teacher.class, parentColumns = "idTeacher", childColumns = "fkTeacher", onDelete = SET_DEFAULT))
public class Class extends Event{
    /**
     * Teacher
     */
    @ColumnInfo(name = "fkTeacher")
    private int teacherId;

    /**
     * Constructor
     * @param name
     * @param teacher
     */
    public Class(String name, Teacher teacher){
        super(name, "");
        this.teacherId = teacher.getIdTeacher();
    }

    /**
     * Constructor (with place)
     * @param name
     * @param place
     * @param teacher
     */
    public Class(String name, String place, Teacher teacher) {
        super(name,"", place);
        this.teacherId = teacher.getIdTeacher();
    }

    /**
     * Changes the teacher for a new one
     * @param newTeacher
     */
    public void changeTeacher(Teacher newTeacher){
        teacherId = newTeacher.getIdTeacher();
    }
}