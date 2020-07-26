package org.maktab.game.controller.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import org.maktab.game.R;
import org.maktab.game.model.fourinarow.ButtonColor;
import org.maktab.game.model.fourinarow.FourInARow;
import org.maktab.game.model.fourinarow.StatusColor;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TicTacToeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FourInARowFragment extends Fragment {

    public static final String TAG = "bashir";
    FourInARow mFourInARow;
    int mRowSize;
    Button[][] mButtons;
    View mFrameLayoutFour;

    public FourInARowFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TicTacToeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FourInARowFragment newInstance() {
        FourInARowFragment fragment = new FourInARowFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        mRowSize = 5;
        mButtons = new Button[mRowSize][mRowSize];
        mFourInARow = new FourInARow(5);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_four_in_a_row, container, false);
        findViews(view);
        setOnClickListeners();
        return view;
    }


    private void findViews(View view) {
        mButtons[0][0] = view.findViewById(R.id.button_f1);
        mButtons[0][1] = view.findViewById(R.id.button_f2);
        mButtons[0][2] = view.findViewById(R.id.button_f3);
        mButtons[0][3] = view.findViewById(R.id.button_f4);
        mButtons[0][4] = view.findViewById(R.id.button_f5);
        mButtons[1][0] = view.findViewById(R.id.button_f6);
        mButtons[1][1] = view.findViewById(R.id.button_f7);
        mButtons[1][2] = view.findViewById(R.id.button_f8);
        mButtons[1][3] = view.findViewById(R.id.button_f9);
        mButtons[1][4] = view.findViewById(R.id.button_f10);
        mButtons[2][0] = view.findViewById(R.id.button_f11);
        mButtons[2][1] = view.findViewById(R.id.button_f12);
        mButtons[2][2] = view.findViewById(R.id.button_f13);
        mButtons[2][3] = view.findViewById(R.id.button_f14);
        mButtons[2][4] = view.findViewById(R.id.button_f15);
        mButtons[3][0] = view.findViewById(R.id.button_f16);
        mButtons[3][1] = view.findViewById(R.id.button_f17);
        mButtons[3][2] = view.findViewById(R.id.button_f18);
        mButtons[3][3] = view.findViewById(R.id.button_f19);
        mButtons[3][4] = view.findViewById(R.id.button_f20);
        mButtons[4][0] = view.findViewById(R.id.button_f21);
        mButtons[4][1] = view.findViewById(R.id.button_f22);
        mButtons[4][2] = view.findViewById(R.id.button_f23);
        mButtons[4][3] = view.findViewById(R.id.button_f24);
        mButtons[4][4] = view.findViewById(R.id.button_f25);
        mFrameLayoutFour = view.findViewById(R.id.frame_layout_four);
    }

    public class myListener implements View.OnClickListener {
        private int mIndex1, mIndex2;

        public myListener(int index1, int index2) {
            mIndex1 = index1;
            mIndex2 = index2;
        }

        @Override
        public void onClick(View v) {
            ButtonColor turn = mFourInARow.getTurn();
            if (mFourInARow.isGameFinished()) {
                reset();
                return;
            }
            if (mFourInARow.checkIsAddable(mIndex1, mIndex2)) {
                mFourInARow.enterPosition(mIndex1, mIndex2);
                Button button = (Button) v;
                switch (turn) {
                    case RED:
                        button.setBackgroundColor(getResources().getColor(R.color.red));
                        break;
                    case BLUE:
                        button.setBackgroundColor(getResources().getColor(R.color.blue));
                        break;
                    default:
                        break;
                }
                StatusColor statusColor = mFourInARow.getStatusColor();
                if (statusColor != null) {
                    switch (statusColor) {
                        case WINS_RED:
                            startSnackbar("player Red Win");
                            break;
                        case WINS_BLUE:
                            startSnackbar("player Blue Win");
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
        for (int i = 0; i < mRowSize; i++) {
            for (int j = 0; j < mRowSize; j++) {
                mButtons[i][j].setOnClickListener(new myListener(i, j));
            }
        }
    }

    private void startSnackbar(String winner) {
        Snackbar.make(mFrameLayoutFour, winner, Snackbar.LENGTH_LONG)
                .setAction("Reset", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        reset();
                    }
                })
                .setActionTextColor(getResources().getColor(android.R.color.holo_blue_bright))
                .show();
    }

    @SuppressLint("ResourceAsColor")
    private void reset() {
        mFourInARow = new FourInARow(5);
        for (int i = 0; i < mRowSize; i++) {
            for (int j = 0; j < mRowSize; j++) {
                mButtons[i][j].setBackgroundColor(getResources().getColor(R.color.gray));
            }
        }
    }
}
