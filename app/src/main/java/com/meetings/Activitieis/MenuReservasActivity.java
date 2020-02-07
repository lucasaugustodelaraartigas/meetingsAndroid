package com.meetings.Activitieis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.meetings.R;

public class MenuReservasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_reservas);
        setTitle("Meetings");

//        final CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
//
//            }
//        });

        Button botaoCancelar = findViewById(R.id.botaoCancelar);
        botaoCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(MenuReservasActivity.this, MainActivity.class));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

    }

}
