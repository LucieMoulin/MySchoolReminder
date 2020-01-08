/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 06.11.2019
 * Description : Menu to choose an event type to add
 */
package com.example.myschoolreminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myschoolreminder.Objects.EventType;

import java.util.ArrayList;

public class EventTypeMenuActivity extends AppCompatActivity {

    /**
     * When the view is being created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_type_menu);

        CreateLayout();
    }

    /**
     * Creates the buttons for the event creation
     */
    private void CreateLayout(){
        //Gets the repetition types
        final EventType[] rawTypes = EventType.values();
        ArrayList<Button> buttons = new ArrayList<Button>();

        //Creates the layout
        ConstraintLayout layout = findViewById(R.id.eventTypeMenuLayout);

        //Create a button for each event type
        for (final EventType type : EventType.values()) {
            Button btnAddElement = new Button(getApplicationContext());
            btnAddElement.setText(type.getName());
            btnAddElement.setId(View.generateViewId());

            //Adds the click listener
            btnAddElement.setOnClickListener(new View.OnClickListener() {
                /**
                 * Is executed on click
                 * @param view
                 */
                @Override
                public void onClick(View view) {
                    //Opens the Event activity with the event type selected
                    Intent intent = new Intent(EventTypeMenuActivity.this, EventActivity.class);
                    intent.putExtra("eventType", type.ordinal());
                    startActivity(intent);
                }
            });

            buttons.add(btnAddElement);
            layout.addView(btnAddElement);
        }

        //Gets all the ids of the buttons
        int[] ids = new int[buttons.size()];
        for (int i = 0; i < buttons.size(); i++) {
            ids[i] = buttons.get(i).getId();
        }

        //Creates a constraint layout set
        ConstraintSet set = new ConstraintSet();
        set.clone(layout);

        //Creates vertical chain containing the buttons
        set.createVerticalChain(R.id.txtEventTypeTitle, ConstraintSet.BOTTOM, layout.getId(), ConstraintSet.BOTTOM, ids, null, ConstraintSet.CHAIN_SPREAD);

        //Centers each button horizontally
        for (Button button : buttons) {
            set.centerHorizontally(button.getId(), layout.getId());
        }

        set.applyTo(layout);
    }
}
