package com.meetings.Activitieis;

import android.widget.ImageButton;
import android.widget.ImageView;

import com.meetings.R;
import java.io.Serializable;
import java.math.BigDecimal;

public class Sala {
    //private final ImageView fotoSala;
    private final String nomeSala;
    private final String corTexto;
    private final String andar;
    private final String status;

    public Sala(String nomeSala, String corTexto, String andar, String status) {
        //this.fotoSala = fotoSala;
        this.nomeSala = nomeSala;
        this.corTexto = corTexto;
        this.andar = andar;
        this.status = status;
    }

    /*public ImageView getFotoSala() {
        return fotoSala;
    }*/

    public String getNomeSala() {
        return nomeSala;
    }

    public String getCorTexto() {
        return corTexto;
    }

    public String getAndar() {
        return andar;
    }

    public String getStatus() {
        return status;
    }
}
