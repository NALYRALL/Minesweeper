package minesweeper;

public class Game {

    private Board board;
    private GameState gameState;

    private int revealedCells;
    private int flaggedCells;

    private boolean firstMove;

    public Game() {

        board = new Board();
        gameState = GameState.PLAYING;

        revealedCells = 0;
        flaggedCells = 0;

        firstMove = true;
    }

    public void reveal(int row, int col) {

        if (gameState != GameState.PLAYING) {
            return;
        }

        Cell cell = board.getCell(row, col);

        // Generate mines after the first click
        if (firstMove) {

            board.initialize(row, col);
            firstMove = false;

            cell = board.getCell(row, col);
        }

        // Flagged cells cannot be revealed
        if (cell.isFlagged()) {
            return;
        }

        // Already revealed
        if (cell.isRevealed()) {
            return;
        }

        // Mine
        if (cell.isMine()) {

            gameState = GameState.LOST;
            return;
        }

        cell.setRevealed(true);
        revealedCells++;

        // Win condition
        if (revealedCells ==
            board.getTotalCells() - board.getMineCount()) {

            gameState = GameState.WON;
            return;
        }

        // Reveal connected empty cells
        if (cell.getAdjacentMines() == 0) {

            for (int dr = -1; dr <= 1; dr++) {

                for (int dc = -1; dc <= 1; dc++) {

                    int newRow = row + dr;
                    int newCol = col + dc;

                    if (newRow < 0 || newRow >= 10 ||
                        newCol < 0 || newCol >= 10) {
                        continue;
                    }

                    reveal(newRow, newCol);
                }
            }
        }
    }

    public void toggleFlag(int row, int col) {

        if (gameState != GameState.PLAYING) {
            return;
        }

        Cell cell = board.getCell(row, col);

        if (cell.isRevealed()) {
            return;
        }

        if (cell.isFlagged()) {

            cell.setFlagged(false);
            flaggedCells--;

        } else {

            if (flaggedCells >= board.getMineCount()) {
                return;
            }

            cell.setFlagged(true);
            flaggedCells++;
        }
    }

    public void chord(int row, int col) {

        if (gameState != GameState.PLAYING) {
            return;
        }

        Cell cell = board.getCell(row, col);

        if (!cell.isRevealed()) {
            return;
        }

        int flagged = 0;

        for (int dr = -1; dr <= 1; dr++) {

            for (int dc = -1; dc <= 1; dc++) {

                int newRow = row + dr;
                int newCol = col + dc;

                if (newRow < 0 || newRow >= 10 ||
                    newCol < 0 || newCol >= 10) {
                    continue;
                }

                if (board.getCell(newRow, newCol).isFlagged()) {
                    flagged++;
                }
            }
        }

        if (flagged != cell.getAdjacentMines()) {
            return;
        }

        for (int dr = -1; dr <= 1; dr++) {

            for (int dc = -1; dc <= 1; dc++) {

                int newRow = row + dr;
                int newCol = col + dc;

                if (newRow < 0 || newRow >= 10 ||
                    newCol < 0 || newCol >= 10) {
                    continue;
                }

                Cell neighbour = board.getCell(newRow, newCol);

                if (!neighbour.isFlagged() &&
                    !neighbour.isRevealed()) {

                    reveal(newRow, newCol);
                }
            }
        }
    }

    public Cell getCell(int row, int col) {
        return board.getCell(row, col);
    }

    public GameState getGameState() {
        return gameState;
    }

    public int getRemainingMines() {
        return board.getMineCount() - flaggedCells;
    }
}