package com.meetings.Activitieis;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.meetings.DAO.SalaDAO;
import com.meetings.R;
import com.meetings.Services.LoginService;
import com.meetings.models.Sala;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static Context contextOfApplication;
    int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contextOfApplication = getApplicationContext();
        List<Sala> salas = new SalaDAO().listaCompleta();
        final List<String> nomeSalas = new SalaDAO().listaNome();
        setContentView(R.layout.activity_main);
        ImageButton botaoCalendario = findViewById(R.id.botaoCalendario);

        final ListView lista = findViewById(R.id.lista_Salas);

        //lista.setAdapter(new ListaSalasAdapter(salas, this));


        if (LoginService.logado == false) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
        if (LoginService.logado == true) {
            botaoCalendario.setVisibility(View.VISIBLE);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, nomeSalas);
            adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
            lista.setAdapter(adapter);

            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    posicao = position;
                    startActivity(new Intent(MainActivity.this, MenuReservasActivity.class));
                }
            });

            botaoCalendario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        startActivity(new Intent(MainActivity.this, MenuReservasActivity.class));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            });
        }
    }

    public static Context getContextOfApplication() {
        return contextOfApplication;
    }
}