package com.example.mateu.projetofinal;

        import com.example.mateu.projetofinal.AndGraph.AGGameManager;
        import com.example.mateu.projetofinal.AndGraph.AGInputManager;
        import com.example.mateu.projetofinal.AndGraph.AGScene;
        import com.example.mateu.projetofinal.AndGraph.AGScreenManager;
        import com.example.mateu.projetofinal.AndGraph.AGSprite;

        import java.util.Random;

public class CenaJogo extends AGScene {
    public CenaJogo(AGGameManager manager) {
        super(manager);
    }

    AGSprite fundo = null;
    AGSprite galinha = null;
    AGSprite carro = null;
    AGSprite setadireita = null;
    AGSprite setaesquerda = null;
    private AGSprite[] vetcarro = null;
    private AGSprite[] vetScore = null;
    private int score = 0;

    @Override
    public void init() {
        //CHAMADO TODA VEZ QUE UMA CENA É APRESENTADA

        fundo = createSprite(R.mipmap.rua1, 1, 1);
        fundo.setScreenPercent(101, 101);
        fundo.vrPosition.setX(AGScreenManager.iScreenWidth / 2);
        fundo.vrPosition.setY(AGScreenManager.iScreenHeight / 2);

        setadireita = createSprite(R.mipmap.setadireita, 1, 1);
        setadireita.setScreenPercent(30, 20);
        setadireita.vrPosition.setX(AGScreenManager.iScreenWidth / 5 * 4);
        setadireita.vrPosition.setY(AGScreenManager.iScreenHeight / 10 * 1);
        setadireita.fAlpha = 0.6f;

        setaesquerda = createSprite(R.mipmap.setaesquerda, 1, 1);
        setaesquerda.setScreenPercent(30, 20);
        setaesquerda.vrPosition.setX(AGScreenManager.iScreenWidth / 5 * 1);
        setaesquerda.vrPosition.setY(AGScreenManager.iScreenHeight / 10 * 1);
        setaesquerda.fAlpha = 0.6f;

        galinha = createSprite(R.mipmap.galinha1, 1, 1);
        galinha.setScreenPercent(7, 5);
        galinha.vrPosition.setX(AGScreenManager.iScreenWidth / 32 * 31);
        galinha.vrPosition.setY(AGScreenManager.iScreenHeight / 2);
        galinha.iMirror = AGSprite.HORIZONTAL;
        desenhaCarro();

        //Cria os Sprites do Score e configura suas animacoes
        vetScore = new AGSprite[3];
        for (int iIndex = vetScore.length - 1; iIndex >=0; iIndex--)
        {
            vetScore[iIndex] = createSprite(R.mipmap.fonte, 4,4);
            vetScore[iIndex].setScreenPercent(8,8);
            vetScore[iIndex].vrPosition.setXY(AGScreenManager.iScreenWidth - iIndex* vetScore[iIndex].getSpriteWidth() - 10, AGScreenManager.iScreenHeight - vetScore[iIndex].getSpriteHeight() / 2);
            vetScore[iIndex].bAutoRender = false;

            for(int jIndex=0; jIndex < 10; jIndex++)
            {
                vetScore[iIndex].addAnimation(1,false, jIndex);
            }
        }

    }

    @Override
    public void restart() {
        //CHAMADO NA VOLTA DE UMA INTERRUPÇÃO
    }

