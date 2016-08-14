package com.babbel.fallingwords.ui.main;

/**
 * Created by felipe on 8/11/16.
 */
public interface MainView {

    void setWordToTranslate(String currentWord);
    void startFallingAnimation();
    void hideStartBtnAndShowGame();
    void setFallingWord(String fallingWord);
    void setRightAnswer(int score);
    void setWrongAnswer(int score);
    void setGameEndedState();
    void setNotAnswered(int score);
    void enableWrongOkButtons(boolean enable);
    void startLoadingData();
    void endLoadingData();

}
