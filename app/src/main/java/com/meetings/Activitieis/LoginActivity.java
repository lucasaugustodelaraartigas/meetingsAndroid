package com.meetings.Activitieis;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.meetings.R;

public class LoginActivity extends AppCompatActivity{
    private EditText campoEmail;
    private EditText campoSenha;
    private ContaUsuario contaUsuario;
    final ContaUsuarioDAO dao = new ContaUsuarioDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Entrar");

        inicializacaoDosCampos();
    }

    private void inicializacaoDosCampos() {
        campoEmail = findViewById(R.id.email);
        campoSenha = findViewById(R.id.senha);
    }


    private void Salva(ContaUsuario contaUsuario, ContaUsuarioDAO dao) {
        dao.salva(contaUsuario);

        finish();
    }


    private void preencheAluno() {
        String email = campoEmail.getText().toString();
        String senha = campoSenha.getText().toString();

        ///return new Aluno(nome, telefone, email);
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
