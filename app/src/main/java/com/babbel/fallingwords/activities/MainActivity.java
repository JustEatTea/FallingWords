package com.babbel.fallingwords.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.babbel.fallingwords.R;

/**
 * Created by felipe on 8/10/16.
 */
public class MainActivity extends AppCompatActivity {


    private TextView mFallingWordTxtView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFallingWordTxtView = (TextView) findViewById(R.id.main_falling_word);

        Animation animationFalling = AnimationUtils.loadAnimation(this, R.anim.falling);
        mFallingWordTxtView.startAnimation(animationFalling);
    }
}
