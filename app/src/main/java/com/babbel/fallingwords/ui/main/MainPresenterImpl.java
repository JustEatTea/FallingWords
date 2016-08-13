package com.babbel.fallingwords.ui.main;

import com.babbel.fallingwords.domain.entities.Word;
import com.babbel.fallingwords.domain.interactors.WordInteractor;

import java.util.Collections;
import java.util.List;

/**
 * Created by felipe on 8/11/16.
 */
public class MainPresenterImpl implements MainPresenter{

    private MainView mainView;
    private List<Word> options;
    private Word currentWordToTranslate;
    private final int totalOfWordsPerGame = 10;
    private int translatedWords;
    private int rightAnswers;
    private int wrongAnswers;
    private int notAnswered;

    private WordInteractor wordInteractor;


    public MainPresenterImpl(MainView mainView){
        this.mainView = mainView;
        this.wordInteractor = new WordInteractor();
    }

    @Override
    public void onWordOkBtnClicked(String currentWord) {

        if(currentWord.contentEquals(this.currentWordToTranslate.getText_spa())){
            rightAnswers++;
            mainView.setRightAnswer("");
            getNextWordToTranslate();
        }
        else{
            wrongAnswers++;
            mainView.setWrongAnswer("");
            getNextWordOption();
        }
    }

    @Override
    public void onWordNokBtnClicked(String currentWord) {

        if(!currentWord.contentEquals(this.currentWordToTranslate.getText_spa())){
            rightAnswers++;
            mainView.setRightAnswer("");
        }
        else{
            wrongAnswers++;
            mainView.setWrongAnswer("");
        }
        getNextWordOption();
    }

    @Override
    public void startGame() {
        translatedWords = 0;
        mainView.hideStartBtnAndShowGame();
        getNextWordToTranslate();
    }

    @Override
    public void populateData() {
        wordInteractor.populateData();
    }

    @Override
    public void getNextWordOption(){

        for(Word word : options){
            if(!word.isUsed()){
                word.setUsed(true);
                mainView.startFallingAnimation();
                mainView.setFallingWord(word.getText_spa());
                return;
            }
        }

        getNextWordToTranslate();
    }

    @Override
    public void setWordNotAnswered() {
        notAnswered++;
    }


    private  void getNextWordToTranslate(){

        if(translatedWords == totalOfWordsPerGame){
            mainView.stopFallingAnimation();
        }
        else {
            translatedWords++;
            options = wordInteractor.getRamdomWords();
            currentWordToTranslate = options.get(0);
            Collections.shuffle(options);
            mainView.setWordToTranslate(currentWordToTranslate.getText_eng());
            getNextWordOption();
        }
    }

}
