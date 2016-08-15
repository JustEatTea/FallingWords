package com.babbel.fallingwords.ui.main;

/**
 * Created by felipe on 8/11/16.
 */
public interface MainPresenter{


    void onWordOkBtnClicked(String currentWord);
    void onWordNokBtnClicked(String curentWord);
    void startGame();
    void populateData();
    void getNextWordOption();
    void setWordNotAnswered();
    void getNextWordToTranslate();

}
