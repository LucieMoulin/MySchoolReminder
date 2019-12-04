/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 06.11.2019
 * Description : Menu to choose an event type to add
 */
package com.example.myschoolreminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myschoolreminder.Objects.EventType;

public class EventTypeMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_type_menu);

        //Gets the repetition types
        final EventType[] rawTypes = EventType.values();

        //Layout
        ConstraintLayout layout = findViewById(R.id.eventTypeMenuLayout);

        for (int i = 0; i < EventType.values().length; i++) {
            Button btnAddElement = new Button(getApplicationContext());
            btnAddElement.setText(rawTypes[i].getName());
            //TODO ajouter les paramètres de layout

            final int finalI = i;

            btnAddElement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(EventTypeMenuActivity.this, EventActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("eventType", rawTypes[finalI].getName());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });


            layout.addView(btnAddElement);
        }


    }
}
