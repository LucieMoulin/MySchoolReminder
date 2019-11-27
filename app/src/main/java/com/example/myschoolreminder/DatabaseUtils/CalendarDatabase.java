/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 13.11.2019
 * Description : Databse
 */

package com.example.myschoolreminder.DatabaseUtils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.myschoolreminder.DataAccessObjects.CheckListElementDAO;
import com.example.myschoolreminder.DataAccessObjects.ClassDAO;
import com.example.myschoolreminder.DataAccessObjects.EventDAO;
import com.example.myschoolreminder.DataAccessObjects.HolidayDAO;
import com.example.myschoolreminder.DataAccessObjects.HomeworkDAO;
import com.example.myschoolreminder.DataAccessObjects.ReminderDAO;
import com.example.myschoolreminder.DataAccessObjects.RepetitionDAO;
import com.example.myschoolreminder.DataAccessObjects.ScheduleDAO;
import com.example.myschoolreminder.DataAccessObjects.TeacherDAO;
import com.example.myschoolreminder.DataAccessObjects.TripDAO;
import com.example.myschoolreminder.Objects.CheckListElement;
import com.example.myschoolreminder.Objects.Class;
import com.example.myschoolreminder.Objects.Event;
import com.example.myschoolreminder.Objects.Holiday;
import com.example.myschoolreminder.Objects.Homework;
import com.example.myschoolreminder.Objects.Reminder;
import com.example.myschoolreminder.Objects.Repetition;
import com.example.myschoolreminder.Objects.Schedule;
import com.example.myschoolreminder.Objects.Teacher;
import com.example.myschoolreminder.Objects.Trip;

@Database(entities = {Event.class, Class.class, Holiday.class, Homework.class, Reminder.class, Repetition.class, Schedule.class, Teacher.class, Trip.class, CheckListElement.class}, exportSchema = true, version = 1)
@TypeConverters({Converters.class})
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

    /**
     * Check list element data access object
     * @return
     */
    public abstract CheckListElementDAO checkListElementDAO();
}
