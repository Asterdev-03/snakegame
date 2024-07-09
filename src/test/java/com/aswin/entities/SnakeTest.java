package com.aswin.entities;

import java.awt.Point;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.aswin.utils.Direction;

public class SnakeTest {

    private Snake snake;

    @Before
    public void setUp() {
        snake = new Snake();
    }

    @Test
    public void testInitialDirection() {
        assertEquals(Direction.RIGHT, snake.getDirection());
    }

    @Test
    public void testSetDirection() {
        snake.setDirection(Direction.UP);
        assertEquals(Direction.UP, snake.getDirection());
    }

    @Test
    public void testMove() {
        Point initialHead = snake.getBody().get(0);
        snake.setDirection(Direction.RIGHT);
        snake.move();
        Point newHead = snake.getBody().get(0);
        assertEquals(new Point(initialHead.x + 1, initialHead.y), newHead);
    }

    @Test
    public void testGrow() {
        int initialSize = snake.getBody().size();
        snake.grow();
        assertEquals(initialSize + 1, snake.getBody().size());
    }

}
