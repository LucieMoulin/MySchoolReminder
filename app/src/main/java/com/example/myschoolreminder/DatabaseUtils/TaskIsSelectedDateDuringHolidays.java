/*
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 15.12.2019
 * Description : Task getting if a date is in holidays
 */

package com.example.myschoolreminder.DatabaseUtils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import java.util.Date;

public class TaskIsSelectedDateDuringHolidays extends AsyncTask<Pair<Context, Date>, Void, Integer> {

    /**
     * Get the holidays asynchronously
     * @param pairs The context
     * @return The holidays
     */
    @Override
    protected Integer doInBackground(Pair<Context, Date>... pairs) {
        CalendarDatabase database = CalendarDatabase.getInstance(pairs[0].first);
        return database.holidayDAO().isSelectedDateDuringHolidays(pairs[0].second);
    }

}
