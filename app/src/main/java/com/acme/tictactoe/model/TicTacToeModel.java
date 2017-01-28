package com.acme.tictactoe.model;

public class TicTacToeModel {
    private Board board;

    public TicTacToeModel() {
        board = new Board();
    }

    public Player mark(int row, int col) {
        return board.mark(row, col);
    }

    public Player getWinner() {
        return board.getWinner();
    }

    public void restart() {
        board.restart();
    }
}
