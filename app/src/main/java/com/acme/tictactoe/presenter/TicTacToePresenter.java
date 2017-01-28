package com.acme.tictactoe.presenter;

import com.acme.tictactoe.model.Player;
import com.acme.tictactoe.model.TicTacToeModel;
import com.acme.tictactoe.view.TicTacToeView;
import com.acme.tictactoe.view.TicTacToeView.CellClickedEvent;
import com.squareup.otto.Subscribe;

public class TicTacToePresenter {

    private TicTacToeModel model;
    private TicTacToeView view;

    public TicTacToePresenter(TicTacToeModel model, TicTacToeView view) {
        this.model = model;
        this.view = view;
    }

    public void onResetSelected() {
        view.clearWinnerDisplay();
        view.clearButtons();
        model.restart();
    }

    @Subscribe
    public void onCellClicked(CellClickedEvent event) {
        onButtonSelected(event.row(), event.col());
    }

    public void onButtonSelected(int row, int col) {
        Player playerThatMoved = model.mark(row, col);

        if (playerThatMoved != null) {
            view.setButtonText(row, col, playerThatMoved.toString());

            if (model.getWinner() != null) {
                view.showWinner(playerThatMoved.toString());
            }
        }
    }
}
