package com.babbel.fallingwords.ui.compoundviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.babbel.fallingwords.R;

/**
 * Created by felipe on 8/12/16.
 */
public class ScoreCustomView extends LinearLayout{


    private TextView rightAnswer;
    private TextView wrongAnswer;
    private TextView notAnswered;

    public ScoreCustomView(Context context) {
        super(context);
        initializeView();
    }

    public ScoreCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView();
    }

    public ScoreCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeView();

    }

    private void initializeView() {
        inflate(getContext(), R.layout.compound_score, this);
        rightAnswer = (TextView) findViewById(R.id.score_right);
        wrongAnswer = (TextView) findViewById(R.id.score_wrong);
        notAnswered = (TextView) findViewById(R.id.score_not_answered);
    }


    public void setRightAnswer(String rightAnswer){
        this.rightAnswer.setText(rightAnswer);
    }

    public void setWrongAnswer(String wrongAnswer){
        this.wrongAnswer.setText(wrongAnswer);
    }

    public void setNotAnswered(String notAnswered){
        this.notAnswered.setText(notAnswered);
    }

}
