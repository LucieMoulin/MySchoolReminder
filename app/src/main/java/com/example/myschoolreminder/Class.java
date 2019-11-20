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
import androidx.room.Ignore;
import androidx.room.Index;

import static androidx.room.ForeignKey.SET_DEFAULT;

/**
 * Class
 */
@Entity(tableName = "t_class", foreignKeys = @ForeignKey(entity = Teacher.class, parentColumns = "idTeacher", childColumns = "fkTeacher", onDelete = SET_DEFAULT), indices = @Index(value = "fkTeacher"), inheritSuperIndices = true)
public class Class extends Event{
    /**
     * Teacher
     */
    @ColumnInfo(name = "fkTeacher")
    private int teacherId;

    /**
     * Constructor
     * @param name
     * @param teacherId
     */
    public Class(String name, int teacherId){
        super(name, "");
        this.teacherId = teacherId;
    }

    /**
     * Constructor (with place)
     * @param name
     * @param place
     * @param teacherId
     */
    @Ignore
    public Class(String name, String place, int teacherId) {
        super(name,"", place);
        this.teacherId = teacherId;
    }

    /**
     * Changes the teacher for a new one
     * @param newTeacher
     */
    public void changeTeacher(Teacher newTeacher){
        teacherId = newTeacher.getIdTeacher();
    }

    /**
     * Gets the teacher id
     * @return
     */
    public int getTeacherId() {
        return teacherId;
    }
}