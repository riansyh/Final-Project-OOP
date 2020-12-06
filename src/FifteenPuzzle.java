/*
| NPM          | Name                |
| ------------ | ------------------- |
| 140810190002 | Rizal Herliansyah H |
| 140810190014 | Indra Kurniawan     |
| 140810190026 | Rian Febriansyah    |
*/

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
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ListIterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    public int score = 1000;
    public int jumlahClick = 0;
    public int xCoor;
    public int yCoor;
    public int solve;

    ArrayList arr = new ArrayList(2);

    public FifteenPuzzle(int size, int margin, int dimension) {
        this.size = size;
        this.dimension = dimension;
        this.margin = margin;
        this.gameOver = true;

        this.nUbin = size * size - 1;
        this.ubin = new int[size * size];

        this.gridSize = (dimension - (2 * margin));
        this.sizeUbin = this.gridSize / size;

        setPreferredSize(new Dimension(dimension + 200, dimension + margin));
        setBackground(Color.DARK_GRAY);
        setForeground(Color.ORANGE);
        setFont(new Font("SansSerif", Font.BOLD, 40));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouse) {
                if (gameOver) { 
                    newGame();
                } else {
                    int clickX = mouse.getX() - margin;
                    int clickY = mouse.getY() - margin;

                    // cek jika menekan tombol reset
                    if (clickX + margin >= ((gridSize / 2) - 5) && clickY + margin >= (margin + gridSize + 15)
                            && clickY + margin <= (margin + gridSize + 45)
                            && clickX + margin <= (((gridSize / 2) - 5) + 60)) {
                        newGame();
                        repaint();
                        jumlahClick = 0;
                        return;
                    }

                    if (clickX < 0 || clickY < 0 || clickX > gridSize || clickY > gridSize) {
                        return;
                    }

                    int posisiX = clickX / sizeUbin;
                    int posisiY = clickY / sizeUbin;

                    int blankX = blank % size;
                    int blankY = blank / size;

                    int posisiArray = posisiY * size + posisiX;

                    int arah = 0;

                    // jika ubin yang diklik berada diatas dan dibawah ubin kosong
                    if (posisiX == blankX && Math.abs(posisiY - blankY) > 0) {
                        if (posisiY - blankY > 0) {
                            arah = size;
                        } else {
                            arah = -size;
                        }

                        // jika ubin yang diklik berada dikiri dan kanan ubin kosong
                    } else if (posisiY == blankY && Math.abs(posisiX - blankX) > 0) {
                        if (posisiX - blankX > 0) {
                            arah = 1;
                        } else {
                            arah = -1;
                        }
                    }

                    if (arah != 0) {
                        do {
                            // tukar ubin blank dengan ubin yang diklik
                            int newBlank = blank + arah;
                            ubin[blank] = ubin[newBlank];
                            blank = newBlank;
                            jumlahClick += 1;
                        } while (blank != posisiArray);
                        ubin[blank] = 0;
                    }
                    gameOver = isSolved();
                }
                repaint();
                System.out.println("Jumlah Step " + jumlahClick);
            }
        });
        newGame();
    }

    // Mengubah 1D array menjadi 2D array
    private void drawGrid(Graphics2D g) {
        for (int i = 0; i < ubin.length; i++) {

            int r = i / this.size;
            int c = i % this.size;

            this.xCoor = this.margin + c * this.sizeUbin;
            this.yCoor = this.margin + r * this.sizeUbin;

            if (ubin[i] == 0) {
                if (gameOver) {
                    this.score = (this.score * this.size) - (this.jumlahClick * 10);
                    arr.add(this.score);
                    g.setColor(Color.ORANGE);
                    g.setFont(getFont().deriveFont(Font.BOLD, 16));
                    drawCenteredString(g, "Solve! ", this.xCoor, this.yCoor - 15);
                    drawCenteredString(g, "Jumlah Step = " + this.jumlahClick, this.xCoor, this.yCoor + 5);
                    drawCenteredString(g, "Score " + this.score, this.xCoor, this.yCoor + 25);
                    solve += 1;
                    System.out.println("solve : " + this.solve);
                    jumlahClick = 0;
                    this.score = 1000;
                }
                continue;
            }

            g.setColor(getForeground());
            g.fillRoundRect(this.xCoor, this.yCoor, sizeUbin, sizeUbin, 20, 20);
            g.setColor(Color.BLACK);
            g.drawRoundRect(this.xCoor, this.yCoor, sizeUbin, sizeUbin, 20, 20);
            g.setColor(Color.WHITE);

            drawCenteredString(g, String.valueOf(ubin[i]), this.xCoor, this.yCoor);
        }
        // Reset Button
        g.setColor(Color.ORANGE);
        g.fillRoundRect((gridSize / 2) - 5, margin + gridSize + 15, 70, 30, 30, 30);

        g.setColor(Color.DARK_GRAY);
        g.setFont(getFont().deriveFont(Font.BOLD, 14));
        g.drawString("Reset", gridSize / 2 + 10, margin + gridSize + 35);

        g.setColor(Color.ORANGE);
        g.drawString(" -- Kelompok VDJCISN -- ", 745, 50);
        g.drawString("High Score", 790, 80);

        Collections.sort(arr, Collections.reverseOrder());

        int index = 100;
        int n = 1;
        ListIterator li = arr.listIterator();
        while (li.hasNext()) {
            g.drawString(n + ". " + li.next().toString(), 803, index);
            index += 20;
            n++;
        }

        g.drawLine(740, this.dimension + 50, 740, 0);

        if (!gameOver) {
            g.setColor(Color.WHITE);
            g.setFont(getFont().deriveFont(Font.BOLD, 14));
            g.drawString("Jumlah Step : " + this.jumlahClick, gridSize / 2 + 75, margin + gridSize + 35);
        }
    }

    private void drawStartMessage(Graphics2D g) {
        if (gameOver) {
            String s = "Click to start new game";

            jumlahClick = 0;

            g.setColor(Color.DARK_GRAY);
            g.fillRoundRect((gridSize / 2) - 5, margin + gridSize + 15, 140, 40, 30, 30);

            g.setColor(Color.ORANGE);
            g.setFont(getFont().deriveFont(Font.BOLD, 18));
            g.drawString(s, (getWidth() - g.getFontMetrics().stringWidth(s)) / 2, margin + gridSize + 35);
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

    // method reset
    private void reset() {
        for (int i = 0; i < ubin.length; i++) {
            ubin[i] = (i + 1) % ubin.length;
        }
        blank = ubin.length - 1;
    }

    // method shuffle untuk mengacak urutan
    private void shuffle() {
        int n = nUbin;
        while (n > 1) {
            int r = RANDOM.nextInt(n--);
            int tmp = ubin[r];
            ubin[r] = ubin[n];
            ubin[n] = tmp;
        }
    }

    // method newGame untuk memulai game
    private void newGame() {
        do {
            jumlahClick = 0;
            reset();
            shuffle();
        } while (!isSolvable());
        gameOver = false;
    }

    // method untuk mengecek inversi
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

    // method untuk mengecek sudah terselesaikan atau belum
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
