package com.aswin.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.aswin.entities.Food;
import com.aswin.entities.Snake;

public class GamePanelTest {

    private GamePanel gamePanel;

    @Before
    public void setUp() {
        gamePanel = new GamePanel();
        gamePanel.startGame();
    }

    @Test
    public void testStartGame() {
        assertTrue(gamePanel.isRunning());
        assertNotNull(gamePanel.getSnake());
        assertNotNull(gamePanel.getFood());
    }

    @Test
    public void testCheckFoodCollision() {
        Snake snake = gamePanel.getSnake();
        Food food = gamePanel.getFood();

        snake.getBody().set(0, food.getPosition());
        gamePanel.checkFoodCollision();
        assertEquals(1, gamePanel.getScore());
    }

    @Test
    public void testCheckCollisions() {
        Snake snake = gamePanel.getSnake();
        snake.grow();
        snake.getBody().set(0, snake.getBody().get(1));
        gamePanel.checkCollisions();
        assertFalse(gamePanel.isRunning());
    }
}
