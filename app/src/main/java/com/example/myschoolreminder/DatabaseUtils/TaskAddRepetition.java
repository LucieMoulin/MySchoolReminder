/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 11.12.2019
 * Description : Task adding repetitions
 */

package com.example.myschoolreminder.DatabaseUtils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import com.example.myschoolreminder.Objects.Event;
import com.example.myschoolreminder.Objects.Repetition;

/**
 * Task adding repetitions
 */
public class TaskAddRepetition extends AsyncTask<Pair<Context, Repetition>, Void, Long> {
    /**
     * Adds a repetition asynchronously
     * @param pairs
     * @return
     */
    @Override
    protected Long doInBackground(Pair<Context, Repetition>... pairs) {
        CalendarDatabase database = CalendarDatabase.getInstance(pairs[0].first);


        Repetition repetition;
        //Rebuild Repetition without limit
        if(pairs[0].second.getUntil() == null && pairs[0].second.getMaximum() == -1){
            repetition = new Repetition(pairs[0].second.getScheduleId(), pairs[0].second.getType(), pairs[0].second.getAmount(), pairs[0].second.getActiveDuringHolidays());
        }else if (pairs[0].second.getMaximum() == -1){
            //Rebuild repetition with date limit
            repetition = new Repetition(pairs[0].second.getScheduleId(), pairs[0].second.getType(), pairs[0].second.getAmount(), pairs[0].second.getUntil(), pairs[0].second.getActiveDuringHolidays());
        }else{
            //Rebuild repetition with amount limit
            repetition = new Repetition(pairs[0].second.getScheduleId(), pairs[0].second.getType(), pairs[0].second.getAmount(), pairs[0].second.getMaximum(), pairs[0].second.getActiveDuringHolidays());
        }

        Long id = database.repetitionDAO().insertRepetition(repetition);

        return id;
    }
}
