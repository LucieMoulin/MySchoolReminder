/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 27.11.2019
 * Description : Event activity
 */
package com.example.myschoolreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EventActivity extends AppCompatActivity implements EventScheduleFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
    }

    @Override
    public void onFragmentInteraction() {

    }
}
