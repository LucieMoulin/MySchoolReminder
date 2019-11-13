/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 13.11.2019
 * Description : Task getting events
 */

package com.example.myschoolreminder;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import java.util.List;

/**
 * Task getting events
 */
public class TaskGetEvents extends AsyncTask<Context, Void, List<Event>> {

    public GetEventsAsyncReturn delegate;

    /**
     * Gets the events
     * @param contexts
     * @return
     */
    @Override
    protected List<Event> doInBackground(Context... contexts) {
        CalendarDatabase database = CalendarDatabase.getInstance(contexts[0]);

        return database.eventDAO().getEvents();
    }

    @Override
    protected void onPostExecute(List<Event> output) {
        delegate.returnEvents(output);
    }
}
