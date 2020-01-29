package com.meetings.Activitieis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.meetings.Adapters.ContaUsuario;
import com.meetings.R;


import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    DAO dao = new DAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (dao.logado == false) {
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