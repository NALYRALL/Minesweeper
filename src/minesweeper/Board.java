package minesweeper;

import java.util.Random;        

public class Board{
    private Cell[][] cells = new Cell[10][10];
    private int mineCount = 10;

    public Board(){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                cells[i][j] = new Cell();
            }
        }
        placeMines();
        calculateAdjacentMines();
    }

    public int getMineCount() {
        return mineCount;
    }

    public int getTotalCells() {
        return 10 * 10;
    }

    private void placeMines(){
        Random random = new Random();
        int placed = 0;
        while(placed < mineCount){
            int row = random.nextInt(10);
            int col = random.nextInt(10);
                if(!cells[row][col].isMine()){
                    cells[row][col].setMine(true);
                    placed++;
                }
        }
    }

    private void calculateAdjacentMines(){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){

                if(cells[i][j].isMine()){
                    continue;
                }

                int count = 0;
                for (int dr = -1; dr <= 1; dr++) {
                    for (int dc = -1; dc <= 1; dc++) {
                        int newRow = i + dr;
                        int newCol = j + dc;

                        if (newRow < 0 || newRow >= 10 ||
                            newCol < 0 || newCol >= 10) {
                            continue;
                        }

                        if(dr==0 && dc==0){
                            continue;
                        }
                        
                        if(cells[newRow][newCol].isMine()){
                            count++;
                        }
                    }
                }
                cells[i][j].setAdjacentMines(count);
            }
        }
    }

    public Cell getCell(int row,int col){
        return cells[row][col];
    }
}