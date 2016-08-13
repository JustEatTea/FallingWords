package com.babbel.fallingwords.domain.entities;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by felipe on 8/11/16.
 */
public class Word extends RealmObject {

    @PrimaryKey
    private String text_eng;
    private String text_spa;

    @Ignore
    private boolean used;

    public String getText_eng() {
        return text_eng;
    }

    public void setText_eng(String text_eng) {
        this.text_eng = text_eng;
    }

    public String getText_spa() {
        return text_spa;
    }

    public void setText_spa(String text_spa) {
        this.text_spa = text_spa;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
