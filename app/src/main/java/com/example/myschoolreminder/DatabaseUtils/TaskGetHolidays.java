/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 15.12.2019
 * Description : Task getting holidays
 */

package com.example.myschoolreminder.DatabaseUtils;

import android.content.Context;
import android.os.AsyncTask;

import com.example.myschoolreminder.Objects.Holiday;

import java.util.List;

public class TaskGetHolidays extends AsyncTask<Context, Void, List<Holiday>> {

    /**
     * Get the holidays asynchronously
     * @param contexts The context
     * @return
     */
    @Override
    protected List<Holiday> doInBackground(Context... contexts) {
        CalendarDatabase database = CalendarDatabase.getInstance(contexts[0]);
        return database.holidayDAO().getHolidays();
    }

}
