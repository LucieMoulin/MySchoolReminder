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

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_type_menu);

        //Gets the repetition types
        final EventType[] rawTypes = EventType.values();
        ArrayList<Button> buttons = new ArrayList<Button>();

        //Layout
        ConstraintLayout layout = findViewById(R.id.eventTypeMenuLayout);

        //Creates all the buttons
        for (final EventType type : EventType.values()) {
            Button btnAddElement = new Button(getApplicationContext());
            btnAddElement.setText(type.getName());
            btnAddElement.setId(View.generateViewId());

            btnAddElement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(EventTypeMenuActivity.this, EventActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("eventType", type.getName());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

            buttons.add(btnAddElement);
            layout.addView(btnAddElement);
        }

        //gets all the ids
        int[] ids = new int[buttons.size()];
        for (int i = 0; i < buttons.size(); i++) {
            ids[i] = buttons.get(i).getId();
        }

        ConstraintSet set = new ConstraintSet();
        set.clone(layout);

        set.createVerticalChain(R.id.txtEventTypeTitle, ConstraintSet.BOTTOM, layout.getId(), ConstraintSet.BOTTOM, ids, null, ConstraintSet.CHAIN_SPREAD);

        for (Button button : buttons) {
            set.centerHorizontally(button.getId(), layout.getId());
        }

        set.applyTo(layout);
    }
}
