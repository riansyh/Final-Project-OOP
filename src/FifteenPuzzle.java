import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class FifteenPuzzle extends JPanel {
    private int size;
    private int dimension;
    private int nUbin;
    private int blankPos;
    private static final Color FOREGROUND_COLOR = new Color(239, 83, 80);
    private static final Random RANDOM = new Random();
    private int[] ubin;
    private int margin;
    private int sizeUbin;
    private JFrame frame;
    private int gridSize;
    private boolean gameOver;

    public FifteenPuzzle(int size, int margin, int dimension) {
        this.size = size;
        this.dimension = dimension;
        this.margin = margin;
        setPreferredSize(new Dimension(dimension, dimension + margin));
        setBackground(Color.LIGHT_GRAY);
        setForeground(Color.ORANGE);
        setFont(new Font("SansSerif", Font.BOLD, 40));
    }

    private void newGame() {
        do {
            reset();
            shuffle();
        } while (!isSolvable());
        gameOver = false;
    }

    private void reset() {
        for (int i = 0; i < ubin.length; i++) {
            ubin[i] = (i + 1) % ubin.length;
        }
        blankPos = ubin.length - 1;
    }

    private void shuffle() {
        int n = nUbin;
        while (n > 1) {
            int r = RANDOM.nextInt(n--);
            int tmp = ubin[r];
            ubin[r] = ubin[n];
            ubin[n] = tmp;
        }
    }

    private boolean isSolvable() {
        int countInversions = 0;
        for (int i = 0; i < nUbin; i++) {
            for (int j = 0; j < i; j++) {
                if (ubin[j] > ubin[i])
                    countInversions++;
            }
        }
        return countInversions % 2 == 0;
    }

    private boolean isSolved() {
        if (ubin[ubin.length - 1] != 0)
            return false;

        for (int i = nUbin - 1; i >= 0; i--) {
            if (ubin[i] != i + 1)
                return false;
        }
        return true;
    }
}
