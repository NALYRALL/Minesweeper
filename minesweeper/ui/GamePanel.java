package minesweeper.ui;

import minesweeper.Game;
import minesweeper.Cell;
import minesweeper.GameState;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;   

public class GamePanel extends JPanel{  
    private Game game;
    private CellButton[][] cells = new CellButton[10][10];
    private JLabel mineLabel;

    public GamePanel(){
        game = new Game();
        setLayout(new BorderLayout());
        
        mineLabel = new JLabel("Mines: 10");

        JButton restartButton = new JButton("Restart");

        restartButton.addActionListener(e -> {
            resetBoard();
            // we'll refresh the board here
        });

            // Top panel
        JPanel topPanel = new JPanel();

        topPanel.add(mineLabel);
        topPanel.add(restartButton);

        add(topPanel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel(new GridLayout(10, 10));
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                cells[row][col] = new CellButton(row, col);

                int r = row;
                int c = col;
                cells[row][col].addActionListener(e -> {
                    game.reveal(r, c);
                    updateBoard();
                });
                cells[row][col].addMouseListener(new MouseAdapter() {

                    @Override
                    public void mousePressed(MouseEvent e) {

                        if (cells[r][c].isRightClick(e)) {
                            game.toggleFlag(r, c);
                            updateBoard();
                        }
                    }
                });
                boardPanel.add(cells[row][col]);            
            }
        }
        add(boardPanel, BorderLayout.CENTER);
    }

    private void updateBoard() {    
        mineLabel.setText("Mines: " + game.getRemainingMines());
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {

                Cell cell = game.getCell(row, col);

                if (cell.isFlagged()) {

                    cells[row][col].setText("🚩");

                } else if (cell.isRevealed()) {

                    if (cell.isMine()) {
                        cells[row][col].setText("💣");

                    } else if (cell.getAdjacentMines() > 0) {
                        cells[row][col].setText(
                            String.valueOf(cell.getAdjacentMines())
                        );

                    } else {
                        cells[row][col].setText("");
                    }

                } else if (game.getGameState() == GameState.LOST
                        && cell.isMine()) {

                    cells[row][col].setText("💣");

                } else {
                    cells[row][col].setText("");
                }
            }
        }
        if(game.getGameState() != GameState.PLAYING){
            disableBoard();
            if (game.getGameState() == GameState.WON) {
                System.out.println("YOU WON!");
            }
            else if (game.getGameState() == GameState.LOST) {
                System.out.println("YOU LOST!");
            }
        }
    }

    public boolean isRightClick(MouseEvent e) {
        return e.getButton() == MouseEvent.BUTTON3;
    }

    private void disableBoard() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                cells[row][col].setEnabled(false);
            }
        }
    }

    private void resetBoard() {
        game = new Game();
        
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                cells[row][col].setText("");
                cells[row][col].setEnabled(true);
            }
        }
        mineLabel.setText("Mines: " + game.getRemainingMines());
    }
}