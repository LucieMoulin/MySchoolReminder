package com.example.myschoolreminder.ui.countdown;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myschoolreminder.DatabaseUtils.TaskGetHolidays;
import com.example.myschoolreminder.Objects.Holiday;
import com.example.myschoolreminder.ObjectsAsyncReturnInterfaces.GetStartDateOfNextHolidaysAsyncReturn;
import com.example.myschoolreminder.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class CountdownFragment extends Fragment implements GetStartDateOfNextHolidaysAsyncReturn {

    private CountdownViewModel countdownViewModel;

    Timer timer;

    List<Holiday> holidays;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        countdownViewModel =
                ViewModelProviders.of(this).get(CountdownViewModel.class);
        View root = inflater.inflate(R.layout.fragment_countdown, container, false);
        final TextView textView = root.findViewById(R.id.text_countdown);
        countdownViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){

        TaskGetHolidays taskGetHolidays = new TaskGetHolidays();
        taskGetHolidays.delegate = this;
        taskGetHolidays.execute(getActivity().getApplicationContext());
    }

    /**
     * Get the holidays
     * @param output The holidays
     */
    @Override
    public void returnHolidays(List<Holiday> output) {

        //Set the holidays
        holidays = output;

        timer = new Timer();
        timer.schedule(new CountDownTask(), 1000);
    }

    /**
     * Display the countdown
     */
    private void displayCountDown()
    {
        TextView txtView = getView().findViewById(R.id.txtCountdown);

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
