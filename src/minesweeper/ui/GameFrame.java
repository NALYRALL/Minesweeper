package minesweeper.ui;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    public GameFrame() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel panel = new GamePanel();
        add(panel);
        setVisible(true);
    }
}