package com.meetings.Activitieis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.meetings.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Lista de alunos");
        ///ListView Salas = findViewById(R.layout.sala);
        //ListaSalasAdapter adapter = new ListaSalasAdapter(MainActivity.this);
        //Salas.setAdapter(adapter);
    }


    }
