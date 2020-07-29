package org.maktab.game.model.tictactoe;

import android.util.Log;

import org.maktab.game.model.tictactoe.Sign;
import org.maktab.game.model.tictactoe.Status;

import java.io.Serializable;

public class TicTacToe implements Serializable {
    private Sign[][] plate = new Sign[3][3];
    private Sign turn = Sign.X;
    private Status status ;
    private int filled = 0;

    public Sign[][] getPlate() {
        return plate;
    }

    public void setPlate(Sign[][] plate) {
        this.plate = plate;
    }

    public Status getStatus() {
        return status;
    }

    public TicTacToe() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                plate[i][j] = Sign.E;
            }
        }
    }

    public Sign getTurn() {
        return turn;
    }

    public boolean isGameFinished() {
        return status != null;
    }

    public void drawBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String printValue;
                if (plate[i][j].equals(Sign.X)) {
                    printValue = "X";
                } else if (plate[i][j].equals(Sign.O)) {
                    printValue = "O";
                } else {
                    printValue = "-";
                }
                System.out.print(printValue + " ");
            }
            System.out.println();
        }
    }

    public void switchTurn() {
        if (turn.equals(Sign.X)) {
            turn = Sign.O;
        } else {
            turn = Sign.X;
        }
    }

    public boolean checkIsAddable(int x, int y) {
        Log.d("bashir", "salam1");
        if (!isGameFinished()) {
            Log.d("bashir", "salam2");
            if (x < 3 && x >= 0 &&y < 3 && y >= 0) {
                if (plate[x][y].equals(Sign.E)) {
                    return true;
                } else {
                    Log.d("bashir", "this position is not empty !");
                    return false;
                    //System.out.println("this position is not empty !");
                }
            } else {
                Log.d("bashir", "out of range input!");
                return false;
                //System.out.println("out of range input!");
            }
        }else {
            return false;
        }
    }

    public void changePlate(int x, int y, Sign sign) {
        filled++;
        plate[x][y] = sign;
    }

    public void printStatus(Sign sign) {
        if (sign.equals(Sign.X)) {
            status = Status.WINS_X;
            //System.out.println("X Wins!");
            Log.d("bashir", "X Wins!");
        } else if (sign.equals(Sign.O)) {
            status = Status.WINS_O;
            Log.d("bashir", "O Wins!");
            //System.out.println("O Wins!");
        } else {
            status = Status.DRAW;
            Log.d("bashir", "draw!");
            //System.out.println("draw!");
        }
    }

    public void checkStatus() {
        for (int i = 0; i < 3; i++) {
            //horizontal
            if (!plate[i][0].equals(Sign.E)) {
                if (plate[i][0].equals(plate[i][1])) {
                    if (plate[i][0].equals(plate[i][2])) {
                        printStatus(plate[i][0]);
                        return;
                    }
                }
            }

            //vertical
            if (!plate[0][i].equals(Sign.E)) {
                if (plate[0][i].equals(plate[1][i])) {
                    if (plate[0][i].equals(plate[2][i])) {
                        printStatus(plate[0][i]);
                        return;
                    }
                }
            }
        }

        if (!plate[0][0].equals(Sign.E)) {
            if (plate[0][0].equals(plate[1][1])) {
                if (plate[1][1].equals(plate[2][2])) {
                    printStatus(plate[0][0]);
                    return;
                }
            }
        }

        if (!plate[0][2].equals(Sign.E)) {
            if (plate[0][2].equals(plate[1][1])) {
                if (plate[1][1].equals(plate[2][0])) {
                    printStatus(plate[0][2]);
                    return;
                }
            }
        }

        if (filled == 9) {
            printStatus(Sign.E);
        }
    }

    public void enterPosition(int x, int y) {
        if ( checkIsAddable(x, y)) {
                changePlate(x, y, turn);
                //drawBoard();
                checkStatus();
                switchTurn();
            }
    }
}
