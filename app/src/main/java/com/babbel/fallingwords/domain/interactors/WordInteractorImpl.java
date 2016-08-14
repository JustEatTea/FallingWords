package com.babbel.fallingwords.domain.interactors;

import android.util.Log;

import com.babbel.fallingwords.FallingWordsApplication;
import com.babbel.fallingwords.domain.entities.Word;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.realm.Realm;

/**
 * Created by felipe on 8/12/16.
 */
public class WordInteractorImpl implements WordInteractor{

    private static final String TAG = "WordInteractorImpl";

    @Override
    public void populateData(final OnDataLoaded listener) {

        Realm realm = Realm.getDefaultInstance();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    InputStream is = FallingWordsApplication.getContext().getAssets().open("words.json");
                    realm.createOrUpdateAllFromJson(Word.class, is);
                    is.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                listener.finished();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.e(TAG,"Error populating data",error);
            }
        });

    }

    @Override
    public List<Word> getRamdomWords() {

        Realm realm = Realm.getDefaultInstance();
        List<Word> words = new ArrayList<>();
        words.addAll(realm.where(Word.class).findAll());


        Collections.shuffle(words);

        List<Word> newWords = new ArrayList<>();


        for (int i = 0; i < 5; i++) {
            newWords.add(words.get(i));
        }

        return newWords;
    }

}
