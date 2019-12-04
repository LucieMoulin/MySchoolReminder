/**
 * ETML
 * Authors : Lucie Moulin and Léa Cherpillod
 * Date : 06.11.2019
 * Description : Menu to choose an event type to add
 */
package com.example.myschoolreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EventTypeMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_type_menu);

        Button btnAddElement = findViewById(R.id.btnAddEvent);

        btnAddElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EventTypeMenuActivity.this, EventActivity.class));
            }
        });
    }
}
