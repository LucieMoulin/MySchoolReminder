/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 15.12.2019
 * Description : Task getting schedules before a date (including the date)
 */

package com.example.myschoolreminder.DatabaseUtils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import com.example.myschoolreminder.Objects.Schedule;

import java.sql.Date;
import java.util.List;

public class TaskGetSchedulesBeforeDate extends AsyncTask<Pair<Context, Date>, Void, List<Schedule>> {

    /**
     * Get the schedules before a date asynchronously
     *
     * @param pairs pairs of context and the selected date
     * @return
     */
    @Override
    protected List<Schedule> doInBackground(Pair<Context, Date>... pairs) {
        CalendarDatabase database = CalendarDatabase.getInstance(pairs[0].first);
        return database.scheduleDAO().getScheduleBeforeDate(pairs[0].second);
    }
}
