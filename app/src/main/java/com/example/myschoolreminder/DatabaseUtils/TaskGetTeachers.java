/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 11.12.2019
 * Description : Task getting teachers
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
public class TaskGetTeachers extends AsyncTask<Context, Void, List<Teacher>> {

    public GetTeachersAsyncReturn delegate;

    /**
     * Gets the events asynchronously
     * @param contexts
     * @return
     */
    @Override
    protected List<Teacher> doInBackground(Context... contexts) {
        CalendarDatabase database = CalendarDatabase.getInstance(contexts[0]);

        return database.teacherDAO().getTeachers();
    }

    /**
     * Launches the delegate method that treats the data
     * @param output
     */
    @Override
    protected void onPostExecute(List<Teacher> output) {
        delegate.returnTeachers(output);
    }
}
