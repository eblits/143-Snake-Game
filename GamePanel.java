package snakedemo.snakedemo;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.Serial;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener{



    @Serial
    private static final long serialVersionUID = 1L;

    Random rand = new Random();
    float r = rand.nextFloat();
    float g = rand.nextFloat();
    float b = rand.nextFloat();


    static final int WIDTH = 500;
    static final int HEIGHT = 500;

    int tomatoX;
    int tomatoY;
    static final int UNIT_SIZE = 20;
    static final int NUMBER_OF_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);

    // hold x and y coordinates for body parts of the snake
    final int[] x = new int[NUMBER_OF_UNITS];
    final int[] y = new int[NUMBER_OF_UNITS];

    // initial length of the snake
    int length = 4;
    int foodEaten;
    int foodX;
    int foodY;
    char direction = 'D';
    boolean running = false;
    Random random;
    Timer timer;

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        play();
    }

    public void play() {
        addFood();
        addTomato();
        running = true;

        timer = new Timer(80, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }

    public void move() {
        for (int i = length; i > 0; i--) {
            // shift the snake one unit to the desired direction to create a move
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        if (direction == 'L') {
            x[0] = x[0] - UNIT_SIZE;
        } else if (direction == 'R') {
            x[0] = x[0] + UNIT_SIZE;
        } else if (direction == 'U') {
            y[0] = y[0] - UNIT_SIZE;
        } else {
            y[0] = y[0] + UNIT_SIZE;
        }
    }

    public void checkFood() {
        if(x[0] == foodX && y[0] == foodY) {
            length++;
            foodEaten++;
            addFood();
        }
    }

    public void checkTomato() {
        if (x[0] == tomatoX && y[0] == tomatoY) {
            length--;
            foodEaten--;
            addTomato();
        }
    }

    public void draw(Graphics graphics) {


        if (running) {


            graphics.setColor(new Color(93, 169, 50));
            graphics.fillRect(foodX, foodY, UNIT_SIZE, UNIT_SIZE);

            graphics.setColor(new Color(169, 50, 50));
            graphics.fillRect(tomatoX, tomatoY, UNIT_SIZE, UNIT_SIZE);

            graphics.setColor(Color.white);
            graphics.fillOval(x[0], y[0], UNIT_SIZE, UNIT_SIZE);

            for (int i = 1; i < length; i++) {

                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();

                graphics.setColor(new Color(r, g, b));
                graphics.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }

            graphics.setColor(Color.white);
            graphics.setFont(new Font("Sans serif", Font.PLAIN, 25));
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Score: " + foodEaten, (WIDTH - metrics.stringWidth("Score: " + foodEaten)) / 2, graphics.getFont().getSize());

        } else {
            gameOver(graphics);
        }
    }

    public void addFood() {
        foodX = random.nextInt(WIDTH / UNIT_SIZE)*UNIT_SIZE;
        foodY = random.nextInt(HEIGHT / UNIT_SIZE)*UNIT_SIZE;
    }
    public void addTomato()
    {
        tomatoX = random.nextInt((int)(WIDTH / UNIT_SIZE))*UNIT_SIZE;
        tomatoY = random.nextInt((int)(HEIGHT / UNIT_SIZE))*UNIT_SIZE;
    }

    public void checkHit() {
        // check if head run into its body
        for (int i = length; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
                break;
            }
        }

        // check if head run into walls
        if (x[0] < 0 || x[0] > WIDTH || y[0] < 0 || y[0] > HEIGHT) {
            running = false;
        }

        if(!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.setFont(new Font("Sans serif", Font.PLAIN, 50));
        FontMetrics metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Game Over", (WIDTH - metrics.stringWidth("Game Over")) / 2, HEIGHT / 2);

        graphics.setColor(Color.white);
        graphics.setFont(new Font("Sans serif", Font.PLAIN, 25));
        metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Score: " + foodEaten, (WIDTH - metrics.stringWidth("Score: " + foodEaten)) / 2, graphics.getFont().getSize());

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (running) {
            move();
            checkFood();
            checkTomato();
            checkHit();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;

                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;

                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
