/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 06.11.2019
 * Description : The Calendar fragment
 */
package com.example.myschoolreminder.ui.calendar;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.myschoolreminder.DatabaseUtils.TaskGetEventsByIds;
import com.example.myschoolreminder.DatabaseUtils.TaskIsSelectedDateDuringHolidays;
import com.example.myschoolreminder.DatabaseUtils.TaskGetRepetitionsByScheduleIds;
import com.example.myschoolreminder.DatabaseUtils.TaskGetSchedulesBeforeDate;
import com.example.myschoolreminder.EventTypeMenuActivity;
import com.example.myschoolreminder.MainActivity;
import com.example.myschoolreminder.Objects.Event;
import com.example.myschoolreminder.Objects.Repetition;
import com.example.myschoolreminder.Objects.Schedule;
import com.example.myschoolreminder.ObjectsAsyncReturnInterfaces.GetEventsByIdsAsyncReturn;
import com.example.myschoolreminder.R;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CalendarFragment extends Fragment implements GetEventsByIdsAsyncReturn, SensorEventListener {

    private CalendarViewModel calendarViewModel;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;


    /**
     * When the view is being created
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        calendarViewModel = ViewModelProviders.of(this).get(CalendarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);
        return root;
    }

    /**
     * When the view is created
     * @param view  The view the fragment is on
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState){

        //Get the button to add en element
        Button btnAddElement = view.findViewById(R.id.btnAddElement);

        //Add a listener on the event click on the button
        btnAddElement.setOnClickListener(new View.OnClickListener() {
            /**
             * On click on the button
             * @param view
             */
            @Override
            public void onClick(View view) {
                //Start the view with the menu to add an element
                startActivity(new Intent(getContext(), EventTypeMenuActivity.class));
            }
        });

        //Deletes all events (Debug)
        //TaskDeleteEvents taskDeleteEvents = new TaskDeleteEvents();
        //taskDeleteEvents.execute(getActivity().getApplicationContext());

        //Get the calendar widget
        CalendarView calendar = view.findViewById(R.id.calendarView);

        //Set a listener on the selected date change
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            /**
             * When the selected day change
             * @param calendarView The calendar view
             * @param year  The year of the day selected
             * @param month The month of the day selected
             * @param dayOfMonth The day of the month of the day selected
             */
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                showEvents(year, month, dayOfMonth);
            }
        });


        //Initialize the sensor manager
        sensorManager = (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);

        //Set the sensor as an accelerometer
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //Register the sensor to the sensor manager
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }


    /**
     * Required for the async return in the fragment
     * @return the instance of the interface
     */
    private GetEventsByIdsAsyncReturn getEventsByIdsAsyncReturn(){return this;}

    /**
     * Return the events by ids, and shows them
     * @param output The list of events
     */
    @Override
    public void returnEventsByIds(List<Event> output) {
        if(output.size() > 0){
            //Gets the layout
            LinearLayout layout = getView().findViewById(R.id.layoutEvents);

            //For each event returned
            for(Event event : output){
                TextView eventView = new TextView(getContext());

                //TODO make it so that it's not ugly

                eventView.setText(event.getName());

                layout.addView(eventView);

                //TODO Open details when the textview is clicked
            }
        }
    }

    /**
     * Shows the event for the date specified
     * @param year The selected date's year
     * @param month The selected date's month
     * @param dayOfMonth The selected date's day
     */
    private void showEvents(int year, int month, int dayOfMonth){
        //Resets the events layout
        LinearLayout layout = getView().findViewById(R.id.layoutEvents);
        layout.removeAllViews();

        //Initializes the selected date
        Date selectedDate = new Date(year - 1900, month, dayOfMonth, 23,59,59);

        //If the date is in holidays
        boolean selectedDateDuringHolidays = false;

        //Gets the schedules before the selected date
        List<Schedule> schedulesBeforeDate = getSchedulesBeforeDate(selectedDate);

        //Gets the schedules ids
        int[] allSchedulesIds = getSchedulesId(schedulesBeforeDate);

        //Gets the holidays
        selectedDateDuringHolidays = isDuringHolidays(selectedDate);


        //Get the repetitions linked to the schedules
        List<Repetition> allRepetitions = getRepetitions(allSchedulesIds);

        //List to stock valid repetitions
        List<Integer> validEventsIds = new ArrayList<>();

        //Converts the selected date to a Joda DateTime (easier to work with)
        DateTime jodaSelectedDate = new DateTime(selectedDate);

        //Checks each repetition, and adds it to the valid repetitions if it corresponds to the selected date
        for (Repetition repetition: allRepetitions){

            //If the date is during holidays, check that the repetition can be done during holiday
            if(selectedDateDuringHolidays && !repetition.getActiveDuringHolidays()){
                //Goes to the next repetition
                continue;
            }

            //Find the schedule linked to the repetition
            for (Schedule schedule: schedulesBeforeDate) {
                //Check if the schedule matches
                if (schedule.getIdSchedule() == repetition.getScheduleId()) {
                    //Converts the dates in joda time
                    DateTime jodaStartDate = new DateTime(schedule.getStartDate());
                    DateTime jodaEndDate = new DateTime(schedule.getEndDate());

                    //Declare the datetimes used for comparison
                    DateTime start = new DateTime(jodaStartDate.getYear(), jodaStartDate.getMonthOfYear(), jodaStartDate.getDayOfMonth(), 0, 0);
                    DateTime end = new DateTime(jodaEndDate.getYear(), jodaEndDate.getMonthOfYear(), jodaEndDate.getDayOfMonth(), 0, 0);
                    DateTime selected = new DateTime(jodaSelectedDate.getYear(), jodaSelectedDate.getMonthOfYear(), jodaSelectedDate.getDayOfMonth(), 0, 0);

                    //Checks if the date matches the schedule
                    if(checkRepetition(repetition, start, end, selected)){
                        validEventsIds.add(schedule.getEventId());
                    }
                }
            }
        }

        //Gets the events to show
        if(validEventsIds.size() != 0){
            TaskGetEventsByIds taskGetEventsByIds = new TaskGetEventsByIds();
            taskGetEventsByIds.delegate = getEventsByIdsAsyncReturn();
            taskGetEventsByIds.execute(new Pair(getActivity().getApplicationContext(), validEventsIds));
        }
    }

    /**
     * Gets all schedules before a date
     * @param date The date before which the event will be looked for
     * @return The list of schedules
     */
    private List<Schedule> getSchedulesBeforeDate(Date date){
        //Instance a getSchedulesBeforeDate task
        TaskGetSchedulesBeforeDate taskGetSchedulesBeforeDate = new TaskGetSchedulesBeforeDate();
        taskGetSchedulesBeforeDate.execute(new Pair<>(getActivity().getApplicationContext(), date));

        List<Schedule> schedulesBeforeDate = new ArrayList<>();

        //Get the schedules
        try {
            schedulesBeforeDate = taskGetSchedulesBeforeDate.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return schedulesBeforeDate;
    }

    /**
     * Gets the ids of the schedules in a list
     * @param schedules
     * @return Array of the schedules id's
     */
    private int[] getSchedulesId(List<Schedule> schedules){
        int[] allSchedulesIds = new int[schedules.size()];

        //Get the id of all schedules
        for (int i = 0; i < schedules.size(); i++){
            allSchedulesIds[i] = schedules.get(i).getIdSchedule();
        }

        return allSchedulesIds;
    }

    /**
     * Check if the selected date is during holidays
     * @return if the date is during holidays
     */
    private Boolean isDuringHolidays(Date selectedDate){
        TaskIsSelectedDateDuringHolidays taskIsSelectedDateDuringHolidays = new TaskIsSelectedDateDuringHolidays();
        taskIsSelectedDateDuringHolidays.execute(new Pair<>(getActivity().getApplicationContext(),selectedDate));

        int isDuringHolidays = 0;

        try {
            isDuringHolidays = taskIsSelectedDateDuringHolidays.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return  isDuringHolidays > 0;
    }

    /**
     * Gets the repetitions linked to the schedules
     * @param allSchedulesIds The array with all the schedules ids
     * @return The list of repetitions matching the event ids
     */
    private List<Repetition> getRepetitions(int[] allSchedulesIds){
        TaskGetRepetitionsByScheduleIds taskGetRepetitionsByScheduleIds = new TaskGetRepetitionsByScheduleIds();
        taskGetRepetitionsByScheduleIds.execute(new Pair<>(getActivity().getApplicationContext(), allSchedulesIds));

        List<Repetition> allRepetitions = new ArrayList<>();

        try {
            allRepetitions = taskGetRepetitionsByScheduleIds.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return allRepetitions;
    }

    /**
     * Checks if a selected date matches with a schedule with a limit date
     * @param repetition The repetition to check
     * @param start The start date of the event
     * @param end The end date of the event
     * @param selected The selected date
     * @return
     */
    private boolean checkWithLimitDate(Repetition repetition, DateTime start, DateTime end, DateTime selected){

        //Checks if the until date is smaller than the selected date
        if (repetition.getUntil().before(selected.toDate())) {
            //The selected date doesn't match
            return false;
        }

        //Converts the limit date to Joda DateTime
        DateTime until = new DateTime(repetition.getUntil());


        //While the end date is bigger than the limit date or is the limit date
        while (until.isAfter(end.toInstant()) || until.isEqual(end.toInstant())) {

            //If the selected date is before the test date, break because no need to check any further
            if (selected.isBefore(start)) {
                break;
            }

            //If the date matches
            if (start.equals(selected) || end.equals(selected) || (selected.isAfter(start) && selected.isBefore(end))) {
                return true;
            }

            //Increments the dates
            switch (repetition.getType()){
                case DAILY:
                    start = start.plusDays(repetition.getAmount());
                    end = end.plusDays(repetition.getAmount());
                    break;

                case WEEKLY:
                    start = start.plusWeeks(repetition.getAmount());
                    end = end.plusWeeks(repetition.getAmount());
                    break;

                case MONTHLY:
                    start = start.plusMonths(repetition.getAmount());
                    end = end.plusMonths(repetition.getAmount());
                    break;

                case YEARLY:
                    start = start.plusYears(repetition.getAmount());
                    end = end.plusYears(repetition.getAmount());
                    break;
            }
        }

        return false;
    }

    /**
     * Checks if a selected date matches with a schedule with a limit amount of repetitions
     * @param repetition The repetition to check
     * @param start The start date of the event
     * @param end The end date of the event
     * @param selected The selected date
     * @return
     */
    private boolean checkWithLimitAmount(Repetition repetition, DateTime start, DateTime end, DateTime selected){

        //While the repetitions counter is smaller than the max
        for (int i = 0; i < repetition.getMaximum(); i++) {

            //If the selected date is before the test date, break because no need to check any further
            if (selected.isBefore(start)) {
                break;
            }

            //If the date matches
            if (start.equals(selected) || end.equals(selected) || (selected.isAfter(start) && selected.isBefore(end))) {
                return true;
            }

            //Increments the dates
            switch (repetition.getType()){
                case DAILY:
                    start = start.plusDays(repetition.getAmount());
                    end = end.plusDays(repetition.getAmount());
                    break;

                case WEEKLY:
                    start = start.plusWeeks(repetition.getAmount());
                    end = end.plusWeeks(repetition.getAmount());
                    break;

                case MONTHLY:
                    start = start.plusMonths(repetition.getAmount());
                    end = end.plusMonths(repetition.getAmount());
                    break;

                case YEARLY:
                    start = start.plusYears(repetition.getAmount());
                    end = end.plusYears(repetition.getAmount());
                    break;
            }
        }

        return false;
    }

    /**
     * Checks if a selected date matches with a schedule with no limit
     * @param repetition The repetition to check
     * @param start The start date of the event
     * @param end The end date of the event
     * @param selected The selected date
     * @return
     */
    private boolean checkWithNoLimit(Repetition repetition, DateTime start, DateTime end, DateTime selected){

        //While the selected date is after the end date
        while (selected.isAfter(end) || selected.equals(end)) {

            //If the date matches
            if (start.equals(selected) || end.equals(selected) || (selected.isAfter(start) && selected.isBefore(end))) {
                return true;
            }

            //Increments the dates
            switch (repetition.getType()){
                case DAILY:
                    start = start.plusDays(repetition.getAmount());
                    end = end.plusDays(repetition.getAmount());
                    break;

                case WEEKLY:
                    start = start.plusWeeks(repetition.getAmount());
                    end = end.plusWeeks(repetition.getAmount());
                    break;

                case MONTHLY:
                    start = start.plusMonths(repetition.getAmount());
                    end = end.plusMonths(repetition.getAmount());
                    break;

                case YEARLY:
                    start = start.plusYears(repetition.getAmount());
                    end = end.plusYears(repetition.getAmount());
                    break;
            }
        }

        return false;
    }

    /**
     * Checks if a selected date matches with a schedule
     * @param repetition The repetition to check
     * @param start The start date of the event
     * @param end The end date of the event
     * @param selected The selected date
     * @return
     */
    private boolean checkRepetition(Repetition repetition, DateTime start, DateTime end, DateTime selected){

        boolean matches = false;

        switch (repetition.getType()){
            case NONE:
                //If the date matches
                return start.equals(selected) || end.equals(selected) || (selected.isAfter(start) && selected.isBefore(end));
            case WEEKLY:
                //Check if the number of the day in the week match
                if (selected.getDayOfWeek() != (start.getDayOfWeek()) || selected.getDayOfWeek() != end.getDayOfWeek()) {
                    return false;
                }
                break;

            case MONTHLY:
                //Check if the number of the day in the month match
                if (selected.dayOfMonth().equals(start.dayOfMonth()) || selected.dayOfMonth().equals(end.dayOfMonth())) {
                    return false;
                }
                break;

            case YEARLY:
                //Check if the number of the day in the year match
                if (selected.dayOfYear().equals(start.dayOfYear()) || selected.dayOfYear().equals(end.dayOfYear())) {
                    return false;
                }
                break;
        }

        //If the limit is a date
        if (repetition.getUntil() != null) {
            matches = matches || checkWithLimitDate(repetition, start, end, selected);
        }

        //If the limit is a maximum of repetitions
        else if (repetition.getMaximum() != -1) {
            matches = matches || checkWithLimitAmount(repetition, start, end, selected);
        }

        //If no limit
        else {
            matches = matches || checkWithNoLimit(repetition, start, end, selected);
        }

        return matches;
    }


    /**
     * When there's a new event on the sensor
     * @param sensorEvent The sensor event
     */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        //Get the sensor that changed
        Sensor mySensor = sensorEvent.sensor;

        //If the sensor is the accelerometer
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            //Save the new x, y, z values
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            //Get current time
            long curTime = System.currentTimeMillis();

            //Get the difference between now and the last update
            long diffTime = (curTime - lastUpdate);

            //Set the new as the last update
            lastUpdate = curTime;

            //Calculate the speed of the movement
            float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

            //If it's bigger than the shake threshold
            if (speed > SHAKE_THRESHOLD) {

                //TODO: Launch
                try{
                    ((MainActivity)getActivity()).onMove();

                }catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

            last_x = x;
            last_y = y;
            last_z = z;
        }
    }

    /**
     * When the accuracy of the sensor changed
     * @param sensor The sensor
     * @param accuracy The current accuracy
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //This function is mandatory in the implementation, but we don't need to do anything with it at this points
        //Just nothing
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
}