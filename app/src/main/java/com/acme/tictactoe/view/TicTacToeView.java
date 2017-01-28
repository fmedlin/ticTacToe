package com.acme.tictactoe.view;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.acme.tictactoe.R;
import com.google.auto.value.AutoValue;
import com.squareup.otto.Bus;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;

public class TicTacToeView {
    @BindView(R.id.buttonGrid) ViewGroup buttonGrid;
    @BindView(R.id.winnerPlayerViewGroup) View winnerPlayerViewGroup;
    @BindView(R.id.winnerPlayerLabel) TextView winnerPlayerLabel;

    private WeakReference<Activity> activityRef;
    private Bus bus;

    public TicTacToeView(Activity activity, Bus bus) {
        activityRef = new WeakReference<>(activity);
        this.bus = bus;
        ButterKnife.bind(this, activity);
    }

    public void showWinner(String winningPlayerDisplayLabel) {
        winnerPlayerLabel.setText(winningPlayerDisplayLabel);
        winnerPlayerViewGroup.setVisibility(View.VISIBLE);
    }

    public void clearWinnerDisplay() {
        winnerPlayerViewGroup.setVisibility(View.GONE);
        winnerPlayerLabel.setText("");
    }

    public void clearButtons() {
        for (int i = 0; i < buttonGrid.getChildCount(); i++) {
            ((Button) buttonGrid.getChildAt(i)).setText("");
        }
    }

    public void setButtonText(int row, int col, String text) {
        Button btn = (Button) buttonGrid.findViewWithTag("" + row + col);
        if(btn != null) {
            btn.setText(text);
        }
    }

    @OnClick({ R.id.button00, R.id.button01, R.id.button02,
                R.id.button10, R.id.button11, R.id.button12,
                R.id.button20, R.id.button21, R.id.button22 })
    public void onCellClicked(View v) {
        Button button = (Button) v;
        String tag = button.getTag().toString();
        int row = Integer.valueOf(tag.substring(0,1));
        int col = Integer.valueOf(tag.substring(1,2));
        Log.i(TAG, "Click Row: [" + row + "," + col + "]");
        bus.post(CellClickedEvent.create(row, col));
    }

    // Events published

    @AutoValue
    abstract public static class CellClickedEvent {
        public abstract int row();
        public abstract int col();

        static CellClickedEvent create(int row, int col) {
            return new AutoValue_TicTacToeView_CellClickedEvent(row, col);
        }
    }
}
