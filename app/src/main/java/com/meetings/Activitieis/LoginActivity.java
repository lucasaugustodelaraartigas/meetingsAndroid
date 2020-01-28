package com.meetings.Activitieis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.meetings.Adapters.ContaUsuario;
import com.meetings.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    private EditText campoEmail; //= findViewById(R.id.email);
    private EditText campoSenha; //= findViewById(R.id.senha);
    private ContaUsuario contaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Meetings");

        inicializacaoDosCampos();

        Button botaoLogin = findViewById(R.id.login);

        campoEmail = findViewById(R.id.email);
        campoSenha = findViewById(R.id.senha);



        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = campoEmail.getText().toString();
                String senha = campoSenha.getText().toString();

                try {
                    makeAuthRequest(email,senha);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                /*if (email.getText().toString().equals("clovis@wises.com") && denha.getText().toString().equals("123"))
                {
                    Toast.makeText(LoginActivity.this, "Logado com sucesso", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                }else{
                    Toast.makeText(LoginActivity.this, "Erro ao logar", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }

    public static int makeAuthRequest(String email, String senha) throws Exception {

            String urlWS = "http://172.30.248.56:8080/ReservaDeSala/rest/usuario/login/";

            try {
                StringBuilder result = new StringBuilder();
                URL url = new URL(urlWS);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("authorization", "secret");
                conn.setRequestProperty("email", email);
                conn.setRequestProperty("password", senha);

                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                rd.close();
                return Integer.parseInt(result.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return  0;
    }

    private void inicializacaoDosCampos() {
        campoEmail = findViewById(R.id.email);
        campoSenha = findViewById(R.id.senha);
    }

}
