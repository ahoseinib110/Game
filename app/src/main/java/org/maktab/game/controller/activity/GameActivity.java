package org.maktab.game.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.maktab.game.R;
import org.maktab.game.controller.fragment.FourInARowFragment;
import org.maktab.game.controller.fragment.TicTacToeFragment;

public class GameActivity extends AppCompatActivity {
    private Button mButtonTic;
    private Button mButtonFour;
    TicTacToeFragment mTicTacToeFragment = TicTacToeFragment.newInstance();
    FourInARowFragment mFourInARowFragment = FourInARowFragment.newInstance();
    FragmentManager mFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        findViews();
        setOnClickListeners();
        mFragmentManager =getSupportFragmentManager();
        Fragment fragment = mFragmentManager.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            startFragment(mTicTacToeFragment);
        }
    }

    private void findViews() {
        mButtonTic = findViewById(R.id.button_tic);
        mButtonFour = findViewById(R.id.button_four);
    }

    private void setOnClickListeners() {
        mButtonTic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(mTicTacToeFragment);
            }
        });
        mButtonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFragment(mFourInARowFragment);
            }
        });
    }

    private void startFragment(Fragment fragment){
        mFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .commit();
    }
}