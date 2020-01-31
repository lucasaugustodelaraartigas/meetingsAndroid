package com.meetings.Activitieis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.meetings.Adapters.ContaUsuario;
import com.meetings.R;
import com.meetings.Services.CadastroService;
import com.meetings.Services.OrganizacaoService;
import com.meetings.models.Organizacao;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CadastroActivity extends AppCompatActivity {
    CadastroService dao = new CadastroService();
    private EditText campoEmailCadastro;
    private EditText campoSenhaCadastro;
    private EditText campoNomeCadastro;
    private ContaUsuario contaUsuario;
    int empSelected;
    List<Organizacao> organizacoes = new ArrayList<>();
    List<String> listaDeStrings = new ArrayList<>();

    public void validaCadastro() {
        try {
            String nome = campoNomeCadastro.getText().toString();
            String email = campoEmailCadastro.getText().toString();
            String senha = campoSenhaCadastro.getText().toString();
            String retornoCadastro = new CadastroService().execute(email, nome, senha, Integer.toString(empSelected)).get();
            if (retornoCadastro.equals("Usuário criado com sucesso")) {
                Toast.makeText(CadastroActivity.this, retornoCadastro, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CadastroActivity.this, LoginActivity.class));
            } else {
                Toast.makeText(CadastroActivity.this, retornoCadastro, Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        setTitle("Meetings");

        Button botaoCadastrar = findViewById(R.id.cadastrar);
        final Spinner spinnerOrganizacoes = findViewById(R.id.spinnerOrganizacao);


        campoEmailCadastro = findViewById(R.id.emailCadastro);
        campoNomeCadastro = findViewById(R.id.nomeCadastro);
        campoSenhaCadastro = findViewById(R.id.senhaCadastro);

        spinnerOrganizacoes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                empSelected = organizacoes.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validaCadastro();
            }
        });

        campoEmailCadastro.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String emailAfterTextChange = campoEmailCadastro.getText().toString();
                    if (emailAfterTextChange.contains("@")) {
                        String dominio = emailAfterTextChange.substring(emailAfterTextChange.indexOf('@') + 1);
                        if (dominio.contains(".")) {
//                            String[] "dominio", a2={emailCompleto};
//                            Log.i("teste", "onFocusChange: entrou");

                            try {
                                String retornoOrganizacoes = new OrganizacaoService().execute(dominio).get();
                                System.out.println(retornoOrganizacoes);
                                organizacoes.clear();
                                listaDeStrings.clear();
                                List<Organizacao> organizacoes = parseOrganizacoesArray(retornoOrganizacoes);
                                ArrayAdapter<String> adapter = new ArrayAdapter<>(CadastroActivity.this, android.R.layout.simple_spinner_item, listaDeStrings);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinnerOrganizacoes.setAdapter(adapter);
                                spinnerOrganizacoes.setVisibility(View.VISIBLE);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

    }

    public List<Organizacao> parseOrganizacoesArray(String retornoOrganizacoes) {
        try {
            JSONArray jsonArray = new JSONArray(retornoOrganizacoes);
            if (jsonArray.length() > 0) {

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    if (obj.has("id") && obj.has("nome") && obj.has("tipoOrganizacao")) {
                        int id = obj.getInt("id");
                        String nome = obj.getString("nome");
                        String tipoOrganizacao = obj.getString("tipoOrganizacao");
                        Organizacao novaOrganizacao = new Organizacao();
                        novaOrganizacao.setId(id);
                        novaOrganizacao.setNome(nome);
                        novaOrganizacao.setTipoOrganizacao(tipoOrganizacao);

                        organizacoes.add(novaOrganizacao);
                        listaDeStrings.add(novaOrganizacao.getNome());
                    }
                }
                return organizacoes;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

