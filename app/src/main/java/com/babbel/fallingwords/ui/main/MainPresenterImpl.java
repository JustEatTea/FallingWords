package com.babbel.fallingwords.ui.main;

import com.babbel.fallingwords.domain.entities.Word;
import com.babbel.fallingwords.domain.interactors.WordInteractorImpl;
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
    private final int totalOfWordsPerGame = 1;
    private int translatedWords;
    private int rightAnswers;
    private int wrongAnswers;
    private int notAnswered;

    private WordInteractor wordInteractor;


    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        this.wordInteractor = new WordInteractorImpl();
    }

    @Override
    public void onWordOkBtnClicked(String currentWord) {

        if (currentWord.contentEquals(this.currentWordToTranslate.getText_spa())) {
            rightAnswers++;
            setRightAnswer();
            getNextWordToTranslate();
        } else {
            wrongAnswers++;
            setWrongAnswer();
            getNextWordOption();
        }
    }

    @Override
    public void onWordNokBtnClicked(String currentWord) {

        if (!currentWord.contentEquals(this.currentWordToTranslate.getText_spa())) {
            rightAnswers++;
            setRightAnswer();
        } else {
            wrongAnswers++;
            setWrongAnswer();
        }
        getNextWordOption();
    }

    @Override
    public void startGame() {

        translatedWords = 0;
        rightAnswers = 0;
        wrongAnswers = 0;
        notAnswered = 0;
        setWrongAnswer();
        setRightAnswer();
        setNotAnswered();
        mainView.enableWrongOkButtons(true);
        mainView.hideStartBtnAndShowGame();
        getNextWordToTranslate();
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


    private void getNextWordToTranslate() {

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

    private void setNotAnswered() {
        mainView.setNotAnswered(notAnswered);
    }

    private void setRightAnswer() {
        mainView.setRightAnswer(rightAnswers);
    }

    private void setWrongAnswer() {
        mainView.setWrongAnswer(wrongAnswers);
    }

    @Override
    public void finished() {
        mainView.endLoadingData();
    }
}
