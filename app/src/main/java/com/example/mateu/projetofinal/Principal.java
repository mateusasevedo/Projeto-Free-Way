package com.example.mateu.projetofinal;

import android.os.Bundle;

import com.example.mateu.projetofinal.AndGraph.AGActivityGame;

public class Principal extends AGActivityGame {

    CenaAbertura abertura=null;
    CenaJogo menu=null;
    CenaAjuda ajuda=null;
    CenaSobre sobre=null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //DA PARTIDA NO MOTOR
        init(this, false);

        //INSTANCIA O OBJETO DE CENA
        abertura = new CenaAbertura(getGameManager());
        menu = new CenaJogo(getGameManager());
        ajuda = new CenaAjuda(getGameManager());
        sobre = new CenaSobre(getGameManager());
        //REGISTRA A CENA AO GERENCIADOR
        getGameManager().addScene(abertura);
        getGameManager().addScene(menu);
        getGameManager().addScene(ajuda);
        getGameManager().addScene(sobre);

    }
}
