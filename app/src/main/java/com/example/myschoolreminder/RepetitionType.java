/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 06.11.2019
 * Description : Types of repetition
 */

package com.example.myschoolreminder;

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
