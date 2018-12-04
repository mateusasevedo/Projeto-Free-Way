package com.example.mateu.projetofinal;

import com.example.mateu.projetofinal.AndGraph.AGGameManager;
import com.example.mateu.projetofinal.AndGraph.AGInputManager;
import com.example.mateu.projetofinal.AndGraph.AGScene;
import com.example.mateu.projetofinal.AndGraph.AGScreenManager;
import com.example.mateu.projetofinal.AndGraph.AGSoundManager;
import com.example.mateu.projetofinal.AndGraph.AGSprite;
import com.example.mateu.projetofinal.AndGraph.AGTimer;

public class CenaAbertura extends AGScene {
    AGTimer intervaloTempo = null;
    AGSprite fundo = null;
    AGSprite jogar = null;
    AGSprite sobre = null;
    AGSprite ajuda = null;
    int codigosom;

    public CenaAbertura(AGGameManager manager) {
        super(manager);
    }

    @Override
    public void init() {
        // METODO CHAMADO NO INICIO DA TELA
        intervaloTempo = new AGTimer(3000);

        AGSoundManager.vrMusic.loadMusic("galinha.mp3", true);
        AGSoundManager.vrMusic.play();
        codigosom = AGSoundManager.vrSoundEffects.loadSoundEffect("toc.wav");
//        AGSoundManager.vrSoundEffects.play(codigo);


        fundo = createSprite(R.mipmap.fundo, 1, 1);
        jogar = createSprite(R.mipmap.jogar, 1, 1);
        ajuda = createSprite(R.mipmap.ajuda, 1, 1);
        sobre = createSprite(R.mipmap.sobre, 1, 1);


        //QUANTOS POR CENTO DA TELA VAI OCUPAR NA LARGURA E ALTURA
        fundo.setScreenPercent(100, 100);
        jogar.setScreenPercent(30, 10);
        ajuda.setScreenPercent(30, 8);
        sobre.setScreenPercent(30, 10);

        //POSICAO FUNDO
        fundo.vrPosition.setX(AGScreenManager.iScreenWidth / 2);
        fundo.vrPosition.setY(AGScreenManager.iScreenHeight / 2);
        //POSICAO BOTAO JOGAR
        jogar.vrPosition.setX(AGScreenManager.iScreenWidth / 14 * 4);
        jogar.vrPosition.setY(AGScreenManager.iScreenHeight / 10 * 4);
        //POSICAO BOTAO AJUDA
        ajuda.vrPosition.setX(AGScreenManager.iScreenWidth / 4);
        ajuda.vrPosition.setY(AGScreenManager.iScreenHeight / 10 * 3);
        //POSICAO BOTAO JOGAR
        sobre.vrPosition.setX(AGScreenManager.iScreenWidth / 14 * 4);
        sobre.vrPosition.setY(AGScreenManager.iScreenHeight / 10 * 2);

    }

    @Override
    public void restart() {
        //CHAMANO NA VOLTA DE UMA INTERRUPÇÃO
    }

    @Override
    public void stop() {
        //CHAMADO QUANDO A INTERRUPÇÃO OCORRER
    }

    @Override
    public void loop() {
        //CHAMADO N VEZES POR SEGUNDO QUE IRA CONTROLAR A LOGICA DA CENA

        //FECHA APLICAÇÃO QUANDO CLICA NO BOTAO VOLTAR
        if (AGInputManager.vrTouchEvents.backButtonClicked()) {
            vrGameManager.vrActivity.finish();
        }
        if (AGInputManager.vrTouchEvents.screenClicked()) { //Quando receber o clique do usuário
            if (jogar.collide(AGInputManager.vrTouchEvents.getLastPosition())) {
                AGSoundManager.vrMusic.pause();
                AGSoundManager.vrSoundEffects.play(codigosom);
                this.vrGameManager.setCurrentScene(1); //a cena jogo é chamada
                return;
            }

            if (AGInputManager.vrTouchEvents.screenClicked()) { //Quando receber o clique do usuário
                if (ajuda.collide(AGInputManager.vrTouchEvents.getLastPosition())) {
                    this.vrGameManager.setCurrentScene(2); //a cena ajuda é chamada
                    return;
                }

                if (AGInputManager.vrTouchEvents.screenClicked()) { //Quando receber o clique do usuário
                    if (sobre.collide(AGInputManager.vrTouchEvents.getLastPosition())) {
                        this.vrGameManager.setCurrentScene(3); //a cena sobre é chamada
                        return;
                    }
                }
            }
        }
    }
}
