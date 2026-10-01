# Minesweeper

A Java Swing based Minesweeper game built from scratch to practice
Object-Oriented Programming, recursion, 2D arrays, event handling,
game-state management, and GUI development.

## Features

- 10 × 10 game board
- Random mine generation
- First-click safety
- Safe 3 × 3 opening area
- Automatic adjacent-mine calculation
- Recursive empty-cell reveal
- Left-click to reveal cells
- Right-click to flag/unflag cells
- Mine counter
- Flag limit
- Chording
- Win detection
- Loss detection
- Mine reveal after losing
- Restart functionality
- Game timer
- Number-based colors
- Win/Lose dialogs
- Java Swing GUI

## Technologies Used

- Java
- Java Swing
- Object-Oriented Programming
- Recursion
- Depth-First Search (DFS)
- 2D Arrays
- Event Handling
- Lambda Expressions
- Enums

## Project Structure

Minesweeper/
├── .gitignore
├── README.md
└── src/
    └── minesweeper/
        ├── Main.java
        ├── Board.java
        ├── Cell.java
        ├── Game.java
        ├── GameState.java
        └── ui/
            ├── GameFrame.java
            ├── GamePanel.java
            └── CellButton.java

Architecture
                    Main
                      │
                      ▼
                 GameFrame
                      │
                      ▼
                 GamePanel
                      │
          ┌───────────┴───────────┐
          ▼                       ▼
     CellButton                 Game
                                  │
                                  ▼
                                Board
                                  │
                                  ▼
                                Cell


How to Play

Action	Control
Reveal cell	Left Click
Flag / Unflag	Right Click
Chord revealed number	Left Click
Restart	Restart Button
Running the Project
Requirements
Java JDK 21 or compatible version
Git (optional)

Check Java:

java -version

Check the Java compiler:

javac -version
1. Clone the Repository
git clone https://github.com/NALYRALL/Minesweeper
2. Enter the Project
cd Minesweeper
3. Go to the Source Directory
cd src
4. Compile
javac minesweeper\ui\*.java minesweeper\*.java
5. Run
java minesweeper.Main