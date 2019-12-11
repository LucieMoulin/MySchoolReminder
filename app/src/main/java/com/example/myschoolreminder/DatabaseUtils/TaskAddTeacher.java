/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 11.12.2019
 * Description : Task adding teachers
 */

package com.example.myschoolreminder.DatabaseUtils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import com.example.myschoolreminder.Objects.Teacher;

/**
 * Task adding teachers
 */
public class TaskAddTeacher extends AsyncTask<Pair<Context, Teacher>, Void, Void> {
    /**
     * Adds a teacher asynchronously
     * @param pairs
     * @return
     */
    @Override
    protected Void doInBackground(Pair<Context, Teacher>... pairs) {
        CalendarDatabase database = CalendarDatabase.getInstance(pairs[0].first);

        Teacher teacher = new Teacher(pairs[0].second.getLastName(), pairs[0].second.getFirstName());

        database.teacherDAO().insertTeacher(teacher);

        return null;
    }
}
