package com.aswin.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.aswin.entities.Food;
import com.aswin.entities.Snake;
import com.aswin.utils.Direction;

public class GamePanel extends JPanel implements KeyListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int UNIT_SIZE = 25;
    private static final int GAME_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    private static final int DELAY = 75;

    private boolean paused;

    private Snake snake;
    private Food food;
    private boolean running;
    private int score;

    private GameThread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(this);
        startGame();
    }

    public boolean isRunning() {
        return running;
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }

    public int getScore() {
        return score;
    }

    public void startGame() {
        snake = new Snake();
        food = new Food(WIDTH / UNIT_SIZE, HEIGHT / UNIT_SIZE);
        running = true;
        score = 0;
        if (gameThread != null && gameThread.isAlive()) {
            gameThread.stopGame();
        }
        gameThread = new GameThread(this);
        gameThread.startGame();
    }

    public void updateGame() {
        if (running && !paused) {
            move();
            checkCollisions();
            checkFoodCollision();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            // Draw food
            g.setColor(Color.red);
            Point foodPos = food.getPosition();
            g.fillRect(foodPos.x * UNIT_SIZE, foodPos.y * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);

            // Draw snake
            LinkedList<Point> body = snake.getBody();
            for (Point point : body) {
                g.setColor(Color.green);
                g.fillRect(point.x * UNIT_SIZE, point.y * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
            }

            // Draw score
            g.setColor(Color.white);
            g.drawString("Score: " + score, 20, 10);
        } else {
            showGameOver(g);
        }
    }

    public void showGameOver(Graphics g) {
        g.setColor(Color.red);
        g.drawString("Game Over", WIDTH / 2 - 30, HEIGHT / 2);
        g.drawString("Score: " + score, WIDTH / 2 - 30, HEIGHT / 2 + 20);

        gameThread.stopGame();

        int choice = JOptionPane.showOptionDialog(this, "Game Over. Score: " + score + "\nDo you want to restart?", "Game Over",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        if (choice == JOptionPane.YES_OPTION) {
            startGame();
        } else {
            System.exit(0);
        }
    }

    public void move() {
        snake.move();
    }

    public void checkFoodCollision() {
        Point head = snake.getBody().getFirst();
        Point foodPos = food.getPosition();

        if (head.equals(foodPos)) {
            snake.grow();
            food.placeFood();
            score++;
        }
    }

    public void checkCollisions() {
        LinkedList<Point> body = snake.getBody();
        Point head = body.getFirst();

        // Check if snake collides with the walls
        if (head.x < 0 || head.x >= WIDTH / UNIT_SIZE || head.y < 0 || head.y >= HEIGHT / UNIT_SIZE) {
            running = false;
        }

        // Check if snake collides with itself
        for (int i = 1; i < body.size(); i++) {
            if (head.equals(body.get(i))) {
                running = false;
                break;
            }
        }

        // if (!running) {
        //     timer.stop();
        // }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_P) {
            paused = !paused;
            return;
        }

        if (!paused) {
            switch (key) {
                case KeyEvent.VK_LEFT:
                    if (snake.getDirection() != Direction.RIGHT) {
                        snake.setDirection(Direction.LEFT);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snake.getDirection() != Direction.LEFT) {
                        snake.setDirection(Direction.RIGHT);
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (snake.getDirection() != Direction.DOWN) {
                        snake.setDirection(Direction.UP);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (snake.getDirection() != Direction.UP) {
                        snake.setDirection(Direction.DOWN);
                    }
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used in this game
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used in this game
    }
}
