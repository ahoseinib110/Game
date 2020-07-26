package org.maktab.game.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import org.maktab.game.R;
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
    TicTacToe mTicTacToe;
    Button mButtonT1;
    Button mButtonT2;
    Button mButtonT3;
    Button mButtonT4;
    Button mButtonT5;
    Button mButtonT6;
    Button mButtonT7;
    Button mButtonT8;
    Button mButtonT9;
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
        if (getArguments() != null) {
        }
        mTicTacToe = new TicTacToe();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);
        findViews(view);
        setOnClickListeners();
        return view;
    }


    private void findViews(View view) {
        mButtonT1 = view.findViewById(R.id.button_t1);
        mButtonT2 = view.findViewById(R.id.button_t2);
        mButtonT3 = view.findViewById(R.id.button_t3);
        mButtonT4 = view.findViewById(R.id.button_t4);
        mButtonT5 = view.findViewById(R.id.button_t5);
        mButtonT6 = view.findViewById(R.id.button_t6);
        mButtonT7 = view.findViewById(R.id.button_t7);
        mButtonT8 = view.findViewById(R.id.button_t8);
        mButtonT9 = view.findViewById(R.id.button_t9);
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
            Sign turn = mTicTacToe.getTurn();
            if(mTicTacToe.isGameFinished()){
                reset();
                return;
            }
            if (mTicTacToe.checkIsAddable(x, y)) {
                mTicTacToe.enterPosition(x, y);
                Button button = (Button) v;
                switch (turn) {
                    case O:
                        button.setText("O");
                        break;
                    case X:
                        button.setText("X");
                        break;
                    default:
                        break;
                }
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
        mButtonT1.setOnClickListener(new myListener(0));
        mButtonT2.setOnClickListener(new myListener(1));
        mButtonT3.setOnClickListener(new myListener(2));
        mButtonT4.setOnClickListener(new myListener(3));
        mButtonT5.setOnClickListener(new myListener(4));
        mButtonT6.setOnClickListener(new myListener(5));
        mButtonT7.setOnClickListener(new myListener(6));
        mButtonT8.setOnClickListener(new myListener(7));
        mButtonT9.setOnClickListener(new myListener(8));
    }

    private void startSnackbar(String winner) {
        Snackbar.make(mFrametLayoutTic, winner , Snackbar.LENGTH_LONG)
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
        mButtonT1.setText("");
        mButtonT2.setText("");
        mButtonT3.setText("");
        mButtonT4.setText("");
        mButtonT5.setText("");
        mButtonT6.setText("");
        mButtonT7.setText("");
        mButtonT8.setText("");
        mButtonT9.setText("");
    }
}
