/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 27.11.2019
 * Description : Event activity
 */

package com.example.myschoolreminder;
import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.myschoolreminder.DatabaseUtils.TaskAddEvent;
import com.example.myschoolreminder.DatabaseUtils.TaskAddRepetition;
import com.example.myschoolreminder.DatabaseUtils.TaskAddSchedule;
import com.example.myschoolreminder.DatabaseUtils.TaskGetTeachers;
import com.example.myschoolreminder.Objects.Class;
import com.example.myschoolreminder.Objects.Event;
import com.example.myschoolreminder.Objects.EventType;
import com.example.myschoolreminder.Objects.Reminder;
import com.example.myschoolreminder.Objects.Repetition;
import com.example.myschoolreminder.Objects.Schedule;
import com.example.myschoolreminder.Objects.Teacher;
import com.example.myschoolreminder.ObjectsAsyncReturnInterfaces.GetTeachersAsyncReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static android.view.View.GONE;

/**
 * Event activity
 */
public class EventActivity extends AppCompatActivity implements ScheduleFragment.OnFragmentInteractionListener, GetTeachersAsyncReturn {

    private ArrayList<ScheduleFragment> fragments;
    EventType typeOfEvent;

    /**
     * On activity creation
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        fragments = new ArrayList<ScheduleFragment>();

        //Gets the button that adds fragments
        Button addButton = findViewById(R.id.buttonAddSchedule);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddScheduleFragment();
            }
        });

        //Gets the save button
        Button saveButton = findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            /**
             * When the button is clicked, add the event to the database
             * @param view
             */
            @Override
            public void onClick(View view) {
                //Checks if the values entered are valid
                if(isValid()){
                    //Gets the event
                    Event event = getEvent();

                    //Save the event into the database
                    TaskAddEvent taskAddEvent = new TaskAddEvent();
                    taskAddEvent.execute(new Pair<Context, Event>(getApplicationContext(), event));

                    //Tries to get the id of the event
                    long eventId = 0;
                    try {
                        eventId = taskAddEvent.get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //Gets the schedule of each fragment, and saves it
                    for (ScheduleFragment fragment : fragments) {
                        Schedule schedule = fragment.getSchedule(((int) eventId));

                        TaskAddSchedule taskAddSchedule = new TaskAddSchedule();
                        taskAddSchedule.execute(new Pair<Context, Schedule>(getApplicationContext(), schedule));

                        long scheduleID = 0;
                        try {
                            scheduleID = taskAddSchedule.get();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        //Gets the repetition of the schedule
                        Repetition repetition = fragment.getRepetition((int) scheduleID);

                        if(repetition != null){
                            //Saves the repetition
                            TaskAddRepetition taskAddRepetition = new TaskAddRepetition();
                            taskAddRepetition.execute(new Pair<Context, Repetition>(getApplicationContext(), repetition));
                        }
                    }

                    finish();
                }else{
                    //TODO show error message
                }
            }
        });