    @Override
    public void stop() {
        //CHAMADO QUANDO A INTERRUPÇÃO OCORRER
    }
    //Sobrescreve o metodo de rendering
    public void render()
    {
        super.render();

        for (AGSprite digit:vetScore)
        {
            digit.render();
        }
    }
    @Override
    public void loop() {
        atualizarCarro();
        verificaColisao();
        //QUANDO O USUARIO CLICA NO BOTAO VOLTAR, VOLTA PARA A TELA INICIAL
        if (AGInputManager.vrTouchEvents.backButtonClicked()) {
            vrGameManager.setCurrentScene(0);
        }

        if (AGInputManager.vrTouchEvents.screenClicked()) { //Quando receber o clique do usuário, galinha anda para a direita
            if (setadireita.collide(AGInputManager.vrTouchEvents.getLastPosition())) {
                galinha.vrPosition.setX(galinha.vrPosition.getX() + 50);
                galinha.iMirror = AGSprite.HORIZONTAL - 1;
            }

            if (AGInputManager.vrTouchEvents.screenClicked()) { //Quando receber o clique do usuário , galinha anda para esquerda
                if (setaesquerda.collide(AGInputManager.vrTouchEvents.getLastPosition())) {
                    galinha.vrPosition.setX(galinha.vrPosition.getX() - 50);
                    galinha.iMirror = AGSprite.HORIZONTAL;
                }
            }
        }
    }
    //METODO UTILIZADO PARA VERIFIRCAR AS COLISOES DA GALINHA COM OS CARROS OU COM AS PAREDES
    private void verificaColisao() {
        //VERIFIRCA AS COLISOES DA GALINHA COM OS CARROS
        for (int iIndex = 0; iIndex < vetcarro.length; iIndex++) {
            if (vetcarro[iIndex].bVisible != false) {
                if (vetcarro[iIndex].bVisible && vetcarro[iIndex].collide(galinha)) {
                    galinha.vrPosition.setX(AGScreenManager.iScreenWidth / 32 * 31);
                    score=0;
                    updateScore();
                }
            }
        }
        //VERIFIRCAR AS COLISOES DA GALINHA COM AS PAREDES
        if(galinha.collide(0,AGScreenManager.iScreenHeight / 2))
        {
            score++;
            updateScore();
            galinha.vrPosition.setX(AGScreenManager.iScreenWidth / 32 * 31);
        }
        if(galinha.collide(AGScreenManager.iScreenWidth / 1,AGScreenManager.iScreenHeight / 2))
        {
            galinha.vrPosition.setX(AGScreenManager.iScreenWidth / 32 * 31);
            galinha.iMirror = AGSprite.HORIZONTAL;
        }
    }
    //METODO UTILIZADO PARA FAZER O CARRO ANDAR
    private void atualizarCarro() {
        vetcarro[0].vrPosition.setY(vetcarro[0].vrPosition.getY() + -10);
        vetcarro[1].vrPosition.setY(vetcarro[1].vrPosition.getY() + -9);
        vetcarro[2].vrPosition.setY(vetcarro[2].vrPosition.getY() + -7);
        vetcarro[3].vrPosition.setY(vetcarro[3].vrPosition.getY() + -5);
        vetcarro[4].vrPosition.setY(vetcarro[4].vrPosition.getY() + 12);
        vetcarro[5].vrPosition.setY(vetcarro[5].vrPosition.getY() + 14);
        vetcarro[6].vrPosition.setY(vetcarro[6].vrPosition.getY() + 16);
        vetcarro[7].vrPosition.setY(vetcarro[7].vrPosition.getY() + 18);
        //SE O CARRO CHEGAR AO FINAL DA PISTA, INICIA NOVAMENTE
        for (int iIndex = 0; iIndex < 4; iIndex++) {
            if (vetcarro[iIndex].vrPosition.getY() < AGScreenManager.iScreenHeight / 1 * 0) {
                vetcarro[iIndex].bVisible = false;
                randomCarro();
                posicao(iIndex);
                vetcarro[iIndex] = carro;
                carro = null;
            }
        }
        //SE O CARRO CHEGAR AO FINAL DA PISTA, INICIA NOVAMENTE
        for (int iIndex = 4; iIndex < 8; iIndex++) {
            if (vetcarro[iIndex].vrPosition.getY() > AGScreenManager.iScreenHeight / 1 * 1) {
                vetcarro[iIndex].bVisible = false;
                randomCarro();
                posicao(iIndex);
                vetcarro[iIndex] = carro;
                carro = null;
            }
        }
    }
    //METODO UTILIZADO PARA SETAR A POSICAO DE CADA PISTA
    public void posicao(int pos) {
        if (pos == 0) {
            carro.setScreenPercent(9, 9);
            carro.vrPosition.setX(AGScreenManager.iScreenWidth / 32 * 6);
            carro.vrPosition.setY(AGScreenManager.iScreenHeight / 1);
            carro.iMirror = AGSprite.VERTICAL;
        }
        if (pos == 1) {
            carro.setScreenPercent(9, 9);
            carro.vrPosition.setX(AGScreenManager.iScreenWidth / 30 * 8);
            carro.vrPosition.setY(AGScreenManager.iScreenHeight / 1);
            carro.iMirror = AGSprite.VERTICAL;
        }
        if (pos == 2) {
            carro.setScreenPercent(9, 9);
            carro.vrPosition.setX(AGScreenManager.iScreenWidth / 30 * 11);
            carro.vrPosition.setY(AGScreenManager.iScreenHeight / 1);
            carro.iMirror = AGSprite.VERTICAL;

        }
        if (pos == 3) {
            carro.setScreenPercent(9, 9);
            carro.vrPosition.setX(AGScreenManager.iScreenWidth / 30 * 14);
            carro.vrPosition.setY(AGScreenManager.iScreenHeight / 1);
            carro.iMirror = AGSprite.VERTICAL;
        }
        if (pos == 4) {
            carro.setScreenPercent(9, 9);
            carro.vrPosition.setX(AGScreenManager.iScreenWidth / 32 * 19);
            carro.vrPosition.setY(AGScreenManager.iScreenHeight / 1 * 0);
        }
        if (pos == 5) {
            carro.setScreenPercent(9, 9);
            carro.vrPosition.setX(AGScreenManager.iScreenWidth / 32 * 22);
            carro.vrPosition.setY(AGScreenManager.iScreenHeight / 1 * 0);
        }
        if (pos == 6) {
            carro.setScreenPercent(9, 9);
            carro.vrPosition.setX(AGScreenManager.iScreenWidth / 32 * 25);
            carro.vrPosition.setY(AGScreenManager.iScreenHeight / 1 * 0);
        }
        if (pos == 7) {
            carro.setScreenPercent(9, 9);
            carro.vrPosition.setX(AGScreenManager.iScreenWidth / 32 * 28);
            carro.vrPosition.setY(AGScreenManager.iScreenHeight / 1 * 0);
        }
    }
    //METODO UTILIZADO PARA GERAR CARROS COM SPRITES RANDOMICOS
    public void randomCarro() {
        Random randomico = new Random();
        int numeroAleatorio = randomico.nextInt(6);
        if (numeroAleatorio == 0) {
            carro = createSprite(R.mipmap.carro1, 1, 1);
        }
        if (numeroAleatorio == 1) {
            carro = createSprite(R.mipmap.carro2, 1, 1);
        }
        if (numeroAleatorio == 2) {
            carro = createSprite(R.mipmap.carro3, 1, 1);
        }
        if (numeroAleatorio == 3) {
            carro = createSprite(R.mipmap.carro4, 1, 1);
        }
        if (numeroAleatorio == 4) {
            carro = createSprite(R.mipmap.carro5, 1, 1);
        }
        if (numeroAleatorio == 5) {
            carro = createSprite(R.mipmap.carro6, 1, 1);
        }
    }

    //METODO UTILIZADO PARA DESENHAR OS CARROS
    public void desenhaCarro() {

        vetcarro = new AGSprite[8];
        for (int iIndex = 0; iIndex < vetcarro.length; iIndex++) {
            randomCarro();
            posicao(iIndex);
            vetcarro[iIndex] = carro;
        }
    }
    //METODO UTILIZADO PARA ATUALIZAR O SCORE
    private void updateScore()
    {
        vetScore[0].setCurrentAnimation((int)   score % 10);
        vetScore[1].setCurrentAnimation(((int)  score % 100) / 10);
        vetScore[2].setCurrentAnimation(((int)  score % 1000) / 100);
    }
}