package minesweeper;

public class Game{
    private Board board;
    private GameState gameState;
    private int revealedCells;
    private int flaggedCells;

    public Game(){
        board = new Board();  
        gameState = GameState.PLAYING;  
        revealedCells = 0;
        flaggedCells = 0;   
    }

    public int getRemainingMines() {
        return board.getMineCount() - flaggedCells;
    }

    public void reveal(int row,int col){
        Cell cell   = board.getCell(row, col);
        if(gameState != GameState.PLAYING){
            return ;
        }

        if(cell.isMine()){
            gameState = GameState.LOST;
            return;
        }

        if(cell.isRevealed()){
            revealedCells++;
            return;
        }

        if (revealedCells == board.getTotalCells()-board.getMineCount()) {
            gameState = GameState.WON;
            return;
        }

        cell.setRevealed(true);

        if(cell.getAdjacentMines()==0){
            for (int dr = -1; dr <= 1; dr++) {
                for (int dc = -1; dc <= 1; dc++) {
                    int newRow = row + dr;
                    int newCol = col + dc;
                    
                    if (newRow < 0 || newRow >= 10 || newCol < 0 || newCol >= 10) {
                        continue;
                    }
                    reveal(newRow,newCol);
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
        } 
        else {
            if (flaggedCells >= board.getMineCount()) {
                return;
            }
            cell.setFlagged(true);
            flaggedCells++;
        }
    }
    
    public Cell getCell(int row,int col){
        return board.getCell(row, col);
    }

    public GameState getGameState(){
        return gameState;
    }
}