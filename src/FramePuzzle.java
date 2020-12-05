import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class FramePuzzle implements Runnable {
    private JFrame frame;

    private int tiles;

    public FramePuzzle(int tiles){
        this.tiles = tiles;
    }

    @Override
    public void run() {
        FifteenPuzzle puzzle = new FifteenPuzzle(this.tiles, 30, 550);
        JLabel text = new JLabel("Let's Beat The Puzzle!", SwingConstants.CENTER);
        frame = new JFrame("Game Fifteen Puzzle");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(puzzle, BorderLayout.CENTER);
        frame.add(text, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
