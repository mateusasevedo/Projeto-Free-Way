package com.example.mateu.projetofinal;
import com.example.mateu.projetofinal.AndGraph.AGGameManager;
import com.example.mateu.projetofinal.AndGraph.AGInputManager;
import com.example.mateu.projetofinal.AndGraph.AGScene;
import com.example.mateu.projetofinal.AndGraph.AGScreenManager;
import com.example.mateu.projetofinal.AndGraph.AGSprite;

public class CenaSobre extends AGScene
{
    public CenaSobre(AGGameManager manager)
    {
        super(manager);
    }
    AGSprite fundo = null;
    AGSprite sobre = null;
    @Override
    public void init() {
        //CHAMADO TODA VEZ QUE UMA CENA É APRESENTADA
        //CRIACAO, PORCENTAGEM DA OCUPACAO E POSICAO DO SPRITE FUNDO
        fundo = createSprite(R.mipmap.fundo2, 1, 1);
        fundo.setScreenPercent(100, 100);
        fundo.vrPosition.setX(AGScreenManager.iScreenWidth / 2);
        fundo.vrPosition.setY(AGScreenManager.iScreenHeight / 2);
        //CRIACAO, PORCENTAGEM DA OCUPACAO E POSICAO DO BALAO SOBRE
        sobre = createSprite(R.mipmap.sobre2, 1, 1);
        sobre.setScreenPercent(110, 80);
        sobre.vrPosition.setX(AGScreenManager.iScreenWidth /9 * 4);
        sobre.vrPosition.setY(AGScreenManager.iScreenHeight / 2);
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
        //QUANDO O USUARIO CLICA NO BOTAO VOLTAR, VOLTA PARA A TELA INICIAL
        if(AGInputManager.vrTouchEvents.backButtonClicked())
        {
            vrGameManager.setCurrentScene(0);
        }
    }

}
