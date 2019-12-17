/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 15.12.2019
 * Description : Task getting events
 */

package com.example.myschoolreminder.DatabaseUtils;

import android.content.Context;
import android.os.AsyncTask;

import com.example.myschoolreminder.Objects.Event;

import java.util.List;

public class TaskGetEvents extends AsyncTask<Context, Void, List<Event>> {

    /**
     * Get the events asynchronously
     * @param contexts The context
     * @return
     */
    @Override
    protected List<Event> doInBackground(Context... contexts) {
        CalendarDatabase database = CalendarDatabase.getInstance(contexts[0]);
        return database.eventDAO().getEvents();
    }

}