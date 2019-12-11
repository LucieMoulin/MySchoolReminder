/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 11.12.2019
 * Description : Task adding schedules
 */

package com.example.myschoolreminder.DatabaseUtils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import com.example.myschoolreminder.Objects.Event;
import com.example.myschoolreminder.Objects.Schedule;

/**
 * Task adding schedules
 */
public class TaskAddSchedule extends AsyncTask<Pair<Context, Schedule>, Void, Long> {
    /**
     * Adds an schedule asynchronously
     * @param pairs
     * @return
     */
    @Override
    protected Long doInBackground(Pair<Context, Schedule>... pairs) {
        CalendarDatabase database = CalendarDatabase.getInstance(pairs[0].first);

        Schedule schedule = new Schedule(pairs[0].second.getStartDate(), pairs[0].second.getEndDate(), pairs[0].second.getEventId());

        Long id = database.scheduleDAO().insertSchedule(schedule);

        return id;
    }
}
