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

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;

import android.util.Base64;

public class CadastroActivity extends AppCompatActivity {
    DAO dao = new DAO();
    private EditText campoEmailCadastro;
    private EditText campoSenhaCadastro;
    private EditText campoNomeCadastro;
    private ContaUsuario contaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        setTitle("Meetings");

        Button botaoCadastrar = findViewById(R.id.cadastrar);

        campoEmailCadastro = findViewById(R.id.emailCadastro);
        campoNomeCadastro = findViewById(R.id.nomeCadastro);
        campoSenhaCadastro = findViewById(R.id.senhaCadastro);


        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaCadastro();
            }
        });

    }

    public void validaCadastro() {
        try {
            String name, password, email;
            name = campoNomeCadastro.getText().toString();
            ;
            password = campoSenhaCadastro.getText().toString();
            email = campoEmailCadastro.getText().toString();

            JSONObject usuarioJson = new JSONObject();

            usuarioJson.put("email", email);
            usuarioJson.put("nome", name);
            usuarioJson.put("senha", password);
            StringBuilder result = new StringBuilder();

            String novoUsuarioEncoded = Base64.encodeToString(usuarioJson.toString().getBytes("UTF-8"), Base64.NO_WRAP);
            System.out.println(usuarioJson.toString());
            System.out.println(novoUsuarioEncoded);
            URL url = new URL("http://172.30.248.113:8080/ReservaDeSala/rest/usuario/cadastro");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("authorization", "secret");
            conn.setRequestProperty("novoUsuario", novoUsuarioEncoded);
            conn.setDoOutput(true);

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null)
            {
                result.append(line);
            }
            rd.close();


            Toast.makeText(CadastroActivity.this, result.toString(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(CadastroActivity.this, LoginActivity.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

