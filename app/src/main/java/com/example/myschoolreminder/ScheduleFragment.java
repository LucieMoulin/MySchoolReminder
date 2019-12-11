/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 27.11.2019
 * Description : Schedule Fragment
 */

package com.example.myschoolreminder;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.myschoolreminder.Objects.Repetition;
import com.example.myschoolreminder.Objects.RepetitionType;
import com.example.myschoolreminder.Objects.Schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Schedule fragment
 */
public class ScheduleFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    /**
     * Required empty public constructor
     */
    public ScheduleFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EventScheduleFragment.
     */
    public static ScheduleFragment newInstance() {
        return new ScheduleFragment();
    }

    /**
     * On the creation of the fragment
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        //Gets the repetition types
        RepetitionType[] rawTypes = RepetitionType.values();
        String[] types = new String[RepetitionType.values().length];

        for(int i = 0; i < RepetitionType.values().length; i++){
            types[i] = rawTypes[i].getName();
        }

        //Gets the spinner
        Spinner spinner = view.findViewById(R.id.spinnerRepetitionType);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, types);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //Gets the remove button
        Button removeButton = view.findViewById(R.id.removeButton);
        final ScheduleFragment fragment = this;

        //Sets the onClickListener
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((EventActivity) getActivity()).RemoveScheduleFragment(fragment);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_schedule, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }

    /**
     * Builds a schedule object with the data entered
     * @return
     */
    public Schedule getSchedule(int eventID){
        //Gets the values entered
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        //Gets the dates strings and tries to convert it
        String startDateString = ((EditText)this.getView().findViewById(R.id.editTextStartDate)).getText().toString();
        String startTimeString = ((EditText)this.getView().findViewById(R.id.editTextStartTime)).getText().toString();
        String endDateString = ((EditText)this.getView().findViewById(R.id.editTextEndDate)).getText().toString();
        String endTimeString = ((EditText)this.getView().findViewById(R.id.editTextEndTime)).getText().toString();
        Date startDate = new Date();
        Date endDate = new Date();;
        try {
            startDate = df.parse(startDateString + " " + startTimeString);
            endDate = df.parse(endDateString + " " + endTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Builds the event
        Schedule schedule = new Schedule(startDate, endDate, eventID);

        return schedule;
    }

    /**
     * Builds a repetition object with the data inserted
     * @param scheduleId
     * @return
     */
    public Repetition getRepetition(int scheduleId){
        //Gets the repetition type, frequency, and activity during holidays
        RepetitionType type = RepetitionType.values()[((Spinner)getView().findViewById(R.id.spinnerRepetitionType)).getSelectedItemPosition()];
        int amount = Integer.parseInt(((EditText)getView().findViewById(R.id.editTextFrequency)).getText().toString());
        Boolean isActiveDuringHolidays = ((CheckBox)getView().findViewById(R.id.checkBoxDuringHolidays)).isChecked();

        //Depending of the type of limit selected, create the Repetition
        RadioGroup group = getView().findViewById(R.id.radioGroupLimitType);
        switch (group.getCheckedRadioButtonId()){
            default:
            case R.id.radioButtonNone:
                return new Repetition(scheduleId, type, amount, isActiveDuringHolidays);
            case R.id.radioButtonLimitDate:
                Date until = new Date();//Todo récupérer la date entrée
                return new Repetition(scheduleId, type, amount, until, isActiveDuringHolidays);
            case R.id.radioButtonLimitAmount:
                int maximumOfRepetitions = 0;//Todo récupérer le maimum entré
                return new Repetition(scheduleId, type, amount, maximumOfRepetitions, isActiveDuringHolidays);
        }
    }
}
