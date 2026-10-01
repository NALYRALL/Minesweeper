package minesweeper;

import java.util.Random;

public class Board {

    private final Cell[][] cells = new Cell[10][10];
    private final int mineCount = 10;

    public Board() {

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                cells[row][col] = new Cell();
            }
        }
    }

    public void initialize(int safeRow, int safeCol) {

        placeMines(safeRow, safeCol);
        calculateAdjacentMines();
    }

    private void placeMines(int safeRow, int safeCol) {

        Random random = new Random();
        int placed = 0;

        while (placed < mineCount) {

            int row = random.nextInt(10);
            int col = random.nextInt(10);

            // Keep the 3x3 area around first click safe
            if (Math.abs(row - safeRow) <= 1 &&
                Math.abs(col - safeCol) <= 1) {
                continue;
            }

            if (!cells[row][col].isMine()) {
                cells[row][col].setMine(true);
                placed++;
            }
        }
    }

    private void calculateAdjacentMines() {

        for (int row = 0; row < 10; row++) {

            for (int col = 0; col < 10; col++) {

                if (cells[row][col].isMine()) {
                    continue;
                }

                int count = 0;

                for (int dr = -1; dr <= 1; dr++) {

                    for (int dc = -1; dc <= 1; dc++) {

                        int newRow = row + dr;
                        int newCol = col + dc;

                        if (newRow < 0 || newRow >= 10 ||
                            newCol < 0 || newCol >= 10) {
                            continue;
                        }

                        if (dr == 0 && dc == 0) {
                            continue;
                        }

                        if (cells[newRow][newCol].isMine()) {
                            count++;
                        }
                    }
                }

                cells[row][col].setAdjacentMines(count);
            }
        }
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public int getMineCount() {
        return mineCount;
    }

    public int getTotalCells() {
        return 10 * 10;
    }
}