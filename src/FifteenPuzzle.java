import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

public class FifteenPuzzle extends JPanel {
    // kerangka class

    //
    private int tiles;
    private int dimension;
    private int[] ubin;
<<<<<<< HEAD
<<<<<<< Updated upstream
    private int sizeUbin;   
=======
    private int sizeUbin;
>>>>>>> 62d4c7d0c2689a5a086d7f02b2c47ff453483267
    private int margin;
=======
    private int sizeUbin;
>>>>>>> Stashed changes

    private JFrame frame;

    public FifteenPuzzle(int tiles, int dimension, int margin) {
        this.tiles = tiles;
        this.dimension = dimension;
        this.margin = margin;
        setPreferredSize(new Dimension(dimension, dimension + margin));
        setBackground(Color.LIGHT_GRAY);
        setForeground(Color.ORANGE);
        setFont(new Font("SansSerif", Font.BOLD, 40));
    }
}
