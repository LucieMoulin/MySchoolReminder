/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 06.11.2019
 * Description : The Countdown fragment
 */
package com.example.myschoolreminder.ui.countdown;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.myschoolreminder.DatabaseUtils.TaskGetStartDateOfNextHolidays;
import com.example.myschoolreminder.ObjectsAsyncReturnInterfaces.GetStartDateOfNextHolidaysAsyncReturn;
import com.example.myschoolreminder.R;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Seconds;
import org.joda.time.Years;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class CountdownFragment extends Fragment implements GetStartDateOfNextHolidaysAsyncReturn {

    private CountdownViewModel countdownViewModel;

    Timer timer;

    Date startDateOfNextHolidays;

    /**
     * When the view is being created
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        countdownViewModel =
                ViewModelProviders.of(this).get(CountdownViewModel.class);
        View root = inflater.inflate(R.layout.fragment_countdown, container, false);

        return root;
    }

    /**
     * When the view is created
     * @param view The view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){

        //The task to get the next holidays date
        TaskGetStartDateOfNextHolidays taskGetStartDateOfNextHolidays = new TaskGetStartDateOfNextHolidays();
        taskGetStartDateOfNextHolidays.delegate = this;
        taskGetStartDateOfNextHolidays.execute(getActivity().getApplicationContext());
    }

    /**
     * Get the holidays
     * @param output The holidays
     */
    @Override
    public void returnStartDateOfNextHolidays(Date output) {

        //Set the holidays
        startDateOfNextHolidays = output;

        timer = new Timer();
        timer.schedule(new CountDownTask(), 1000);
    }

    /**
     * Display the countdown
     */
    private void displayCountDown()
    {
        final TextView txtView;

        try{
            //Find the textview
            txtView = getActivity().findViewById(R.id.txtCountdown);
        }catch (NullPointerException e){
            e.printStackTrace();
            return;
        }


        //If no holiday
        if(startDateOfNextHolidays == null){

            //Change the text in the main thread
            txtView.post(new Runnable() {
                public void run() {
                    txtView.setText(R.string.countdown_no_holidays);
                }
            });
        }

        else{
            //TODO: Display prettily the countdown
            //Cast the start date as joda time datetime
            DateTime sDate = new DateTime(startDateOfNextHolidays);

            final StringBuilder sb = new StringBuilder();

            //Year check
            int time = Years.yearsBetween(DateTime.now(), sDate).getYears();

            if(time > 0){
                sb.append(" " + time + " année");
                if(time > 1){
                    sb.append("s");
                }

            }

            //Month check
            time = Months.monthsBetween(DateTime.now(), sDate).getMonths();

            if(time > 0){
                sb.append(" " + time + " mois");

            }

            //Day check
            time = Days.daysBetween(DateTime.now(), sDate).getDays();

            if(time > 0){
                sb.append(" " + time + " jour");
                if(time > 1){
                    sb.append("s");
                }

            }

            //Hour check
            time = Hours.hoursBetween(DateTime.now(), sDate).getHours();

            if(time > 0){
                sb.append(" " + time + " heure");
                if(time > 1){
                    sb.append("s");
                }

            }

            //Minute check
            time = Minutes.minutesBetween(DateTime.now(), sDate).getMinutes();

            if(time > 0){
                sb.append(" " + time + " minute");
                if(time > 1){
                    sb.append("s");
                }

            }

            //Second check
            time = Seconds.secondsBetween(DateTime.now(), sDate).getSeconds();

            if(time > 0){
                sb.append(" " + time + " seconde");
                if(time > 1){
                    sb.append("s");
                }

            }

            //Change the text in the main thread
            txtView.post(new Runnable() {
                public void run() {
                    txtView.setText(sb.toString());
                }
            });
        }

    }

    /**
     * Class to implement the countdown for the timer
     */
    class CountDownTask extends TimerTask{

        @Override
        public void run() {
            displayCountDown();
        }
    }
}
