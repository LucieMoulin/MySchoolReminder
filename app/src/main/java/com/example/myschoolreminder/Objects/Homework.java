/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 30.10.2019
 * Description : Homework
 */

package com.example.myschoolreminder.Objects;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import static androidx.room.ForeignKey.CASCADE;

/**
 * HomeWork
 */
@Entity(tableName = "t_homework", foreignKeys = @ForeignKey(entity = Class.class, parentColumns = "idEvent", childColumns = "fkClass", onDelete = CASCADE), indices = @Index(value = "fkClass"), inheritSuperIndices = true)
public class Homework extends Reminder {
    /**
     * Class for which this homework is
     */
    @ColumnInfo(name = "fkClass")
    private int classId;

    /**
     * Constructor
     * @param name
     * @param description
     * @param classId id of the class for which the homework is
     */
    public Homework(String name, String description, int classId) {
        super(name, description);
        this.classId = classId;
    }

    /**
     * Gets the class id
     * @return
     */
    public int getClassId() {
        return classId;
    }
}