        //Switches the event type
        Bundle bundle = getIntent().getExtras();
        int value = EventType.EVENT.ordinal();
        typeOfEvent = EventType.EVENT;
        if(bundle != null)
        {
            value = bundle.getInt("eventType");
            typeOfEvent = EventType.values()[value];

            //Gets the title view
            TextView title = findViewById(R.id.textViewTitle);
            if(value != EventType.HOLIDAY.ordinal() && value != EventType.HOMEWORK.ordinal()){
                title.setText("Ajouter un " + EventType.values()[value].getName());
            } else{
                title.setText("Ajouter des " + EventType.values()[value].getName());
            }

            switch (EventType.values()[value]) {
                case EVENT:

                    //Adds a schedule fragment
                    AddScheduleFragment();
                    break;
                case REMINDER:
                    //Gets and hides the Place editText
                    findViewById(R.id.editTextPlace).setVisibility(GONE);

                    //Adds a schedule fragment
                    AddScheduleFragment();
                    break;
                case CLASS:
                    //Gets and changes the text of the Place editText
                    ((EditText)findViewById(R.id.editTextPlace)).setHint("Salle");

                    //Gets and adds the teachers to the spinner
                    TaskGetTeachers taskGetTeachers = new TaskGetTeachers();
                    taskGetTeachers.delegate = this;
                    taskGetTeachers.execute(getApplicationContext());

                    //Adds a schedule fragment
                    AddScheduleFragment();
                    break;
                case HOMEWORK:
                    //Gets and hides the Place editText
                    findViewById(R.id.editTextPlace).setVisibility(GONE);

                    //Hides the add schedule button
                    findViewById(R.id.buttonAddSchedule).setVisibility(GONE);

                    //Gets the spinner
                    Spinner classSpinner = findViewById(R.id.spinner);
                    classSpinner.setVisibility(View.VISIBLE);

                    //Gets the classes
                    Class[] rawClasses = new Class[1];//Todo récupérer toutes les cours
                    String[] classes = new String[rawClasses.length];

                    for(int i = 0; i < rawClasses.length; i++){
                        classes[i] = rawClasses[i].getName();
                    }

                    // Create an ArrayAdapter using the string array and a default spinner layout
                    ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, classes);

                    // Specify the layout to use when the list of choices appears
                    classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // Apply the adapter to the spinner
                    classSpinner.setAdapter(classAdapter);
                    break;
                case HOLIDAY:
                    //Gets and hides the Place editText
                    findViewById(R.id.editTextPlace).setVisibility(GONE);

                    //Hides the add schedule button
                    findViewById(R.id.buttonAddSchedule).setVisibility(GONE);
                    break;
                case TRIP:
                    //Gets and hides the Place editText
                    findViewById(R.id.editTextPlace).setVisibility(GONE);

                    //Hides the add schedule button
                    findViewById(R.id.buttonAddSchedule).setVisibility(GONE);
                    break;
            }
        }
    }

    /**
     * Required function for the fragments.
     */
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

    /**
     * Returns the teachers and adds them to the spinner
     * @param output
     */
    @Override
    public void returnTeachers(List<Teacher> output) {
        //Gets the spinner
        Spinner teachersSpinner = findViewById(R.id.spinner);
        teachersSpinner.setVisibility(View.VISIBLE);

        //Gets the teachers
        Teacher[] rawTeachers = output.toArray(new Teacher[0]);
        String[] teachers = new String[rawTeachers.length];

        for(int i = 0; i < rawTeachers.length; i++){
            teachers[i] = rawTeachers[i].getLastName() + " " + rawTeachers[i].getFirstName();
        }

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, teachers);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        teachersSpinner.setAdapter(adapter);
    }

    /**
     * Builds an event object with the data entered
     * @return
     */
    private Event getEvent(){
        Event event;
        switch (typeOfEvent){
            default:
            case EVENT:
                //Gets the values entered
                String title = ((EditText)findViewById(R.id.editTextTitle)).getText().toString();
                String description = ((EditText)findViewById(R.id.editTextDescription)).getText().toString();
                String place = ((EditText)findViewById(R.id.editTextPlace)).getText().toString();

                //Builds the event
                if(place != ""){
                    event = new Event(title, description, place);
                }else{
                    event = new Event(title, description);
                }
                break;
            case REMINDER:
                //Todo créer l'objet reminder
                event = new Event("", "");
                break;
            case CLASS:
                //Todo créer l'objet class
                event = new Event("", "");
                break;
            case HOMEWORK:
                //Todo créer l'objet Homework
                event = new Event("", "");
                break;
            case HOLIDAY:
                //Todo créer l'objet holiday
                event = new Event("", "");
                break;
            case TRIP:
                //Todo créer l'objet trip
                event = new Event("", "");
                break;
        }

        return event;
    }

    /**
     * Checks if the values entered are valid
     * @return
     */
    private Boolean isValid(){
        Boolean valid = true;

        Event event = getEvent();

        //The event is valid if it has a title
        valid = valid && event.getName() != "";

        //The schedule(s) are valid
        for (ScheduleFragment fragment: fragments) {
            valid = valid && fragment.isValid();
        }

        return  valid;
    }
}
