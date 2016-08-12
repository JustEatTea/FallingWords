package com.babbel.fallingwords.ui.main;

/**
 * Created by felipe on 8/11/16.
 */
public interface MainPresenter{

    void onWordOkBtnClicked();
    void onWordNokBtnClicked();
    void startGame();
    void updateScore();
    void showNextWord();
    void endGame();

}
