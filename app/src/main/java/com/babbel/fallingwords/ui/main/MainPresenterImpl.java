package com.babbel.fallingwords.ui.main;

import com.babbel.fallingwords.domain.entities.Word;
import com.babbel.fallingwords.domain.interactors.WordInteractor;

import java.util.Collections;
import java.util.List;

/**
 * Created by felipe on 8/11/16.
 */
public class MainPresenterImpl implements MainPresenter, WordInteractor.OnDataLoaded{

    private MainView mainView;
    private List<Word> options;
    private Word currentWordToTranslate;
    private final int totalOfWordsPerGame = 10;
    private int translatedWords;
    private int rightAnswers;
    private int wrongAnswers;
    private int notAnswered;

    private WordInteractor wordInteractor;


    public MainPresenterImpl(MainView mainView, WordInteractor wordInteractor) {
        this.mainView = mainView;
        this.wordInteractor = wordInteractor;
    }

    @Override
    public void onWordOkBtnClicked(String currentWord) {

        if(currentWordToTranslate!=null) {
            if (currentWord.contentEquals(this.currentWordToTranslate.getText_spa())) {
                rightAnswers+=5;
                setRightAnswer();
                getNextWordToTranslate();
            } else {
                wrongAnswers++;
                setWrongAnswer();
                getNextWordOption();
            }
        }
    }

    @Override
    public void onWordNokBtnClicked(String currentWord) {

        if(currentWordToTranslate!=null) {
            if (!currentWord.contentEquals(this.currentWordToTranslate.getText_spa())) {
                rightAnswers++;
                setRightAnswer();
            } else {
                wrongAnswers++;
                setWrongAnswer();
            }
        }
    }

    @Override
    public void startGame() {

        mainView.enableWrongOkButtons(true);
        mainView.hideStartBtnAndShowGame();
        translatedWords = 0;
        rightAnswers = 0;
        wrongAnswers = 0;
        notAnswered = 0;
        setWrongAnswer();
        setRightAnswer();
        setNotAnswered();
    }


    @Override
    public void populateData() {
        mainView.startLoadingData();
        wordInteractor.populateData(this);
    }

    @Override
    public void getNextWordOption() {

        for (Word word : options) {
            if (!word.isUsed()) {
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
        setNotAnswered();
    }


    @Override
    public void getNextWordToTranslate() {

        if (translatedWords == totalOfWordsPerGame) {
            mainView.setGameEndedState();

        } else {
            translatedWords++;
            options = wordInteractor.getRamdomWords();
            currentWordToTranslate = options.get(0);
            Collections.shuffle(options);
            mainView.setWordToTranslate(currentWordToTranslate.getText_eng());
            getNextWordOption();
        }
    }

    @Override
    public void finished() {
        mainView.endLoadingData();
    }
    private void setNotAnswered() {
        mainView.setNotAnswered(notAnswered);
    }

    private void setRightAnswer() {
        mainView.setRightAnswer(rightAnswers);
    }

    private void setWrongAnswer() {
        mainView.setWrongAnswer(wrongAnswers);
    }

    public void setCurrentWordToTranslate(Word word){
        this.currentWordToTranslate = word;
    }


    public int getRightAnswers() {
        return rightAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }


    public int getNotAnswered() {
        return notAnswered;
    }


    public void setTranslatedWords(int translatedWords) {
        this.translatedWords = translatedWords;
    }

    public int getTotalOfWordsPerGame() {
        return totalOfWordsPerGame;
    }
}
