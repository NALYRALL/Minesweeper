package minesweeper.ui;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CellButton extends JButton {
    private int row;
    private int col;

    public CellButton(int row,int col){
        this.row = row;
        this.col = col;
    }

    public boolean isRightClick(MouseEvent e) {
        return e.getButton() == MouseEvent.BUTTON3;
    }
}