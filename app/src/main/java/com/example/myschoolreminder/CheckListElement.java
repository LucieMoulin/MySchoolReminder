/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 20.11.2019
 * Description : Element of a checklist
 */

package com.example.myschoolreminder;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Element of a checklist
 */
@Entity(tableName = "t_checkListElement", foreignKeys = @ForeignKey(entity = Trip.class, parentColumns = "idEvent", childColumns = "fkTrip", onDelete = CASCADE), indices = {@Index(unique = true, value = "idCheckListElement"), @Index(value = "fkTrip")})
public class CheckListElement {
    /**
     * id
     */
    @PrimaryKey(autoGenerate = true)
    private int idCheckListElement;

    /**
     * Is the element checked
     */
    @ColumnInfo(name = "cheIsChecked")
    private Boolean isChecked;

    /**
     * Description
     */
    @ColumnInfo(name = "cheDescription")
    private String description;

    /**
     * id of the trip
     */
    @ColumnInfo(name = "fkTrip")
    private int tripId;

    /**
     * Constructor
     * @param isChecked
     * @param description
     * @param tripId
     */
    public CheckListElement(Boolean isChecked, String description, int tripId){
        this.isChecked = isChecked;
        this.description = description;
        this.tripId = tripId;
    }

    /**
     * Sets the id
     * @param idCheckListElement
     */
    public void setIdCheckListElement(int idCheckListElement) {
        this.idCheckListElement = idCheckListElement;
    }

    /**
     * Gets the id
     * @return
     */
    public int getIdCheckListElement() {
        return idCheckListElement;
    }

    /**
     * Sets the checked boolean
     * @param checked
     */
    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    /**
     * Gets the checked boolean
     * @return
     */
    public Boolean getChecked() {
        return isChecked;
    }

    /**
     * Sets the description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the description
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the trip id
     * @param tripId
     */
    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    /**
     * Gets the trip id
     * @return
     */
    public int getTripId() {
        return tripId;
    }
}
