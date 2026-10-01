package minesweeper.ui;

import minesweeper.Cell;
import minesweeper.Game;
import minesweeper.GameState;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {

    private Game game;

    private final CellButton[][] cells =
        new CellButton[10][10];

    private JLabel mineLabel;
    private JLabel timerLabel;

    private int seconds;

    private Timer timer;

    public GamePanel() {

        game = new Game();

        setLayout(new BorderLayout());

        // Restart
        JButton restartButton =
            new JButton("Restart");

        restartButton.addActionListener(e -> {
            resetBoard();
        });

        // Mine counter
        mineLabel =
            new JLabel("Mines: 10");

        // Timer
        seconds = 0;

        timerLabel =
            new JLabel("Time: 0");

        timer = new Timer(1000, e -> {

            seconds++;

            timerLabel.setText(
                "Time: " + seconds
            );
        });

        timer.start();

        // Top panel
        JPanel topPanel = new JPanel();

        topPanel.add(mineLabel);
        topPanel.add(timerLabel);
        topPanel.add(restartButton);

        add(
            topPanel,
            BorderLayout.NORTH
        );

        // Board
        JPanel boardPanel =
            new JPanel(new GridLayout(10, 10));

        for (int row = 0; row < 10; row++) {

            for (int col = 0; col < 10; col++) {

                cells[row][col] =
                    new CellButton(row, col);

                int r = row;
                int c = col;

                cells[row][col]
                    .addMouseListener(
                        new MouseAdapter() {

                            @Override
                            public void mousePressed(
                                MouseEvent e) {

                                // Left click
                                if (e.getButton()
                                    == MouseEvent.BUTTON1) {

                                    Cell cell =
                                        game.getCell(r, c);

                                    if (cell.isRevealed()) {
                                        game.chord(r, c);
                                    } else {
                                        game.reveal(r, c);
                                    }

                                    updateBoard();
                                }

                                // Right click
                                else if (
                                    e.getButton()
                                    == MouseEvent.BUTTON3) {

                                    game.toggleFlag(r, c);

                                    updateBoard();
                                }
                            }
                        }
                    );

                boardPanel.add(
                    cells[row][col]
                );
            }
        }

        add(
            boardPanel,
            BorderLayout.CENTER
        );
    }

    private void updateBoard() {

        mineLabel.setText(
            "Mines: " +
            game.getRemainingMines()
        );

        for (int row = 0; row < 10; row++) {

            for (int col = 0; col < 10; col++) {

                Cell cell =
                    game.getCell(row, col);

                updateCellAppearance(
                    row,
                    col,
                    cell
                );
            }
        }

        if (game.getGameState()
            != GameState.PLAYING) {

            timer.stop();

            disableBoard();

            if (game.getGameState()
                == GameState.WON) {

                JOptionPane.showMessageDialog(
                    this,
                    "You Won!\nTime: "
                    + seconds
                    + " seconds"
                );

            } else if (
                game.getGameState()
                == GameState.LOST) {

                JOptionPane.showMessageDialog(
                    this,
                    "Game Over!\nYou hit a mine."
                );
            }
        }
    }

    private void updateCellAppearance(
        int row,
        int col,
        Cell cell) {

        // Flag
        if (cell.isFlagged()) {

            cells[row][col]
                .setText("🚩");

            return;
        }

        // Show mines after losing
        if (game.getGameState()
            == GameState.LOST
            && cell.isMine()) {

            cells[row][col]
                .setText("💣");

            return;
        }

        // Hidden
        if (!cell.isRevealed()) {

            cells[row][col]
                .setText("");

            return;
        }

        cells[row][col].setFont(
            new Font(
                "Arial",
                Font.BOLD,
                18
            )
        );

        // Mine
        if (cell.isMine()) {

            cells[row][col]
                .setText("💣");

            return;
        }

        int mines =
            cell.getAdjacentMines();

        // Empty
        if (mines == 0) {

            cells[row][col]
                .setText("");

        } else {

            cells[row][col]
                .setText(
                    String.valueOf(mines)
                );

            setNumberColor(
                row,
                col,
                mines
            );
        }
    }

    private void setNumberColor(
        int row,
        int col,
        int mines) {

        switch (mines) {

            case 1:
                cells[row][col]
                    .setForeground(Color.BLUE);
                break;

            case 2:
                cells[row][col]
                    .setForeground(Color.GREEN);
                break;

            case 3:
                cells[row][col]
                    .setForeground(Color.RED);
                break;

            case 4:
                cells[row][col]
                    .setForeground(Color.MAGENTA);
                break;

            case 5:
                cells[row][col]
                    .setForeground(Color.ORANGE);
                break;

            case 6:
                cells[row][col]
                    .setForeground(Color.CYAN);
                break;

            default:
                cells[row][col]
                    .setForeground(Color.BLACK);
        }
    }

    private void resetBoard() {

        game = new Game();

        seconds = 0;

        timerLabel.setText("Time: 0");

        timer.start();

        for (int row = 0; row < 10; row++) {

            for (int col = 0; col < 10; col++) {

                cells[row][col]
                    .setText("");

                cells[row][col]
                    .setEnabled(true);

                cells[row][col]
                    .setForeground(Color.BLACK);
            }
        }

        mineLabel.setText(
            "Mines: " +
            game.getRemainingMines()
        );
    }

    private void disableBoard() {

        for (int row = 0; row < 10; row++) {

            for (int col = 0; col < 10; col++) {

                cells[row][col]
                    .setEnabled(false);
            }
        }
    }
}