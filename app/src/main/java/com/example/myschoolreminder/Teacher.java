/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 06.11.2019
 * Description : Teacher
 */

package com.example.myschoolreminder;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Teacher
 */
@Entity(tableName = "t_teacher")
public class Teacher {

    /**
     * id
     */
    @PrimaryKey(autoGenerate = true)
    private int idTeacher;

    /**
     * Last name
     */
    @ColumnInfo(name = "teaLastName")
    private String lastName;

    /**
     * First name
     */
    @ColumnInfo(name = "teaFirstName")
    private String firstName;

    /**
     * Constructor
     * @param lastName
     * @param firstName
     */
    public Teacher(String lastName, String firstName){
        this.lastName = lastName;
        this.firstName = firstName;
    }

    /**
     * Sets the last name
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the last name
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the first name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the first name
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the id
     * @return
     */
    public int getIdTeacher() {
        return idTeacher;
    }

    /**
     * Sets the id
     * @param idTeacher
     */
    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }
}
