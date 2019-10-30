/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 30.10.2019
 * Description : Repetition
 */

package com.example.myschoolreminder;

import java.util.Date;

/**
 * Repetition
 */
public class Repetition {

    /**
     * Types of repetition
     */
    public enum RepetitionType{
        None("Aucune",""),
        Daily("Journalier","jours"),
        Weekly("Hebdomadaire", "semaines"),
        Monthly("Mensuel", "mois"),
        Yearly("Annuel", "années");

        /**
         * Name
         */
        private String name;

        /**
         * Every What ? Ex : every day, every week
         */
        private String everyWhat;

        /**
         * Constructor
         * @param name
         * @param everyWhat
         */
        RepetitionType(String name, String everyWhat){
            this.name = name;
            this.everyWhat = everyWhat;
        }

        /**
         * Gets the name
         * @return name
         */
        public String getName(){
            return name;
        }

        /**
         * Gets the "every"
         * @return everyWhat
         */
        public String getEveryWhat(){
            return everyWhat;
        }
    }

    /**
     * Amount
     */
    private int amount;

    /**
     * Type (daily, weekly, etc)
     */
    private RepetitionType type;

    /**
     * Date when the repetition stops
     */
    private Date until;

    /**
     * Maximum of repetitions
     */
    private int maximum;

    /**
     * Constructor
     * @param type type of repetition
     * @param amount amount of type (days, weeks, months)
     */
    public Repetition(RepetitionType type, int amount){
        this.type = type;
        this.amount = amount;
        this.until = null;
        this.maximum = -1;
    }

    /**
     * Constructor (With end date)
     * @param type type of repetition
     * @param amount amount of type (days, weeks, months)
     * @param until Date when the repetition stops
     */
    public Repetition(RepetitionType type, int amount, Date until){
        this.type = type;
        this.amount = amount;
        this.until = until;
        this.maximum = -1;
    }

    /**
     * Constructor (With maximum of repetitions)
     * @param type type of repetition
     * @param amount amount of type (days, weeks, months)
     * @param maximum Maximum of repetitions
     */
    public Repetition(RepetitionType type, int amount, int maximum){
        this.type = type;
        this.amount = amount;
        this.until = null;
        this.maximum = maximum;
    }

    //TODO Faire l'histoire du choix de si ça a lieu pendant les vacances où pas
}

