import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.WindowConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FramePuzzle implements Runnable {
    private JFrame frame;

    private int tiles;

    public FramePuzzle(int tiles){
        this.tiles = tiles;
    }

    @Override
    public void run() {
        FifteenPuzzle puzzle = new FifteenPuzzle(this.tiles, 30, 550);
        JButton button = new JButton("Click!");
        
        frame = new JFrame("Game Fifteen Puzzle");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        createComponents(frame.getContentPane(), puzzle);
        frame.add(puzzle, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container, FifteenPuzzle fif) {
    }

    public JFrame getFrame() {
        return frame;
    }
}
