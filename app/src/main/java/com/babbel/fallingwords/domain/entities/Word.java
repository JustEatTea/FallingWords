package com.babbel.fallingwords.domain.entities;

import io.realm.RealmObject;

/**
 * Created by felipe on 8/11/16.
 */
public class Word extends RealmObject {

    private String original;
    private String translated;

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getTranslated() {
        return translated;
    }

    public void setTranslated(String translated) {
        this.translated = translated;
    }
}
