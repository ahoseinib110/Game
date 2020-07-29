package org.maktab.game.controller.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import org.maktab.game.R;
import org.maktab.game.model.fourinarow.ButtonColor;
import org.maktab.game.model.tictactoe.Sign;
import org.maktab.game.model.tictactoe.Status;
import org.maktab.game.model.tictactoe.TicTacToe;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TicTacToeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TicTacToeFragment extends Fragment {

    public static final String TAG = "bashir";
    private static final String ARG_TIC_TAC_TOE = "tacTacToe";
    TicTacToe mTicTacToe;
    Button[][] mButtons;
    View mFrametLayoutTic;

    public TicTacToeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TicTacToeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TicTacToeFragment newInstance() {
        TicTacToeFragment fragment = new TicTacToeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            mTicTacToe = new TicTacToe();
        }
        mButtons = new Button[3][3];
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);
        findViews(view);
        setOnClickListeners();
        if (savedInstanceState != null) {
            mTicTacToe = (TicTacToe) savedInstanceState.getSerializable(ARG_TIC_TAC_TOE);
            Sign[][] plate = mTicTacToe.getPlate();
            for (int i = 0; i <3 ; i++) {
                for (int j = 0; j <3 ; j++) {
                    fillButtons(mButtons[i][j],plate[i][j]);
                }
            }
        }
        return view;
    }

    private void fillButtons(Button button,Sign turn) {
        switch (turn) {
            case X:
                button.setText("X");
                break;
            case O:
                button.setText("O");
                break;
            default:
                break;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ARG_TIC_TAC_TOE, mTicTacToe);
    }

    private void findViews(View view) {
        mButtons[0][0] = view.findViewById(R.id.button_t1);
        mButtons[0][1] = view.findViewById(R.id.button_t2);
        mButtons[0][2] = view.findViewById(R.id.button_t3);
        mButtons[1][0] = view.findViewById(R.id.button_t4);
        mButtons[1][1] = view.findViewById(R.id.button_t5);
        mButtons[1][2] = view.findViewById(R.id.button_t6);
        mButtons[2][0] = view.findViewById(R.id.button_t7);
        mButtons[2][1] = view.findViewById(R.id.button_t8);
        mButtons[2][2] = view.findViewById(R.id.button_t9);
        mFrametLayoutTic = view.findViewById(R.id.frame_layout_tic);
    }

    public class myListener implements View.OnClickListener {
        private int mIndex;

        public myListener(int index) {
            mIndex = index;
        }

        @Override
        public void onClick(View v) {
            int x = mIndex / 3;
            int y = mIndex % 3;
            Log.d("bashir",""+x);
            Log.d("bashir",""+y);
            Sign turn = mTicTacToe.getTurn();
            if (mTicTacToe.isGameFinished()) {
                reset();
                return;
            }
            if (mTicTacToe.checkIsAddable(x, y)) {
                mTicTacToe.enterPosition(x, y);
                Button button = (Button) v;
                fillButtons(button,turn);
                Status status = mTicTacToe.getStatus();
                if (status != null) {
                    switch (status) {
                        case WINS_X:
                            startSnackbar("player X Win");
                            break;
                        case WINS_O:
                            //Toast.makeText(getActivity(),"player2 win",Toast.LENGTH_LONG).show();
                            startSnackbar("player O Win");
                            break;
                        case DRAW:
                            startSnackbar("DRAW");
                            break;
                    }
                }
            }
        }
    }

    private void setOnClickListeners() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int index = i * 3 + j;
                mButtons[i][j].setOnClickListener(new myListener(index));
            }
        }
    }

    private void startSnackbar(String winner) {
        Snackbar.make(mFrametLayoutTic, winner, Snackbar.LENGTH_LONG)
                .setAction("Reset", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        reset();
                    }
                })
                .setActionTextColor(getResources().getColor(android.R.color.holo_blue_bright))
                .show();
    }

    private void reset() {
        mTicTacToe = new TicTacToe();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mButtons[i][j].setText("");
            }
        }
    }
}
