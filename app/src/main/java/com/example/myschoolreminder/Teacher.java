/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 06.11.2019
 * Description : Teacher
 */

package com.example.myschoolreminder;

/**
 * Teacher
 */
public class Teacher {
    /**
     * Last name
     */
    private String lastName;

    /**
     * First name
     */
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
}
