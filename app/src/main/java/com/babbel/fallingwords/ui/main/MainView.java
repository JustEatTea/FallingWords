package com.babbel.fallingwords.ui.main;

/**
 * Created by felipe on 8/11/16.
 */
public interface MainView {

    void setWordToTranslate(String currentWord);
    void startFallingAnimation();
    void hideStartBtnAndShowGame();
    void setFallingWord(String fallingWord);

}