package org.maktab.game.model.tictactoe;

import java.util.Scanner;

public class TestGame {
    public static void printTurn(Sign sign) {
        if (sign.equals(Sign.X)) {
            System.out.print("X turn:");
        } else {
            System.out.print("O turn:");
        }
    }

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        Scanner scanner = new Scanner(System.in);
        ticTacToe.drawBoard();
        while (!ticTacToe.isGameFinished()) {
            printTurn(ticTacToe.getTurn());
            String strx =scanner.next();
            String stry =scanner.next();
            try {
                int x =Integer.parseInt(strx);
                int y =Integer.parseInt(stry);
                ticTacToe.enterPosition(x, y);
            } catch (NumberFormatException e) {
                System.out.println("Input Mismatch Exception");
            }
        }
    }
}
