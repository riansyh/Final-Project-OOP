/*
| NPM          | Name                |
| ------------ | ------------------- |
| 140810190002 | Rizal Herliansyah H |
| 140810190014 | Indra Kurniawan     |
| 140810190026 | Rian Febriansyah    |
*/

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class FramePuzzle extends JPanel implements Runnable {
    private JFrame frame;

    private int tiles;

    public FramePuzzle(int tiles){
        this.tiles = tiles;
    }

    @Override
    public void run() {
        FifteenPuzzle puzzle = new FifteenPuzzle(this.tiles, 30, 750);
        frame = new JFrame("Game Fifteen Puzzle");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        createComponents(frame.getContentPane(), puzzle);
        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container, FifteenPuzzle puzzle) {
        JLabel text = new JLabel("Size Puzzle: " + this.tiles + " x " + this.tiles , SwingConstants.CENTER);
        container.add(text, BorderLayout.SOUTH);
        container.add(puzzle, BorderLayout.CENTER);
    }

    public JFrame getFrame() {
        return frame;
    }
}
