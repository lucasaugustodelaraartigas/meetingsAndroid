package com.meetings.Services;

import android.os.AsyncTask;
import android.util.Base64;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CadastroService extends AsyncTask<String, Void, String> {

    static public boolean logado = false;

    @Override
    protected String doInBackground(String... strings) {
        try {
        String urlWs = "http://172.30.248.113:8080/ReservaDeSala/rest/usuario/cadastro/";

        StringBuilder result = new StringBuilder();


            String name, password, email, dominio;

            JSONObject usuarioJson = new JSONObject();

            usuarioJson.put("email", strings[0]);
            usuarioJson.put("nome", strings[1]);
            usuarioJson.put("senha", strings[2]);
            usuarioJson.put("idOrganizacao", strings[3]);

            String novoUsuarioEncoded = Base64.encodeToString(usuarioJson.toString().getBytes("UTF-8"), Base64.NO_WRAP);
            System.out.println(usuarioJson.toString());
            System.out.println(novoUsuarioEncoded);
            URL url = new URL(urlWs);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("authorization", "secret");
            conn.setRequestProperty("novoUsuario", novoUsuarioEncoded);
            conn.setDoOutput(true);

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
}