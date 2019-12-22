/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 13.11.2019
 * Description : Task getting events by id
 */

package com.example.myschoolreminder.DatabaseUtils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import com.example.myschoolreminder.Objects.Event;
import com.example.myschoolreminder.ObjectsAsyncReturnInterfaces.GetEventsByIdsAsyncReturn;

import java.util.List;

/**
 * Task getting events
 */
public class TaskGetEventsByIds extends AsyncTask<Pair<Context, List<Integer>>, Void, List<Event>> {

    public GetEventsByIdsAsyncReturn delegate;

    /**
     * Gets the events asynchronously
     * @param pairs Pair with context and the list of ids
     * @return
     */
    @Override
    protected List<Event> doInBackground(Pair<Context, List<Integer>> ...pairs) {
        CalendarDatabase database = CalendarDatabase.getInstance(pairs[0].first);

        return database.eventDAO().getEventsByIds(pairs[0].second);
    }

    /**
     * Launches the delegate method that treats the data
     * @param output
     */
    @Override
    protected void onPostExecute(List<Event> output) {
        delegate.returnEventsByIds(output);
    }
}
