package com.meetings.Activitieis;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private EditText campoEmail;
    private EditText campoSenha;
    private ContaUsuario contaUsuario;
    static public String returnLogin;

    SharedPreferences pref;

    public void validaLogin() {
        String email = campoEmail.getText().toString();
        String password = campoSenha.getText().toString();

        try {
            returnLogin = new LoginService().execute(email, password).get();
//            if(pref.getString("userEmail", null)!=null){
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//            }

        if (returnLogin.length() > 0) {
            JSONObject userObj = new JSONObject(returnLogin);
            String nome = userObj.getString("nome");
            int id = userObj.getInt("id");
            String userEmail = userObj.getString("email");
            JSONObject organizacao = userObj.getJSONObject("idOrganizacao");
            int idOrganizacao = organizacao.getInt("id");
            String nomeOrganizacao = organizacao.getString("nome");

            SharedPreferences prefs = getSharedPreferences("USER_DATA", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            editor.putString("userNome", nome);
            editor.putString("userId", Integer.toString(id));
            editor.putString("userEmail", userEmail);
            editor.putString("idOrganizacao", Integer.toString(idOrganizacao));
            editor.putString("nomeOrganizacao", nomeOrganizacao);

            editor.commit();

            Toast.makeText(LoginActivity.this, "Logado com sucesso!", Toast.LENGTH_SHORT).show();
            LoginService.logado = true;
            SalvaEmail();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        } else {
            Toast.makeText(LoginActivity.this, "Erro ao logar", Toast.LENGTH_SHORT).show();
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SalvaEmail() {
        pref = getApplicationContext().getSharedPreferences("USER_DATA", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("email", campoEmail.getText().toString());
        editor.commit();
        System.out.println(pref.getString("email", null));
    }
//    public void PegaEmail(){
//        campoEmail.setText(pref.getString("key_name", null));
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Meetings");

        inicializacaoDosCampos();

        validaLogin();

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

            }
        });
    }


    private void inicializacaoDosCampos() {
        campoEmail = findViewById(R.id.email);
        campoSenha = findViewById(R.id.senha);
    }

}
