package com.meetings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.meetings.models.Sala;

import java.util.List;

public class ListaSalasAdapter extends BaseAdapter {
    private List<Sala> salas;
    private final Context context;


    public ListaSalasAdapter (List<Sala> salas, Context context) {
        this.salas = salas;
        this.context = context;
    }

    /*public void atualiza(List<Sala> salas) {
        this.salas.clear();
        this.salas.addAll(salas);
    }*/

    @Override
    public int getCount() {
        return salas.size();
    }

    @Override
    public Object getItem(int position) {
        return salas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewCriada = LayoutInflater
                .from(context)
                .inflate(R.layout.sala, viewGroup, false);
        Sala sala = salas.get(i);

        TextView nome = view.findViewById(R.id.nomeSala);
        nome.setText(sala.getNomeSala());

        TextView localizacao = view.findViewById(R.id.Andar);
        localizacao.setText(sala.getLocalizacao());

        return viewCriada;
    }

}
