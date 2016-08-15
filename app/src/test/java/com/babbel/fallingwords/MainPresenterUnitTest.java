package com.babbel.fallingwords;

import com.babbel.fallingwords.domain.entities.Word;
import com.babbel.fallingwords.domain.interactors.WordInteractor;
import com.babbel.fallingwords.ui.main.MainPresenterImpl;
import com.babbel.fallingwords.ui.main.MainView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterUnitTest {

    @Mock
    MainView view;

    @Mock
    WordInteractor interactor;

    private MainPresenterImpl presenter;
    private List<Word> list;
    private Word word;

    @Before
    public void setUp() throws Exception {

        presenter = new MainPresenterImpl(view,interactor);
        list = new ArrayList<>();
        word = new Word();

        word.setText_eng("test");
        word.setText_spa("teste");
        list.add(word);
    }

    @Test
    public void checkNotAnswered(){

        presenter.setWordNotAnswered();
        verify(view,times(1)).setNotAnswered(1);
        assertEquals("Not answered score isn't ok",1,presenter.getNotAnswered());
    }

    @Test
    public void checkIfGameStarted() {

        presenter.startGame();
        verify(view,times(1)).setWrongAnswer(0);
        verify(view,times(1)).setRightAnswer(0);
        verify(view,times(1)).setNotAnswered(0);
        verify(view,times(1)).enableWrongOkButtons(true);
        verify(view,times(1)).hideStartBtnAndShowGame();
    }

    @Test
    public void checkOnNotOkBtnClick(){

        presenter.onWordNokBtnClicked("");
        verify(view,times(0)).setWrongAnswer(1);
        verify(view,times(0)).setRightAnswer(1);

        presenter.setCurrentWordToTranslate(word);
        presenter.onWordNokBtnClicked("teste");
        verify(view,times(1)).setWrongAnswer(1);
        assertEquals("Wrong score isn't ok",1,presenter.getWrongAnswers());

        presenter.onWordNokBtnClicked("123");
        verify(view,times(1)).setRightAnswer(1);
        assertEquals("Right score isn't ok",1,presenter.getRightAnswers());
    }

    @Test
    public void checkOkBtnClick(){

        when(interactor.getRamdomWords()).thenReturn(list);
        presenter.onWordOkBtnClicked("");
        verify(view,times(0)).setWrongAnswer(1);
        verify(view,times(0)).setRightAnswer(1);

        presenter.setCurrentWordToTranslate(word);
        presenter.onWordOkBtnClicked("teste");
        verify(view,times(1)).setRightAnswer(5);
        assertEquals("Right score isn't ok",5,presenter.getRightAnswers());

        presenter.onWordOkBtnClicked("123");
        verify(view,times(1)).setWrongAnswer(1);
        assertEquals("Wrong score isn't ok",1,presenter.getWrongAnswers());
    }

    @Test
    public void checkIfShowLoadingData(){
        presenter.populateData();
        verify(view,times(1)).startLoadingData();
    }

    @Test
    public void checkIfStartFallingAnimationAndSetFallingWord(){

        when(interactor.getRamdomWords()).thenReturn(list);
        presenter.getNextWordToTranslate();
        verify(view,times(1)).startFallingAnimation();
        verify(view,times(1)).setFallingWord(word.getText_spa());
    }

    @Test
    public void checkIfGameEnds(){

        presenter.setTranslatedWords(presenter.getTotalOfWordsPerGame());
        presenter.getNextWordToTranslate();
        verify(view,times(1)).setGameEndedState();
    }


}