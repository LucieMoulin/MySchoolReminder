/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 30.10.2019
 * Description : Calendar Activity
 */

package com.example.myschoolreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * Main Activity
 */
public class CalendarActivity extends AppCompatActivity {

    /**
     * Creation event listener
     * @param savedInstanceState Saved instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
    }
}
