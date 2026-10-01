package minesweeper.ui;

import javax.swing.JButton;
import java.awt.event.MouseEvent;

public class CellButton extends JButton {

    private final int row;
    private final int col;

    public CellButton(int row, int col) {

        this.row = row;
        this.col = col;

        setFocusPainted(false);
    }

    public boolean isRightClick(MouseEvent e) {
        return e.getButton() == MouseEvent.BUTTON3;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}