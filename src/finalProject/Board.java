package finalProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    int[] HEIGHT = {100, 200, 300};
    int[] WIDTH = {100, 200, 300};
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    int[] RAND_POS = {9, 19, 29};
    private final int DELAY = 140;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;
    
    int userInput = Integer.parseInt(JOptionPane.showInputDialog("What size board would you like? \n(1)100 \n(2)200 \n(3)300"));
    
    public Board() throws IOException {
    	if(userInput == 1){
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(WIDTH[0], HEIGHT[0]));
        loadImages();
        initGame();
    	}
    	if(userInput == 2)
    	{
                addKeyListener(new TAdapter());
                setBackground(Color.black);
                setFocusable(true);

                setPreferredSize(new Dimension(WIDTH[1], HEIGHT[1]));
                loadImages();
                initGame();
            	
    	}
    	if(userInput == 3){
                addKeyListener(new TAdapter());
                setBackground(Color.black);
                setFocusable(true);

                setPreferredSize(new Dimension(WIDTH[2], HEIGHT[2]));
                loadImages();
                initGame();

    	}
    }

    private void loadImages() throws IOException {

    	ball = ImageIO.read(new File("src\\dotBody.jpg"));
    	
    	apple = ImageIO.read(new File("src\\dotFood.jpg"));

        head = ImageIO.read(new File("src\\dotBody.jpg"));
    }

    private void initGame() {

        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        
        if (inGame) {

            g.drawImage(apple, apple_x, apple_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) {
       if(userInput == 1){ 
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (WIDTH[0] - metr.stringWidth(msg)) / 2, HEIGHT[0] / 2);
    }
       if(userInput == 2){ 
           String msg = "Game Over";
           Font small = new Font("Helvetica", Font.BOLD, 14);
           FontMetrics metr = getFontMetrics(small);

           g.setColor(Color.white);
           g.setFont(small);
           g.drawString(msg, (WIDTH[1] - metr.stringWidth(msg)) / 2, HEIGHT[1] / 2);
       }
       if(userInput == 3){ 
           String msg = "Game Over";
           Font small = new Font("Helvetica", Font.BOLD, 14);
           FontMetrics metr = getFontMetrics(small);

           g.setColor(Color.white);
           g.setFont(small);
           g.drawString(msg, (WIDTH[2] - metr.stringWidth(msg)) / 2, HEIGHT[2] / 2);
       }
    }   

    private void checkApple() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {

            dots++;
            locateApple();
        }
    }

    private void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    private void checkCollision() {
    	if(userInput == 1){
        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= HEIGHT[0]) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= WIDTH[0]) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }
        
        if(!inGame) {
            timer.stop();
        }}
    	if(userInput == 2){
            for (int z = dots; z > 0; z--) {

                if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                    inGame = false;
                }
            }

            if (y[0] >= HEIGHT[1]) {
                inGame = false;
            }

            if (y[0] < 0) {
                inGame = false;
            }

            if (x[0] >= WIDTH[1]) {
                inGame = false;
            }

            if (x[0] < 0) {
                inGame = false;
            }
            
            if(!inGame) {
                timer.stop();
            }}
    	if(userInput == 3){
            for (int z = dots; z > 0; z--) {

                if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                    inGame = false;
                }
            }

            if (y[0] >= HEIGHT[2]) {
                inGame = false;
            }

            if (y[0] < 0) {
                inGame = false;
            }

            if (x[0] >= WIDTH[2]) {
                inGame = false;
            }

            if (x[0] < 0) {
                inGame = false;
            }
            
            if(!inGame) {
                timer.stop();
            }}
    }

    private void locateApple() {
if(userInput == 1)
	{
        int r = (int) (Math.random() * RAND_POS[0]);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS[0]);
        apple_y = ((r * DOT_SIZE));
	}
if(userInput == 2)
{
	int r = (int) (Math.random() * RAND_POS[1]);
    apple_x = ((r * DOT_SIZE));

    r = (int) (Math.random() * RAND_POS[1]);
    apple_y = ((r * DOT_SIZE));
}
if(userInput == 3)
{
	int r = (int) (Math.random() * RAND_POS[2]);
    apple_x = ((r * DOT_SIZE));

    r = (int) (Math.random() * RAND_POS[2]);
    apple_y = ((r * DOT_SIZE));
}
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }
}