package com.meetings.Activitieis;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.meetings.DAO.SalaDAO;
import com.meetings.ListaSalasAdapter;
import com.meetings.R;
import com.meetings.Services.LoginService;
import com.meetings.models.Sala;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static Context contextOfApplication;
    List<Sala> salas = new SalaDAO().lista();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contextOfApplication = getApplicationContext();
        setContentView(R.layout.activity_main);
        ImageButton botaoLogarConvidado = findViewById(R.id.botaoLogarFromConvidado);
        ImageButton botaoCalendario = findViewById(R.id.botaoCalendario);

        ListView lista = findViewById(R.id.lista_Salas);

        lista.setAdapter(new ListaSalasAdapter(salas, this));


        if(LoginActivity.convidado == false && LoginService.logado == false){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

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
            if(LoginActivity.convidado == false && LoginService.logado == true) {
                botaoLogarConvidado.setVisibility(View.INVISIBLE);
                botaoCalendario.setVisibility(View.VISIBLE);
                botaoCalendario.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {

                            startActivity(new Intent(MainActivity.this, MenuReservasActivity.class));
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                });
            }
        }

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }
    }