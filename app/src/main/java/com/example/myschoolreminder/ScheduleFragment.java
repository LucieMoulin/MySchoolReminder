/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 27.11.2019
 * Description : Schedule Fragment
 */

package com.example.myschoolreminder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.myschoolreminder.Objects.Repetition;
import com.example.myschoolreminder.Objects.RepetitionType;
import com.example.myschoolreminder.Objects.Schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Schedule fragment
 */
public class ScheduleFragment extends Fragment {
    private OnFragmentInteractionListener mListener;

    /**
     * Calendar
     */
    private Calendar calendar = Calendar.getInstance();

    /**
     * Date edittexts
     */
    private EditText editTextStartDate;
    private EditText editTextEndDate;
    private EditText editTextLimitDate;

    /**
     * Time edittexts
     */
    private EditText editTextStartTime;
    private EditText editTextEndTime;

    /**
     * Date ans time formats
     */
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
    private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy hh:mm");

    /**
     * DatePickerDialog for the start date
     */
    DatePickerDialog.OnDateSetListener onStartDateListener = new DatePickerDialog.OnDateSetListener() {
        /**
         * When the date is set, update the edittext
         * @param view
         * @param year
         * @param monthOfYear
         * @param dayOfMonth
         */
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateStartDate();
        }
    };

    /**
     * DatePickerDialog for the end date
     */
    DatePickerDialog.OnDateSetListener onEndDateListener = new DatePickerDialog.OnDateSetListener() {
        /**
         * When the date is set, update the edittext
         * @param view
         * @param year
         * @param monthOfYear
         * @param dayOfMonth
         */
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateEndDate();
        }
    };

    /**
     * DatePickerDialog for the end date
     */
    DatePickerDialog.OnDateSetListener onLimitDateListener = new DatePickerDialog.OnDateSetListener() {
        /**
         * When the date is set, update the edittext
         * @param view
         * @param year
         * @param monthOfYear
         * @param dayOfMonth
         */
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLimitDate();
        }
    };

    /**
     * DatePickerDialog for the start time
     */
    TimePickerDialog.OnTimeSetListener onStartTimeListener = new TimePickerDialog.OnTimeSetListener(){
        /**
         * When the time is set, update the edittext
         * @param view
         * @param hourOfDay
         * @param minute
         */
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar.set(Calendar.HOUR, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            updateStartTime();
        }
    };

    /**
     * DatePickerDialog for the end time
     */
    TimePickerDialog.OnTimeSetListener onEndTimeListener = new TimePickerDialog.OnTimeSetListener(){
        /**
         * When the time is set, update the edittext
         * @param view
         * @param hourOfDay
         * @param minute
         */
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            updateEndTime();
        }
    };


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

    /**
     * On the creation of the view
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        //Gets the date fields
        editTextStartDate = (EditText) view.findViewById(R.id.editTextStartDate);
        editTextEndDate = (EditText) view.findViewById(R.id.editTextEndDate);
        editTextLimitDate = (EditText) view.findViewById(R.id.editTextLimitDate);

        //Gets the time fields
        editTextStartTime = (EditText) view.findViewById(R.id.editTextStartTime);
        editTextEndTime = (EditText) view.findViewById(R.id.editTextEndTime);

        //Fill the spinner
        fillSpinnerWithRepetitionTypes(view);

        //Initializes the remove button
        initializeRemoveButton(view);

        //Initializes the radio group
        initializeRadioGroup(view);

        //Initializes the Date fields
        initializeDateFields(view);

        //initializes the Time fields
        initializeTimeFields(view);
    }

    /**
     * Gets the repetition types and adds them in the spinner
     * @param view
     */
    private void fillSpinnerWithRepetitionTypes(View view){
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
    }

    /**
     * Initializes the button that removes the current fragment from the activity
     * @param view
     */
    private void initializeRemoveButton(View view){
        //Gets the remove button
        Button removeButton = view.findViewById(R.id.removeButton);
        final ScheduleFragment fragment = this;

        //Sets the onClickListener
        removeButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Removes the current fragment from the activity
             * @param view
             */
            @Override
            public void onClick(View view) {
                ((EventActivity) getActivity()).RemoveScheduleFragment(fragment);
            }
        });
    }

    /**
     * Initializes the radio group for the choice of type of limit
     * @param view
     */
    private void initializeRadioGroup(View view){
        //Gets the radiobutton group and editTexts for the limit
        RadioGroup radioGroup = view.findViewById(R.id.radioGroupLimitType);
        final EditText editTextLimitAmount = view.findViewById(R.id.editTextLimitAmount);

        //Sets the OnCheckedChangeListener
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             * When the selected radiobutton changes, show the right field
             * @param group
             * @param checkedId
             */
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    default:
                    case R.id.radioButtonNone:
                        //Shows no field
                        editTextLimitAmount.setVisibility(View.GONE);
                        editTextLimitDate.setVisibility(View.GONE);
                        break;
                    case R.id.radioButtonLimitAmount:
                        //Shows the field for numeric limit
                        editTextLimitAmount.setVisibility(View.VISIBLE);
                        editTextLimitDate.setVisibility(View.GONE);
                        break;
                    case R.id.radioButtonLimitDate:
                        //Shows the field for date limit
                        editTextLimitAmount.setVisibility(View.GONE);
                        editTextLimitDate.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }

    /**
     * Initializes the date fields
     * @param view
     */
    private void initializeDateFields(View view){
        //Sets the OnClickListener for the start date
        editTextStartDate.setOnClickListener(new View.OnClickListener() {
            /**
             * On the click of the view, open the DatePickerDialog
             * @param v
             */
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), onStartDateListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //Sets the OnClickListener for the end date
        editTextEndDate.setOnClickListener(new View.OnClickListener() {
            /**
             * On the click of the view, open the DatePickerDialog
             * @param v
             */
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), onEndDateListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //Sets the OnClickListener for the limit date
        editTextLimitDate.setOnClickListener(new View.OnClickListener() {
            /**
             * On the click of the view, open the DatePickerDialog
             * @param v
             */
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), onLimitDateListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    /**
     * Initializes the time fields
     * @param view
     */
    private void initializeTimeFields(View view){
        //Sets the OnClickListener for the start time
        editTextStartTime.setOnClickListener(new View.OnClickListener() {
            /**
             * On the click of the view, open the DatePickerDialog
             * @param v
             */
            @Override
            public void onClick(View v) {
                new TimePickerDialog(getContext(), onStartTimeListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
            }
        });

        //Sets the OnClickListener for the end time
        editTextEndTime.setOnClickListener(new View.OnClickListener() {
            /**
             * On the click of the view, open the DatePickerDialog
             * @param v
             */
            @Override
            public void onClick(View v) {
                new TimePickerDialog(getContext(), onEndTimeListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
            }
        });

        //Sets the OnClickListener for the limit date
        editTextLimitDate.setOnClickListener(new View.OnClickListener() {
            /**
             * On the click of the view, open the DatePickerDialog
             * @param v
             */
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), onLimitDateListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    /**
     * Updates the start date editText with the date entered in the calendar
     */
    private void updateStartDate() {
        editTextStartDate.setText(dateFormat.format(calendar.getTime()));
    }

    /**
     * Updates the end date editText with the date entered in the calendar
     */
    private void updateEndDate() {
        editTextEndDate.setText(dateFormat.format(calendar.getTime()));
    }

    /**
     * Updates the limit date editText with the date entered in the calendar
     */
    private void updateLimitDate() {
        editTextLimitDate.setText(dateFormat.format(calendar.getTime()));
    }

    /**
     * Updates the start date editText with the date entered in the calendar
     */
    private void updateStartTime() {
        editTextStartTime.setText(timeFormat.format(calendar.getTime()));
    }

    /**
     * Updates the end date editText with the date entered in the calendar
     */
    private void updateEndTime() {
        editTextEndTime.setText(timeFormat.format(calendar.getTime()));
    }

    /**
     * Public onCreateView method
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_schedule, container, false);
    }

    /**
     * Public onAttach method
     * @param context
     */
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

    /**
     * Public onDetach method
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this fragment
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }

    /**
     * Builds a schedule object with the data entered
     * @return
     */
    public Schedule getSchedule(int eventID){
        //Gets the dates strings and tries to convert it
        String startDateString = ((EditText)this.getView().findViewById(R.id.editTextStartDate)).getText().toString();
        String startTimeString = ((EditText)this.getView().findViewById(R.id.editTextStartTime)).getText().toString();
        String endDateString = ((EditText)this.getView().findViewById(R.id.editTextEndDate)).getText().toString();
        String endTimeString = ((EditText)this.getView().findViewById(R.id.editTextEndTime)).getText().toString();
        Date startDate = new Date();
        Date endDate = new Date();;
        try {
            startDate = dateTimeFormat.parse(startDateString + " " + startTimeString);
            endDate = dateTimeFormat.parse(endDateString + " " + endTimeString);
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
        if(type == RepetitionType.NONE){
            return null;
        }
        int amount = Integer.parseInt(((EditText)getView().findViewById(R.id.editTextFrequency)).getText().toString());
        Boolean isActiveDuringHolidays = ((CheckBox)getView().findViewById(R.id.checkBoxDuringHolidays)).isChecked();

        //Depending of the type of limit selected, create the Repetition
        RadioGroup group = getView().findViewById(R.id.radioGroupLimitType);
        switch (group.getCheckedRadioButtonId()){
            default:
            case R.id.radioButtonNone:
                //Returns a new repetition with no limit
                return new Repetition(scheduleId, type, amount, isActiveDuringHolidays);
            case R.id.radioButtonLimitDate:
                //Creates the date with the input gotten
                String limitDateString = ((EditText)this.getView().findViewById(R.id.editTextLimitDate)).getText().toString();
                Date limitDate = new Date();
                try {
                    limitDate = dateFormat.parse(limitDateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //Returns new repetition with limit date
                return new Repetition(scheduleId, type, amount, limitDate, isActiveDuringHolidays);
            case R.id.radioButtonLimitAmount:
                //Gets the maximum of repetitions entered
                int maximumOfRepetitions = Integer.parseInt(((EditText)getView().findViewById(R.id.editTextLimitAmount)).getText().toString());

                //Returns new repetition with maimum amount of repetitions
                return new Repetition(scheduleId, type, amount, maximumOfRepetitions, isActiveDuringHolidays);
        }
    }
}
