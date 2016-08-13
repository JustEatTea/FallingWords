package com.babbel.fallingwords.domain.interactors;

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
public class WordInteractor {

    public void populateData(){

        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    InputStream is = FallingWordsApplication.getContext().getAssets().open("words.json");
                    realm.createOrUpdateAllFromJson(Word.class, is);
                    is.close();
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });


    }

    public List<Word> getRamdomWords(){

        Realm realm = Realm.getDefaultInstance();
        List<Word> words  = new ArrayList<>();
        words.addAll(realm.where(Word.class).findAll());


        Collections.shuffle(words);

        List<Word> newWords = new ArrayList<>();


        for(int i=0;i<5;i++){
            newWords.add(words.get(i));
        }

        return newWords;
    }

}
