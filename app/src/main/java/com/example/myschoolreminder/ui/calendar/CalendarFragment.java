package com.example.myschoolreminder.ui.calendar;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
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

import com.example.myschoolreminder.DatabaseUtils.TaskDeleteEvents;
import com.example.myschoolreminder.DatabaseUtils.TaskGetEventsByIds;
import com.example.myschoolreminder.DatabaseUtils.TaskGetHolidays;
import com.example.myschoolreminder.DatabaseUtils.TaskGetRepetitionsByScheduleIds;
import com.example.myschoolreminder.DatabaseUtils.TaskGetSchedulesBeforeDate;
import com.example.myschoolreminder.EventTypeMenuActivity;
import com.example.myschoolreminder.Objects.Event;
import com.example.myschoolreminder.Objects.Holiday;
import com.example.myschoolreminder.Objects.Repetition;
import com.example.myschoolreminder.Objects.Schedule;
import com.example.myschoolreminder.ObjectsAsyncReturnInterfaces.GetEventsByIdsAsyncReturn;
import com.example.myschoolreminder.R;

import org.joda.time.DateTime;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CalendarFragment extends Fragment implements GetEventsByIdsAsyncReturn {

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

                //Create a date with info of selected date
                Date selectedDate = new Date(calendarView.getDate());

                //If the date is in holidays
                boolean isDuringHolidays = false;

                //Instance a getSchedulesBeforeDate task
                TaskGetSchedulesBeforeDate taskGetSchedulesBeforeDate = new TaskGetSchedulesBeforeDate();
                taskGetSchedulesBeforeDate.execute(new Pair<>(getActivity().getApplicationContext(), selectedDate));

                List<Schedule> schedulesBeforeDate = new ArrayList<>();

                //Get the schedules
                try {
                    schedulesBeforeDate = taskGetSchedulesBeforeDate.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                int[] allSchedulesIds = new int[schedulesBeforeDate.size()];

                //Get the id of all schedules
                for (int i = 0; i < schedulesBeforeDate.size(); i++){
                    allSchedulesIds[i] = schedulesBeforeDate.get(i).getIdSchedule();
                }

                //Get the holidays
                TaskGetHolidays taskGetHolidays = new TaskGetHolidays();
                taskGetHolidays.execute(getActivity().getApplicationContext());

                List<Holiday> holidays = new ArrayList<>();

                try {
                    holidays = taskGetHolidays.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int[] holidaysIds = new int[holidays.size()];

                //Get the ids of all the holidays
                for(int i =0; i < holidays.size(); i++){
                    holidaysIds[i] = holidays.get(i).getIdEvent();
                }

                //Check if the date is in holidays
                for (Schedule s: schedulesBeforeDate) {
                    //If schedule is for a holiday
                    if(Arrays.asList(holidaysIds).contains(s.getEventId())){
                        //Check if the actual date is in holidays
                        if(selectedDate.equals(s.getStartDate()) || selectedDate.equals(s.getEndDate()) || (selectedDate.after(s.getStartDate()) && selectedDate.before(s.getEndDate()))){
                            isDuringHolidays = true;
                        }
                    }
                }

                //Get the repetitions linked to the schedules
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

                //List to stock valid repetitions
                List<Integer> validEventsIds = new ArrayList<>();

                DateTime jodaSelectedDate = new DateTime(selectedDate);

                for (Repetition r: allRepetitions){

                    //If the date is during holidays, check that the repetition can be done during holiday
                    if(isDuringHolidays || !r.getActiveDuringHolidays()){
                        continue;
                    }

                    //Find the schedule linked to the repetition
                    for (Schedule s: schedulesBeforeDate) {
                        //Check if the schedule matches
                        if (s.getIdSchedule() == r.getScheduleId()) {

                            //The test dates in joda time
                            DateTime jodaTestStartDate = new DateTime(s.getStartDate());
                            DateTime jodaTestEndDate = new DateTime(s.getEndDate());

                            switch (r.getType()) {

                                case NONE:

                                    //If the selected date is the start or the end date or is between start and end

                                    if ((s.getStartDate().equals(selectedDate) || s.getEndDate().equals(selectedDate) || (s.getStartDate().before(selectedDate) && s.getEndDate().after(selectedDate)))) {

                                        //Add the event's id
                                        validEventsIds.add(s.getEventId());
                                        break;
                                    }

                                    break;

                                case DAILY:

                                    //Get the limit
                                    //If the limit is a date
                                    if (r.getUntil() != null) {

                                        //Check if the until date is smaller than the selected date
                                        if (r.getUntil().before(selectedDate)) {
                                            break;
                                        }
                                        DateTime jodaUntilDate = new DateTime(r.getUntil());

                                        //Until the end test date is bigger than the day until when the event should be repeated
                                        while (jodaUntilDate.isAfter(jodaTestEndDate.toInstant())) {
                                            jodaTestStartDate = jodaTestStartDate.plusDays(r.getAmount());
                                            jodaTestEndDate = jodaTestEndDate.plusDays(r.getAmount());

                                            //If the selected date is before the test date, break because no need to check more
                                            if (jodaSelectedDate.isBefore(jodaTestStartDate)) {
                                                break;
                                            }

                                            //If the date matches and the holiday condition is verified
                                            if ((jodaTestStartDate.equals(jodaSelectedDate) || jodaTestEndDate.equals(jodaSelectedDate))) {

                                                //Add the event's id
                                                validEventsIds.add(s.getEventId());

                                                break;
                                            }
                                        }
                                    }
                                    //If the limit is a maximum of repetitions
                                    else if (r.getMaximum() != 0) {

                                        //Until the repetitions amount is bigger than the max
                                        for (int i = 0; i < r.getMaximum(); i++) {
                                            jodaTestStartDate = jodaTestStartDate.plusDays(r.getAmount());
                                            jodaTestEndDate = jodaTestEndDate.plusDays(r.getAmount());

                                            //If the selected date is before the test date, break because no need to check more
                                            if (jodaSelectedDate.isBefore(jodaTestStartDate)) {
                                                break;
                                            }

                                            //If the date matches and the holiday condition is verified
                                            if ((jodaTestStartDate.equals(jodaSelectedDate) || jodaTestEndDate.equals(jodaSelectedDate))) {

                                                //Add the event's id
                                                validEventsIds.add(s.getEventId());

                                                break;
                                            }
                                        }
                                    }

                                    //If no limit
                                    else {

                                        //While the test end date is before the selected date
                                        while (jodaSelectedDate.isAfter(jodaTestEndDate)) {
                                            jodaTestStartDate = jodaTestStartDate.plusDays(r.getAmount());
                                            jodaTestEndDate = jodaTestEndDate.plusDays(r.getAmount());

                                            //If the date matches and the holiday condition is verified
                                            if ((jodaTestStartDate.equals(jodaSelectedDate) || jodaTestEndDate.equals(jodaSelectedDate))) {

                                                //Add the event's id
                                                validEventsIds.add(s.getEventId());

                                                break;
                                            }
                                        }
                                    }

                                    break;

                                case WEEKLY:

                                    //Check if the number of the day in the week match
                                    if (jodaSelectedDate.dayOfWeek().equals(jodaTestStartDate.dayOfWeek()) || jodaSelectedDate.dayOfWeek().equals(jodaTestEndDate.dayOfWeek())) {
                                        break;
                                    }

                                    //Get the limit
                                    //If the limit is a date
                                    if (r.getUntil() != null) {

                                        //Check if the until date is smaller than the selected date
                                        if (r.getUntil().before(selectedDate)) {
                                            break;
                                        }
                                        DateTime jodaUntilDate = new DateTime(r.getUntil());

                                        //Until the end test date is bigger than the day until when the event should be repeated
                                        while (jodaUntilDate.isAfter(jodaTestEndDate.toInstant())) {
                                            jodaTestStartDate = jodaTestStartDate.plusWeeks(r.getAmount());
                                            jodaTestEndDate = jodaTestEndDate.plusWeeks(r.getAmount());

                                            //If the selected date is before the test date, break because no need to check more
                                            if (jodaSelectedDate.isBefore(jodaTestStartDate)) {
                                                break;
                                            }

                                            //If the date matches and the holiday condition is verified
                                            if ((jodaTestStartDate.equals(jodaSelectedDate) || jodaTestEndDate.equals(jodaSelectedDate))) {

                                                //Add the event's id
                                                validEventsIds.add(s.getEventId());

                                                break;
                                            }
                                        }
                                    }
                                    //If the limit is a maximum of repetitions
                                    else if (r.getMaximum() != 0) {

                                        //Until the repetitions amount is bigger than the max
                                        for (int i = 0; i < r.getMaximum(); i++) {
                                            jodaTestStartDate = jodaTestStartDate.plusWeeks(r.getAmount());
                                            jodaTestEndDate = jodaTestEndDate.plusWeeks(r.getAmount());

                                            //If the selected date is before the test date, break because no need to check more
                                            if (jodaSelectedDate.isBefore(jodaTestStartDate)) {
                                                break;
                                            }

                                            //If the date matches and the holiday condition is verified
                                            if ((jodaTestStartDate.equals(jodaSelectedDate) || jodaTestEndDate.equals(jodaSelectedDate))) {

                                                //Add the event's id
                                                validEventsIds.add(s.getEventId());

                                                break;
                                            }
                                        }
                                    }

                                    //If no limit
                                    else {

                                        //While the test end date is before the selected date
                                        while (jodaSelectedDate.isAfter(jodaTestEndDate)) {
                                            jodaTestStartDate = jodaTestStartDate.plusWeeks(r.getAmount());
                                            jodaTestEndDate = jodaTestEndDate.plusWeeks(r.getAmount());

                                            //If the date matches and the holiday condition is verified
                                            if ((jodaTestStartDate.equals(jodaSelectedDate) || jodaTestEndDate.equals(jodaSelectedDate))) {

                                                //Add the event's id
                                                validEventsIds.add(s.getEventId());

                                                break;
                                            }
                                        }
                                    }

                                    break;


                                case MONTHLY:

                                    //Check if the number of the day in the month match
                                    if (jodaSelectedDate.dayOfMonth().equals(jodaTestStartDate.dayOfMonth()) || jodaSelectedDate.dayOfMonth().equals(jodaTestEndDate.dayOfMonth())) {
                                        break;
                                    }

                                    //Get the limit
                                    //If the limit is a date
                                    if (r.getUntil() != null) {

                                        //Check if the until date is smaller than the selected date
                                        if (r.getUntil().before(selectedDate)) {
                                            break;
                                        }
                                        DateTime jodaUntilDate = new DateTime(r.getUntil());

                                        //Until the end test date is bigger than the day until when the event should be repeated
                                        while (jodaUntilDate.isAfter(jodaTestEndDate.toInstant())) {
                                            jodaTestStartDate = jodaTestStartDate.plusMonths(r.getAmount());
                                            jodaTestEndDate = jodaTestEndDate.plusMonths(r.getAmount());

                                            //If the selected date is before the test date, break because no need to check more
                                            if (jodaSelectedDate.isBefore(jodaTestStartDate)) {
                                                break;
                                            }

                                            //If the date matches and the holiday condition is verified
                                            if ((jodaTestStartDate.equals(jodaSelectedDate) || jodaTestEndDate.equals(jodaSelectedDate))) {

                                                //Add the event's id
                                                validEventsIds.add(s.getEventId());

                                                break;
                                            }
                                        }
                                    }
                                    //If the limit is a maximum of repetitions
                                    else if (r.getMaximum() != 0) {

                                        //Until the repetitions amount is bigger than the max
                                        for (int i = 0; i < r.getMaximum(); i++) {
                                            jodaTestStartDate = jodaTestStartDate.plusMonths(r.getAmount());
                                            jodaTestEndDate = jodaTestEndDate.plusMonths(r.getAmount());

                                            //If the selected date is before the test date, break because no need to check more
                                            if (jodaSelectedDate.isBefore(jodaTestStartDate)) {
                                                break;
                                            }

                                            //If the date matches and the holiday condition is verified
                                            if ((jodaTestStartDate.equals(jodaSelectedDate) || jodaTestEndDate.equals(jodaSelectedDate))) {

                                                //Add the event's id
                                                validEventsIds.add(s.getEventId());

                                                break;
                                            }
                                        }
                                    }

                                    //If no limit
                                    else {

                                        //While the test end date is before the selected date
                                        while (jodaSelectedDate.isAfter(jodaTestEndDate)) {
                                            jodaTestStartDate = jodaTestStartDate.plusMonths(r.getAmount());
                                            jodaTestEndDate = jodaTestEndDate.plusMonths(r.getAmount());

                                            //If the date matches and the holiday condition is verified
                                            if ((jodaTestStartDate.equals(jodaSelectedDate) || jodaTestEndDate.equals(jodaSelectedDate))) {

                                                //Add the event's id
                                                validEventsIds.add(s.getEventId());

                                                break;
                                            }
                                        }
                                    }

                                    break;
                                case YEARLY:

                                    //Check if the number of the day in the year match
                                    if (jodaSelectedDate.dayOfYear().equals(jodaTestStartDate.dayOfYear()) || jodaSelectedDate.dayOfYear().equals(jodaTestEndDate.dayOfYear())) {
                                        break;
                                    }

                                    //Get the limit
                                    //If the limit is a date
                                    if (r.getUntil() != null) {

                                        //Check if the until date is smaller than the selected date
                                        if (r.getUntil().before(selectedDate)) {
                                            break;
                                        }
                                        DateTime jodaUntilDate = new DateTime(r.getUntil());

                                        //Until the end test date is bigger than the day until when the event should be repeated
                                        while (jodaUntilDate.isAfter(jodaTestEndDate.toInstant())) {
                                            jodaTestStartDate = jodaTestStartDate.plusYears(r.getAmount());
                                            jodaTestEndDate = jodaTestEndDate.plusYears(r.getAmount());

                                            //If the selected date is before the test date, break because no need to check more
                                            if (jodaSelectedDate.isBefore(jodaTestStartDate)) {
                                                break;
                                            }

                                            //If the date matches and the holiday condition is verified
                                            if ((jodaTestStartDate.equals(jodaSelectedDate) || jodaTestEndDate.equals(jodaSelectedDate))) {

                                                //Add the event's id
                                                validEventsIds.add(s.getEventId());

                                                break;
                                            }
                                        }
                                    }
                                    //If the limit is a maximum of repetitions
                                    else if (r.getMaximum() != 0) {

                                        //Until the repetitions amount is bigger than the max
                                        for (int i = 0; i < r.getMaximum(); i++) {
                                            jodaTestStartDate = jodaTestStartDate.plusYears(r.getAmount());
                                            jodaTestEndDate = jodaTestEndDate.plusYears(r.getAmount());

                                            //If the selected date is before the test date, break because no need to check more
                                            if (jodaSelectedDate.isBefore(jodaTestStartDate)) {
                                                break;
                                            }

                                            //If the date matches and the holiday condition is verified
                                            if ((jodaTestStartDate.equals(jodaSelectedDate) || jodaTestEndDate.equals(jodaSelectedDate))) {

                                                //Add the event's id
                                                validEventsIds.add(s.getEventId());

                                                break;
                                            }
                                        }
                                    }

                                    //If no limit
                                    else {

                                        //While the test end date is before the selected date
                                        while (jodaSelectedDate.isAfter(jodaTestEndDate)) {
                                            jodaTestStartDate = jodaTestStartDate.plusYears(r.getAmount());
                                            jodaTestEndDate = jodaTestEndDate.plusYears(r.getAmount());

                                            //If the date matches and the holiday condition is verified
                                            if ((jodaTestStartDate.equals(jodaSelectedDate) || jodaTestEndDate.equals(jodaSelectedDate))) {

                                                //Add the event's id
                                                validEventsIds.add(s.getEventId());

                                                break;
                                            }
                                        }
                                    }
                                    break;
                            }
                        }
                    }
                }

                //Get the events if not null
6                if(validEventsIds.size() != 0){
                    TaskGetEventsByIds taskGetEventsByIds = new TaskGetEventsByIds();
                    taskGetEventsByIds.delegate = getEventsByIdsAsyncReturn();
                    taskGetEventsByIds.execute(new Pair(getActivity().getApplicationContext(), validEventsIds));
                }
            }
        });
    }

    private GetEventsByIdsAsyncReturn getEventsByIdsAsyncReturn(){return this;}

    /**
     * Return the events by ids
     * @param output
     */
    @Override
    public void returnEventsByIds(List<Event> output) {

    }

}