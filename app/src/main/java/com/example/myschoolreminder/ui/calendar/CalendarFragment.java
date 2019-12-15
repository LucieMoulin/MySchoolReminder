package com.example.myschoolreminder.ui.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myschoolreminder.DatabaseUtils.TaskGetEvents;
import com.example.myschoolreminder.EventTypeMenuActivity;
import com.example.myschoolreminder.Objects.Event;
import com.example.myschoolreminder.Objects.Holiday;
import com.example.myschoolreminder.Objects.Repetition;
import com.example.myschoolreminder.Objects.Schedule;
import com.example.myschoolreminder.ObjectsAsyncReturnInterfaces.GetEventsAsyncReturn;
import com.example.myschoolreminder.ObjectsAsyncReturnInterfaces.GetHolidayAsyncReturn;
import com.example.myschoolreminder.ObjectsAsyncReturnInterfaces.GetRepetitionsAsyncReturn;
import com.example.myschoolreminder.ObjectsAsyncReturnInterfaces.GetSchedulesAsyncReturn;
import com.example.myschoolreminder.R;

import java.util.List;
import java.util.Locale;

public class CalendarFragment extends Fragment implements GetEventsAsyncReturn, GetSchedulesAsyncReturn, GetRepetitionsAsyncReturn, GetHolidayAsyncReturn {

    private CalendarViewModel calendarViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendarViewModel =
                ViewModelProviders.of(this).get(CalendarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);
        final TextView textView = root.findViewById(R.id.text_calendar);
        calendarViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

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

        //Get the calendar widget
        CalendarView calendar = view.findViewById(R.id.calendarView);

        //Set a listener on the selected date change
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            /**
             * When the lected day change
             * @param calendarView The calendar view
             * @param year  The year of the day selected
             * @param month The month of the day selected
             * @param dayOfMonth The day of the month of the day selected
             */
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                TextView txtview = view.findViewById(R.id.txtcal);

                txtview.setText(dayOfMonth + "." + month + "." + year);
            }
        });
    }

    /**
     * Return the events
     * @param output
     */
    @Override
    public void returnEvents(List<Event> output) {

    }

    /**
     * Return the schedules
     * @param output
     */
    @Override
    public void returnSchedules(List<Schedule> output) {

    }

    /**
     * Return repetitions
     * @param output
     */
    @Override
    public void returnRepetitions(List<Repetition> output) {

    }

    /**
     * Return holidays
     * @param output
     */
    @Override
    public void returnHoliday(List<Holiday> output) {

    }
}