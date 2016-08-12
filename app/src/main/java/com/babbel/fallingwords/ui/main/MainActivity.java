package com.babbel.fallingwords.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.babbel.fallingwords.R;

/**
 * Created by felipe on 8/10/16.
 */
public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener,Animation.AnimationListener {


    private TextView fallingWordTxtView;
    private TextView currentWord;
    private Button wordOkBtn;
    private Button workNotOkBtn;
    private Button startGameBtn;
    private RelativeLayout gameLayout;
    private Animation animationFalling;

    private MainPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fallingWordTxtView = (TextView) findViewById(R.id.main_falling_word);
        currentWord = (TextView) findViewById(R.id.main_current_word);
        workNotOkBtn = (Button) findViewById(R.id.main_word_nok);
        wordOkBtn = (Button) findViewById(R.id.main_word_ok);
        startGameBtn = (Button) findViewById(R.id.main_start_game);
        gameLayout = (RelativeLayout) findViewById(R.id.main_game_layout);

        animationFalling = AnimationUtils.loadAnimation(this, R.anim.falling);

        wordOkBtn.setOnClickListener(this);
        workNotOkBtn.setOnClickListener(this);
        presenter = new MainPresenterImpl(this);
        animationFalling.setAnimationListener(this);
    }


    @Override
    public void setWordToTranslate(String currentWord) {
        this.currentWord.setText(currentWord);
    }

    @Override
    public void startFallingAnimation() {
        fallingWordTxtView.startAnimation(animationFalling);
    }

    @Override
    public void hideStartBtnAndShowGame() {
        startGameBtn.setVisibility(View.GONE);
        gameLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void setFallingWord(String fallingWord) {
        this.fallingWordTxtView.setText(fallingWord);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.main_word_ok:
                presenter.onWordOkBtnClicked();
                break;
            case R.id.main_word_nok:
                presenter.onWordNokBtnClicked();
                break;

            case R.id.main_start_game:
                presenter.startGame();
                break;
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
