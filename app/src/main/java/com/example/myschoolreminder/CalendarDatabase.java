/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 13.11.2019
 * Description : Databse
 */

package com.example.myschoolreminder;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.AbstractCollection;

@Database(entities = {Event.class, Class.class, Holiday.class, Homework.class, Reminder.class, Repetition.class, Schedule.class, Teacher.class, Trip.class}, exportSchema = false, version = 1)
public abstract class CalendarDatabase extends RoomDatabase {

    /**
     * Database name
     */
    private static String DB_NAME = "dbCalendar";

    /**
     * Singleton
     */
    private static CalendarDatabase instance;

    /**
     * Gets the instance of the database
     * @param context
     * @return
     */
    public static synchronized CalendarDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), CalendarDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
        }

        return instance;
    }

    /**
     * Event Data Access Object
     * @return
     */
    public abstract EventDAO eventDAO();

    /**
     * Class Data Access Object
     * @return
     */
    public abstract ClassDAO classDAO();

    /**
     * Holiday Data Access Object
     * @return
     */
    public abstract HolidayDAO holidayDAO();

    /**
     * Homework Data Access Object
     * @return
     */
    public abstract HomeworkDAO homeworkDAO();

    /**
     * Reminder Data Access Object
     * @return
     */
    public abstract ReminderDAO reminderDAO();

    /**
     * Repetition Data Access Object
     * @return
     */
    public abstract RepetitionDAO repetitionDAO();

    /**
     * Schedule Data Access Object
     * @return
     */
    public abstract ScheduleDAO scheduleDAO();

    /**
     * Teacher Data Access Object
     * @return
     */
    public abstract TeacherDAO teacherDAO();

    /**
     * Trip Data Access Object
     * @return
     */
    public abstract TripDAO tripDAO();
}
