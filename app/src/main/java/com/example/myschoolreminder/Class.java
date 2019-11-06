/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 30.10.2019
 * Description : Class
 */

package com.example.myschoolreminder;

import java.util.ArrayList;

/**
 * Class
 */
public class Class extends Event{
    /**
     * Homework for this class
     */
    private ArrayList<Homework> homework;

    /**
     * Teacher
     */
    private Teacher teacher;

    /**
     * Constructor
     * @param name
     * @param teacher
     */
    public Class(String name, Teacher teacher){
        super(name, "");
        this.teacher = teacher;
    }

    /**
     * Constructor (with place)
     * @param name
     * @param place
     * @param teacher
     */
    public Class(String name, String place, Teacher teacher) {
        super(name,"", place);
        this.teacher = teacher;
    }

    /**
     * Gets the homework for this class
     * @return
     */
    public ArrayList<Homework> getHomework() {
        return homework;
    }

    /**
     * Adds homework to the class
     * @param homework
     */
    public void addHomeWork(Homework homework){
        this.homework.add(homework);
    }

    /**
     * Changes the teacher for a new one
     * @param newTeacher
     */
    public void changeTeacher(Teacher newTeacher){
        teacher = newTeacher;
    }
}