package com.babbel.fallingwords.ui.main;

/**
 * Created by felipe on 8/11/16.
 */
public class MainPresenterImpl implements MainPresenter{

    private MainView mainView;


    public MainPresenterImpl(MainView mainView){
        this.mainView = mainView;
    }

    @Override
    public void onWordOkBtnClicked() {
        //verificar se esta correto
        //atualiza placar
        //se correto, troca palavra

    }

    @Override
    public void onWordNokBtnClicked() {

    }

    @Override
    public void startGame() {

    }

    @Override
    public void updateScore() {

    }

    @Override
    public void showNextWord() {

    }

    @Override
    public void endGame() {

    }

    public MainView getMainView() {
        return mainView;
    }
}
