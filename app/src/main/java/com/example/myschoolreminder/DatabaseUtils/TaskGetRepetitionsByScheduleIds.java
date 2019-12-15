/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 15.12.2019
 * Description : Task getting repetitions by schedules ids
 */
package com.example.myschoolreminder.DatabaseUtils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import com.example.myschoolreminder.Objects.Repetition;

import java.util.List;

public class TaskGetRepetitionsByScheduleIds extends AsyncTask<Pair<Context, int[]>, Void, List<Repetition>> {

    /**
     * Get the repetitions by schedule ids asynchronously
     * @param pairs Pair with context and schedule ids as int array
     * @return The repetitions' list
     */
    @Override
    protected List<Repetition> doInBackground(Pair<Context, int[]>... pairs) {
        CalendarDatabase database = CalendarDatabase.getInstance(pairs[0].first);
        return database.repetitionDAO().getRepetitionsByScheduleIds(pairs[0].second);
    }
}
