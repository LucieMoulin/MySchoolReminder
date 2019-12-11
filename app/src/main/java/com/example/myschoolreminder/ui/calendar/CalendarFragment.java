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

import com.example.myschoolreminder.EventTypeMenuActivity;
import com.example.myschoolreminder.R;

import java.util.Locale;

public class CalendarFragment extends Fragment {

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

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState){

        Button btnAddElement = view.findViewById(R.id.btnAddElement);

        btnAddElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), EventTypeMenuActivity.class));
            }
        });

        CalendarView calendar = view.findViewById(R.id.calendarView);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                TextView txtview = view.findViewById(R.id.txtcal);

                txtview.setText(dayOfMonth + "." + month + "." + year);
            }
        });
    }
}