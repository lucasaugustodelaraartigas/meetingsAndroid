package com.meetings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.meetings.Activitieis.Sala;

import java.util.ArrayList;
import java.util.List;

public class ListaSalasAdapter extends BaseAdapter {
    private final List<Sala> salas = new ArrayList<>();
    private Context context;

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
        View viewCriada = criaVIew(viewGroup);
        Sala salaDevolvida = salas.get(i);
        vincula(viewCriada, salaDevolvida);
        return viewCriada;
    }

    private void vincula(View view, Sala sala) {
        TextView nomeSala = view.findViewById(R.id.nomeSala);
        //nome.setText(sala.getNome());
    }

    private View criaVIew(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.sala, viewGroup, false);
    }

}
