package org.maktab.game.model.fourinarow;

import android.util.Log;

import java.io.Serializable;


public class FourInARow implements Serializable {
    private int mRowSize ;
    private ButtonColor[][] plate ;
    private ButtonColor turn = ButtonColor.RED;
    private StatusColor statusColor ;
    private int filled = 0;

    public ButtonColor[][] getPlate() {
        return plate;
    }

    public StatusColor getStatusColor() {
        return statusColor;
    }

    public FourInARow(int rowSize) {
        mRowSize = rowSize;
        plate= new ButtonColor[rowSize][rowSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < rowSize; j++) {
                plate[i][j] = ButtonColor.GRAY;
            }
        }
    }

    public ButtonColor getTurn() {
        return turn;
    }

    public boolean isGameFinished() {
        return statusColor != null;
    }


    public void switchTurn() {
        if (turn.equals(ButtonColor.RED)) {
            turn = ButtonColor.BLUE;
        } else {
            turn = ButtonColor.RED;
        }
    }

    public boolean checkIsAddable(int x, int y) {
        boolean condition1 = !isGameFinished();
        boolean condition2 = x <mRowSize && x >= 0 &&y < mRowSize && y >= 0;
        boolean condition3 = plate[x][y].equals(ButtonColor.GRAY);
        boolean condition4 = (x==0)  || ((x>0)&&(!plate[x-1][y].equals(ButtonColor.GRAY)));
        boolean result = condition1 && condition2 && condition3 && condition4;
        return result;
     }

    public void changePlate(int x, int y, ButtonColor ButtonColor) {
        filled++;
        plate[x][y] = ButtonColor;
    }

    public void printStatusColor(ButtonColor ButtonColor) {
        if (ButtonColor.equals(ButtonColor.RED)) {
            statusColor = StatusColor.WINS_RED;
            //System.out.println("X Wins!");
            Log.d("bashir", "Red Wins!");
        } else if (ButtonColor.equals(ButtonColor.BLUE)) {
            statusColor = StatusColor.WINS_BLUE;
            Log.d("bashir", "Blue Wins!");
            //System.out.println("O Wins!");
        } else {
            statusColor = StatusColor.DRAW;
            Log.d("bashir", "draw!");
            //System.out.println("draw!");
        }
    }

    public void checkStatusColor() {
        for (int i = 0; i <mRowSize ; i++) {
            for (int j = 0; j < 2; j++) {
                //horizontal
                if (!plate[i][j].equals(ButtonColor.GRAY)) {
                    boolean flag =true;
                    for (int k = j; k <j+3 ; k++) {
                        if (!plate[i][k].equals(plate[i][k+1])) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                            printStatusColor(plate[i][j]);
                            return;
                    }
                }
            }
        }

        for (int i = 0; i <mRowSize ; i++) {
            for (int j = 0; j < 2; j++) {
                //horizontal
                if (!plate[j][i].equals(ButtonColor.GRAY)) {
                    boolean flag =true;
                    for (int k = j; k <j+3 ; k++) {
                        if (!plate[k][i].equals(plate[k+1][i])) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        printStatusColor(plate[j][i]);
                        return;
                    }
                }
            }
        }

        for (int i = 0; i <2 ; i++) {
            for (int j = 0; j < 2; j++) {
                //horizontal
                if (!plate[i][j].equals(ButtonColor.GRAY)) {
                    boolean flag =true;
                    for (int k = 0; k <3 ; k++) {
                            if (!plate[i+k][j+k].equals(plate[i+k+1][j+k+1])) {
                                flag = false;
                                break;
                            }
                    }
                    if (flag) {
                        printStatusColor(plate[i][j]);
                        return;
                    }
                }
            }
        }

        for (int i = 0; i <2 ; i++) {
            for (int j = mRowSize-1; j >mRowSize-3 ; j--) {
                //horizontal
                if (!plate[i][j].equals(ButtonColor.GRAY)) {
                    boolean flag =true;
                    for (int k = 0; k <3 ; k++) {
                            if (!plate[i+k][j-k].equals(plate[i+k+1][j-k-1])) {
                                flag = false;
                                break;
                            }
                    }
                    if (flag) {
                        printStatusColor(plate[i][j]);
                        return;
                    }
                }
            }
        }

        if (filled == mRowSize*mRowSize) {
            printStatusColor(ButtonColor.GRAY);
        }
    }

    public void enterPosition(int x, int y) {
        if ( checkIsAddable(x, y)) {
                changePlate(x, y, turn);
                //drawBoard();
                checkStatusColor();
                switchTurn();
            }
    }
}
