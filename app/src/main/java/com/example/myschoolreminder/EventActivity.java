/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 27.11.2019
 * Description : Event activity
 */

package com.example.myschoolreminder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.myschoolreminder.Objects.Schedule;

import java.util.ArrayList;

/**
 * Event activity
 */
public class EventActivity extends AppCompatActivity implements ScheduleFragment.OnFragmentInteractionListener {

    private ArrayList<ScheduleFragment> fragments;


    /**
     * On activity creation
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        fragments = new ArrayList<ScheduleFragment>();

        AddScheduleFragment();

        //Gets the button that adds fragments
        Button addButton = findViewById(R.id.buttonAddSchedule);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddScheduleFragment();
            }
        });
    }

    @Override
    public void onFragmentInteraction() {

    }

    /**
     * Adds a schedule fragment
     */
    private void AddScheduleFragment(){
        //Begin the transaction
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        //Adds the new fragment to the container
        ScheduleFragment fragment = new ScheduleFragment();
        fragments.add(fragment);
        fragmentTransaction.add(R.id.fragmentFrame, fragment);

        //Complete the changes added above
        fragmentTransaction.commit();
    }

    /**
     * Removes a schedule fragment
     * @param fragment
     */
    public void RemoveScheduleFragment(ScheduleFragment fragment){
        //Begin the transaction
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        //Removes the fragment from the container
        fragmentTransaction.remove(fragment);

        //Complete the changes added above
        fragmentTransaction.commit();
    }
}
