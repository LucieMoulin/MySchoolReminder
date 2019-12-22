/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 11.12.2019
 * Description : Task to delete events
 */

package com.example.myschoolreminder.DatabaseUtils;

import android.content.Context;
import android.os.AsyncTask;

import com.example.myschoolreminder.Objects.Teacher;
import com.example.myschoolreminder.ObjectsAsyncReturnInterfaces.GetTeachersAsyncReturn;

import java.util.List;

/**
 * Task getting teachers
 */
public class TaskDeleteEvents extends AsyncTask<Context, Void, Integer> {

    public GetTeachersAsyncReturn delegate;

    /**
     * Gets the events asynchronously
     * @param contexts
     * @return
     */
    @Override
    protected Integer doInBackground(Context... contexts) {
        CalendarDatabase database = CalendarDatabase.getInstance(contexts[0]);

        return database.eventDAO().deleteAllEvent();
    }

}
