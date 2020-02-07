package com.meetings.DAO;

import android.app.Activity;
import android.content.SharedPreferences;

import com.meetings.Services.SalasService;
import com.meetings.models.Sala;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.meetings.Activitieis.MainActivity.getContextOfApplication;

public class SalaDAO {

    List<Sala> salas = new ArrayList<>();
    List<String> nomeSalas = new ArrayList<>();

    public List<Sala> listaCompleta() {
        try {
            SharedPreferences prefs = getContextOfApplication().getSharedPreferences("USER_DATA", Activity.MODE_PRIVATE);
            String returnSalas = null;
            returnSalas = new SalasService().execute(prefs.getString("idOrganizacao", null)).get();

            JSONArray salasJSON = new JSONArray(returnSalas);
            if (returnSalas.length() > 2) {
                for (int i = 0; i < salasJSON.length(); i++) {
                    JSONObject salaJSON = salasJSON.getJSONObject(i);

                    if (salaJSON.has("id") && salaJSON.has("nome") && salaJSON.has("localizacao")) {
                        int id = salaJSON.getInt("id");
                        String localizacao = salaJSON.getString("localizacao");
                        String nome = salaJSON.getString("nome");

                        Sala sala = new Sala(nome, localizacao, id);

                        salas.add(sala);
                        nomeSalas.add(sala.getNomeSala());

                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return salas;
    }
    public List<String> listaNome() {
        try {
            SharedPreferences prefs = getContextOfApplication().getSharedPreferences("USER_DATA", Activity.MODE_PRIVATE);
            String returnSalas = null;
            returnSalas = new SalasService().execute(prefs.getString("idOrganizacao", null)).get();

            JSONArray salasJSON = new JSONArray(returnSalas);
            if (returnSalas.length() > 2) {
                for (int i = 0; i < salasJSON.length(); i++) {
                    JSONObject salaJSON = salasJSON.getJSONObject(i);

                    if (salaJSON.has("nome")) {
                        String nome = salaJSON.getString("nome");

                        nomeSalas.add(nome);

                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return nomeSalas;
    }

}
//                new Sala("Conselho Jedi", "#FFFFFF", "** Andar", "string/statusSala"),
//                new Sala("Coworking", "#08088A", "** Andar", "string/statusSala"),
//                new Sala("Fábrica de Softtware", "#0B610B", "** Andar", "string/statusSala"),
//                new Sala("Sala-Cozinha", "#DF3A01", "** Andar", "string/statusSala"),
//                new Sala("Sala da criatividade", "#FFBF00", "** Andar", "string/statusSala"),
//                new Sala("Wise Lab", "#FFFF00", "** Andar", "string/statusSala")));