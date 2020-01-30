package com.meetings.Activitieis;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.meetings.Adapters.ContaUsuario;
import com.meetings.R;
import com.meetings.Services.LoginService;

public class LoginActivity extends AppCompatActivity {
    private EditText campoEmail;
    private EditText campoSenha;
    private ContaUsuario contaUsuario;


    public void validaLogin(){
        String returnLogin = "0";
        String email = campoEmail.getText().toString();
        String password = campoSenha.getText().toString();

        try {
            returnLogin = new LoginService().execute(email, password).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(returnLogin.contains("Login efetuado com sucesso!")){
            Toast.makeText(LoginActivity.this, "Logado com sucesso!", Toast.LENGTH_SHORT).show();
            LoginService.logado = true;
            startActivity(new Intent( LoginActivity.this, MainActivity.class));
        }else{
            Toast.makeText(LoginActivity.this, "Erro ao logar", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Meetings");

        inicializacaoDosCampos();

        Button botaoLogin = findViewById(R.id.login);
        Button botaoFazerCadastro = findViewById(R.id.fazerCadastro);

        campoEmail = findViewById(R.id.email);
        campoSenha = findViewById(R.id.senha);

        EditText campoEmailCadastro = findViewById(R.id.emailCadastro);
        EditText campoNomeCadastro = findViewById(R.id.nomeCadastro);
        EditText campoSenhaCadastro = findViewById(R.id.senhaCadastro);

        botaoFazerCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Teste", "onClick: ENTROU");
                startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
            }
        });

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaLogin();

                /*if (campoEmail.getText().toString().equals("lucas@wises.com.br") && campoSenha.getText().toString().equals("123"))
                {
                    Toast.makeText(LoginActivity.this, "Logado com sucesso", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                }else{
                    Toast.makeText(LoginActivity.this, "Erro ao logar", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }


    private void inicializacaoDosCampos() {
        campoEmail = findViewById(R.id.email);
        campoSenha = findViewById(R.id.senha);
    }

}
