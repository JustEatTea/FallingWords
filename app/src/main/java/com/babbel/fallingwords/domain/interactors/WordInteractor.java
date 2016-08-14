package com.babbel.fallingwords.domain.interactors;

import com.babbel.fallingwords.domain.entities.Word;

import java.util.List;

/**
 * Created by felipe on 8/14/16.
 */
public interface WordInteractor {

    void populateData(OnDataLoaded listener);
    List<Word> getRamdomWords();

    interface OnDataLoaded {
        void finished();
    }
}
