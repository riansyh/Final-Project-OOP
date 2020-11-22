import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class FramePuzzle implements Runnable {
    private JFrame frame;

    private int tiles;

    public FramePuzzle(int tiles){
        this.tiles = tiles;
    }

    @Override
    public void run() {
        frame = new JFrame("Game Fifteen Puzzle");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        createComponents(frame.getContentPane());
        frame.add(new FifteenPuzzle(this.tiles, 550, 30), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

        //Cek ukuran
        System.out.println(this.tiles);
    }

    private void createComponents(Container container) {

    }

    public JFrame getFrame() {
        return frame;
    }
}
