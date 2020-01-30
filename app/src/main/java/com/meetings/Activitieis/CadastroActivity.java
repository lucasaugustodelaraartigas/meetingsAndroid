package com.meetings.Activitieis;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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

    public void validaCadastro() {


//        Toast.makeText(CadastroService.this, result.toString(), Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(CadastroService.this, LoginActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        int empSelected;
        setTitle("Meetings");

        Button botaoCadastrar = findViewById(R.id.cadastrar);
        ///final Spinner spinnerOrganizacoes = findViewById(R.id.spinnerOrganizacao);


        campoEmailCadastro = findViewById(R.id.emailCadastro);
        campoNomeCadastro = findViewById(R.id.nomeCadastro);
        campoSenhaCadastro = findViewById(R.id.senhaCadastro);

//            spinnerOrganizacoes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    empSelected=lista.get(position).getId();
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//                }
//            });


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
                        String[] emailCompleto = emailAfterTextChange.split("@");
                        if (emailCompleto[1] != null && emailCompleto[1].length > 0) {
                            String dominio = emailCompleto[1];
                            if (dominio.contains(".")) {
                                System.out.println("dominio: " + dominio);

                                // aqui fazer request para puxar as organizacoes com o dominio informado
                                try {
                                    String retornoOrganizacoes = new OrganizacaoService().execute(dominio).get();
                                    System.out.println(retornoOrganizacoes);

                                    List<Organizacao> organizacoes = parseOrganizacoesArray(retornoOrganizacoes);
                                    //spinnerOrganizacoes.setVisibility(View.VISIBLE);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
        /*campoEmailCadastro.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                System.out.println("chamou afterTextChanged");
                try{
                String emailAfterTextChange = campoEmailCadastro.getText().toString();
                if(emailAfterTextChange.contains("@")){
                    String[] emailCompleto = emailAfterTextChange.split("@");
                    if(emailCompleto[1] != null && emailCompleto[1].length() > 0){
                        String dominio = emailCompleto[1];
                        if(dominio.contains(".")){
                            System.out.println("dominio: " + dominio);
                        }
                    }
                }
            }
                catch (Exception e){
                    e.printStackTrace();
                }

        });

    }*/
            }
        });

    }

    public List<Organizacao> parseOrganizacoesArray(String retornoOrganizacoes) {
        try {
            JSONArray jsonArray = new JSONArray(retornoOrganizacoes);
            List<Organizacao> listaDeOrganizacoes = new ArrayList();
            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length; i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    if (obj.has("id") && obj.has("nome") && obj.has("tipoOrganizacao")) {
                        int id = obj.getInt("id");
                        String nome = obj.getString("nome");
                        int tipoOrganizacao = obj.getInt("tipoOrganizacao");
                        Organizacao novaOrganizacao = new Organizacao();
                        novaOrganizacao.setId(id);
                        novaOrganizacao.setNome(nome);
                        novaOrganizacao.setTipoOrganizacao(tipoOrganizacao);

                        listaDeOrganizacoes.add(novaOrganizacao);
                    }
                }
                return  listaDeOrganizacoes;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

