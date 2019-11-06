/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 30.10.2019
 * Description : Homework
 */

package com.example.myschoolreminder;

/**
 * HomeWork
 */
public class Homework extends Reminder{
    /**
     * Class for which this homework is
     */
    private Class container;

    /**
     * Constructor
     * @param name
     * @param description
     * @param container class for which the homework is
     */
    public Homework(String name, String description, Class container) {
        super(name, description);
        this.container = container;
    }
}
