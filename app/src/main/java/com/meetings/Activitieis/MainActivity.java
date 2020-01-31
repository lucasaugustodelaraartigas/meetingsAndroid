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
        setContentView(R.layout.activity_main);
        ImageButton botaoLogarConvidado = findViewById(R.id.botaoLogarFromConvidado);
        ImageButton botaoCalendario = findViewById(R.id.botaoCalendario);
        if (LoginService.logado == false && LoginActivity.convidado == false) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        } else {
            if(LoginActivity.convidado == true && LoginService.logado == false) {
                botaoCalendario.setVisibility(View.INVISIBLE);
                botaoLogarConvidado.setVisibility(View.VISIBLE);
                botaoLogarConvidado.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoginActivity.convidado=false;
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));

                    }
                });
        }
            if(LoginService.logado == true && LoginActivity.convidado == false) {
                botaoLogarConvidado.setVisibility(View.INVISIBLE);
                botaoCalendario.setVisibility(View.VISIBLE);
                botaoCalendario.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(MainActivity.this, MenuReservasActivity.class));

                    }
                });
            }
        }
    }
}