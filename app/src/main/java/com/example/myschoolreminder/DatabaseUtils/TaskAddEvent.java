/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 11.12.2019
 * Description : Task adding events
 */

package com.example.myschoolreminder.DatabaseUtils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import com.example.myschoolreminder.Objects.Event;
import com.example.myschoolreminder.Objects.Teacher;

/**
 * Task adding events
 */
public class TaskAddEvent extends AsyncTask<Pair<Context, Event>, Void, Long> {
    /**
     * Adds an event asynchronously
     * @param pairs
     * @return
     */
    @Override
    protected Long doInBackground(Pair<Context, Event>... pairs) {
        CalendarDatabase database = CalendarDatabase.getInstance(pairs[0].first);

        Event event = new Event(pairs[0].second.getName(), pairs[0].second.getDescription(), pairs[0].second.getPlace());

        Long id = database.eventDAO().insertEvent(event);

        return id;
    }
}
