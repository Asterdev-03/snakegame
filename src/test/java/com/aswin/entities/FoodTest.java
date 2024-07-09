package com.aswin.entities;

import java.awt.Point;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class FoodTest {

    private Food food;

    @Before
    public void setUp() {
        food = new Food(20, 20);
    }

    @Test
    public void testInitialPosition() {
        assertNotNull(food.getPosition());
    }

    @Test
    public void testGetPosition() {
        Point position = food.getPosition();
        assertNotNull(position);
        assertTrue(position.x >= 0 && position.x < 20);
        assertTrue(position.y >= 0 && position.y < 20);
    }

    @Test
    public void testPlaceFood() {
        Point initialPosition = food.getPosition();
        food.placeFood();
        Point newPosition = food.getPosition();
        assertNotEquals(initialPosition, newPosition);
    }
}
