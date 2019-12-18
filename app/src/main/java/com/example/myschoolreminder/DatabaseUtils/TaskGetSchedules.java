/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 15.12.2019
 * Description : Task getting schedules
 */

package com.example.myschoolreminder.DatabaseUtils;

import android.content.Context;
import android.os.AsyncTask;

import com.example.myschoolreminder.Objects.Holiday;
import com.example.myschoolreminder.Objects.Schedule;
import com.example.myschoolreminder.ScheduleFragment;

import java.util.List;

public class TaskGetSchedules extends AsyncTask<Context, Void, List<Schedule>> {

    /**
     * Get the holidays asynchronously
     * @param contexts The context
     * @return
     */
    @Override
    protected List<Schedule> doInBackground(Context... contexts) {
        CalendarDatabase database = CalendarDatabase.getInstance(contexts[0]);
        return database.scheduleDAO().getSchedules();
    }

}
