package com.babbel.fallingwords.ui.compoundviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by felipe on 8/12/16.
 */
public class ScoreCustomView extends LinearLayout{


    private TextView rightAnswer;
    private TextView wrongAnsert;
    private TextView notAnswered;

    public ScoreCustomView(Context context) {
        super(context);
    }

    public ScoreCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScoreCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



}
