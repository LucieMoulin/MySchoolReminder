/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 30.10.2019
 * Description : Homework
 */

package com.example.myschoolreminder;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import static androidx.room.ForeignKey.CASCADE;

/**
 * HomeWork
 */
@Entity(tableName = "t_homework", foreignKeys = @ForeignKey(entity = Class.class, parentColumns = "idClass", childColumns = "fkClass", onDelete = CASCADE))
public class Homework extends Reminder{
    /**
     * Class for which this homework is
     */
    @ColumnInfo(name = "fkClass")
    private int classId;

    /**
     * Constructor
     * @param name
     * @param description
     * @param container class for which the homework is
     */
    public Homework(String name, String description, Class container) {
        super(name, description);
        this.classId = container.getIdEvent();
    }

    /**
     * Gets the class id
     * @return
     */
    public int getClassId() {
        return classId;
    }
}
