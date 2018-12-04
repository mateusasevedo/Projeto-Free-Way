package com.example.mateu.projetofinal;
import com.example.mateu.projetofinal.AndGraph.AGGameManager;
import com.example.mateu.projetofinal.AndGraph.AGInputManager;
import com.example.mateu.projetofinal.AndGraph.AGScene;
import com.example.mateu.projetofinal.AndGraph.AGScreenManager;
import com.example.mateu.projetofinal.AndGraph.AGSprite;

public class CenaAjuda extends AGScene
{
    public CenaAjuda(AGGameManager manager)
    {
        super(manager);
    }
    AGSprite fundo=null;
    AGSprite ajuda = null;
    @Override
    public void init() {
        //CHAMADO TODA VEZ QUE UMA CENA É APRESENTADA

        fundo = createSprite(R.mipmap.fundo2, 1, 1);
        fundo.setScreenPercent(100, 100);
        fundo.vrPosition.setX(AGScreenManager.iScreenWidth / 2);
        fundo.vrPosition.setY(AGScreenManager.iScreenHeight / 2);

        ajuda = createSprite(R.mipmap.ajuda1, 1, 1);
        ajuda.setScreenPercent(105, 105);
        ajuda.vrPosition.setX(AGScreenManager.iScreenWidth / 2);
        ajuda.vrPosition.setY(AGScreenManager.iScreenHeight /5 * 2);
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
    public void loop()
    {
        //CHAMADO N VEZES POR SEGUNDO QUE IRA CONTROLAR A LOGICA DA CENA
        //QUANDO O USUARIO CLICA NO BOTAO VOLTAR, VOLTA PARA A TELA INICIAL
        if(AGInputManager.vrTouchEvents.backButtonClicked())
        {
            vrGameManager.setCurrentScene(0);
        }
    }

}
