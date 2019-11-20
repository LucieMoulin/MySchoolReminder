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

import com.example.myschoolreminder.R;

public class CountdownFragment extends Fragment {

    private CountdownViewModel countdownViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        countdownViewModel =
                ViewModelProviders.of(this).get(CountdownViewModel.class);
        View root = inflater.inflate(R.layout.fragment_countdown, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        countdownViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}