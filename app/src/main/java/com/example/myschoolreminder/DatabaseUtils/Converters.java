/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 20.11.2019
 * Description : Type converters for RoomDatabase
 */

package com.example.myschoolreminder.DatabaseUtils;
import androidx.room.TypeConverter;

import com.example.myschoolreminder.Objects.RepetitionType;

import java.util.Date;

/**
 * Type converters for RoomDatabase
 */
public class Converters {
    /**
     * Converts a long value to a date
     * @param value
     * @return
     */
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    /**
     * Converts a date to a long value
     * @param date
     * @return
     */
    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    /**
     * Converts a repetition type to a string
     * @param type
     * @return
     */
    @TypeConverter
    public static String typeToString(RepetitionType type){
        return type.getName();
    }

    /**
     * Converts a string to a repetition type
     * @param value
     * @return
     */
    @TypeConverter
    public static RepetitionType stringToType(String value){
        //return RepetitionType.valueOf(value);
        switch(value){
            default:
            case "Aucune":
                return RepetitionType.NONE;

            case "Journalière":
                return RepetitionType.DAILY;

            case "Hebdomadaire":
                return RepetitionType.WEEKLY;

            case "Mensuelle":
                return RepetitionType.MONTHLY;

            case "Annuelle":
                return RepetitionType.YEARLY;
        }
    }
}
