/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 13.11.2019
 * Description : Task getting events
 */

package com.example.myschoolreminder.DatabaseUtils;

import android.content.Context;
import android.os.AsyncTask;

import com.example.myschoolreminder.Objects.Event;
import com.example.myschoolreminder.ObjectsAsyncReturnInterfaces.GetEventsAsyncReturn;

import java.util.List;

/**
 * Task getting events
 */
public class TaskGetEvents extends AsyncTask<Context, Void, List<Event>> {

    public GetEventsAsyncReturn delegate;

    /**
     * Gets the events asynchronously
     * @param contexts
     * @return
     */
    @Override
    protected List<Event> doInBackground(Context... contexts) {
        CalendarDatabase database = CalendarDatabase.getInstance(contexts[0]);

        return database.eventDAO().getEvents();
    }

    /**
     * Launches the delegate method that treats the data
     * @param output
     */
    @Override
    protected void onPostExecute(List<Event> output) {
        delegate.returnEvents(output);
    }
}
