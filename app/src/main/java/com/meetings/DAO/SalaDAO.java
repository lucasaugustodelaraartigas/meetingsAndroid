package com.meetings.DAO;

import android.widget.ImageButton;

import com.meetings.Activitieis.Sala;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SalaDAO {

    public List<Sala> lista() {

        List<Sala> salas = new ArrayList<>(Arrays.asList(
                new Sala("Conselho Jedi", "#FFFFFF", "** Andar", "string/statusSala"),
                new Sala("Coworking", "#08088A", "** Andar", "string/statusSala"),
                new Sala("Fábrica de Softtware", "#0B610B", "** Andar", "string/statusSala"),
                new Sala("Sala-Cozinha", "#DF3A01", "** Andar", "string/statusSala"),
                new Sala("Sala da criatividade", "#FFBF00", "** Andar", "string/statusSala"),
                new Sala("Wise Lab", "#FFFF00", "** Andar", "string/statusSala")));
        return salas;
    }

}
