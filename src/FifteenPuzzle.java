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
    private int blank;
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
        this.gameOver = true;

        this.nUbin = size * size - 1;
        this.ubin = new int[size * size];

        this.gridSize = (dimension - (2 * margin));
        this.sizeUbin = this.gridSize / size;

        setPreferredSize(new Dimension(dimension, dimension + margin));
        setBackground(Color.DARK_GRAY);
        setForeground(Color.ORANGE);
        setFont(new Font("SansSerif", Font.BOLD, 40));

        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent mouse) {
                if(gameOver){
                    newGame();
                } else {
                    int clickX = mouse.getX() - margin;  
                    int clickY = mouse.getY() - margin;

                    if (clickX < 0 || clickY < 0 || clickX > gridSize || clickY > gridSize ) {
                        return;
                    }

                    int posisiX = clickX / sizeUbin; 
                    int posisiY = clickY / sizeUbin; 

                    int blankX = blank % size; 
                    int blankY = blank / size; 

                    int posisiArray = posisiY * size + posisiX;

                    int arah = 0;
                    
                    //jika ubin yang diklik berada diatas dan dibawah ubin kosong
                    if(posisiX == blankX && Math.abs(posisiY - blankY) > 0){
                        if (posisiY - blankY > 0){
                            arah = size;
                        } else {
                            arah = -size;
                        }
                    //jika ubin yang diklik berada dikiri dan kanan ubin kosong                        
                    } else if (posisiY == blankY && Math.abs(posisiX - blankX) > 0){
                        if (posisiX - blankX > 0){
                            arah = 1;
                        } else {
                            arah = -1;
                        }                        
                    }

                    if (arah != 0){
                        do{
                            //tukar ubin blank dengan ubin yang diklik
                            int newBlank = blank + arah;
                            ubin[blank] = ubin[newBlank];
                            blank = newBlank;
                        } while (blank != posisiArray);

                        ubin[blank] = 0;
                    }

                    gameOver = isSolved();
                }
                repaint();
            }
        });            
        newGame();
    }

    //Mengubah 1D array menjadi 2D array
    private void drawGrid(Graphics2D g) {
        for (int i = 0; i < ubin.length; i++) {

            int r = i / this.size;
            int c = i % this.size;

            int x = this.margin + c * this.sizeUbin;
            int y = this.margin + r * this.sizeUbin;

            if (ubin[i] == 0) {
                if (gameOver) {
                    g.setColor(Color.ORANGE);
                    drawCenteredString(g, "SELAMAT!", x, y);
                }
                continue;
            }

            g.setColor(getForeground());
            g.fillRect(x, y, sizeUbin, sizeUbin);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, sizeUbin, sizeUbin);
            g.setColor(Color.WHITE);

            drawCenteredString(g, String.valueOf(ubin[i]), x, y);
        }
    }

    private void drawStartMessage(Graphics2D g) {
        if (gameOver) {
            g.setFont(getFont().deriveFont(Font.BOLD, 18));
            g.setColor(Color.ORANGE);
            String s = "Click to start new game";
            g.drawString(s, (getWidth() - g.getFontMetrics().stringWidth(s)) / 2, getHeight() - margin);
        }
    }

    private void drawCenteredString(Graphics2D g, String s, int x, int y) {
        FontMetrics fm = g.getFontMetrics();
        int ascending = fm.getAscent();
        int descending = fm.getDescent();
        g.drawString(s, x + (sizeUbin - fm.stringWidth(s)) / 2,
                y + (ascending + (sizeUbin - (ascending + descending)) / 2));
    }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    drawGrid(g2D);
    drawStartMessage(g2D);
  }    

    //method reset
    private void reset() {
        for (int i = 0; i < ubin.length; i++) {
            ubin[i] = (i + 1) % ubin.length;
        }
        blank = ubin.length - 1;
    }

    //method shuffle untuk mengacak urutan
    private void shuffle() {
        int n = nUbin;
        while (n > 1) {
            int r = RANDOM.nextInt(n--);
            int tmp = ubin[r];
            ubin[r] = ubin[n];
            ubin[n] = tmp;
        }
    }

    //method newGame untuk memulai game
    private void newGame() {
        do {
            reset();
            shuffle();
        } while (!isSolvable());
        gameOver = false;
    }
    
    //method untuk mengecek inversi
    private boolean isSolvable() {
        int inversi = 0;
        for (int i = 0; i < nUbin; i++) {
            for (int j = 0; j < i; j++) {
                if (ubin[j] > ubin[i])
                    inversi++;
            }
        }
        return inversi % 2 == 0;
    }

    //method untuk mengecek sudah terselesaikan atau belum
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
