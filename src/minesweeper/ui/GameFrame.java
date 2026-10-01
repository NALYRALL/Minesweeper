package minesweeper.ui;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    public GameFrame() {
        setSize(600, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        GamePanel panel = new GamePanel();
        add(panel);
        setVisible(true);
    }
}