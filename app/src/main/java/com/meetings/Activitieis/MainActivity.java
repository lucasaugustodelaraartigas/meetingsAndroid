package com.meetings.Activitieis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.meetings.R;
import com.meetings.Services.LoginService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (LoginService.logado == false) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        } else {
            setContentView(R.layout.activity_main);
            ImageButton botaoCalendario = findViewById(R.id.botaoCalendario);

            botaoCalendario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(MainActivity.this, MenuReservasActivity.class));

                }
            });
        }
    }
}