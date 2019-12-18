/*
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 18.12.2019
 * Description : Task getting the start date of the next holidays
 */

package com.example.myschoolreminder.DatabaseUtils;

import android.content.Context;
import android.os.AsyncTask;

import com.example.myschoolreminder.ObjectsAsyncReturnInterfaces.GetStartDateOfNextHolidaysAsyncReturn;

import java.util.Date;

public class TaskGetStartDateOfNextHolidays extends AsyncTask<Context, Void, Date> {

    public GetStartDateOfNextHolidaysAsyncReturn delegate;

    /**
     * Get the holidays asynchronously
     * @param contexts The context
     * @return The holidays
     */
    @Override
    protected Date doInBackground(Context... contexts) {
        CalendarDatabase database = CalendarDatabase.getInstance(contexts[0]);
        return database.scheduleDAO().getNextHolidayStartDay(new Date(System.currentTimeMillis()));
    }

    /**
     * Launches the delegate method that treats the data
     * @param start The start date
     */
    @Override
    protected void onPostExecute(Date start) {delegate.returnStartDateOfNextHolidays(start);}
}
