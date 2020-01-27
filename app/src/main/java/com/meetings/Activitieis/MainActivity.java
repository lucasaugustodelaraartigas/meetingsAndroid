package com.meetings.Activitieis;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.meetings.R;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;



public class MainActivity extends AppCompatActivity {
    private EditText campoEmail;
    private EditText campoSenha;
    private ContaUsuario contaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Meetings");

        inicializacaoDosCampos();

        Button botaoLogin = findViewById(R.id.login);
        //Button botaoCadastrar = findViewById(R.id.cadastrar);

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
    private void inicializacaoDosCampos() {
        campoEmail = findViewById(R.id.email);
        campoSenha = findViewById(R.id.senha);
    }


    private void preencheCadastro() {
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        if (contaUsuario != null) {
            contaUsuario.setEmail(email);
            contaUsuario.setSenha(senha);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.login){
            finalizaLogin();
        }
        return super.onOptionsItemSelected(item);
    }

    private void finalizaLogin() {

    }

    private void preencheCampos() {
        campoEmail.setText(contaUsuario.getEmail());
        campoSenha.setText(contaUsuario.getSenha());
    }


}

