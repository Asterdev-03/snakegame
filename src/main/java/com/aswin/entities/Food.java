package com.aswin.entities;

import java.awt.Point;
import java.util.Random;

public class Food {

    private Point position;
    private int width;
    private int height;

    public Food(int width, int height) {
        this.width = width;
        this.height = height;
        placeFood();
    }

    public void placeFood() {
        Random random = new Random();
        int x = random.nextInt(width);
        int y = random.nextInt(height);

        position = new Point(x, y);
    }

    public Point getPosition() {
        return position;
    }

}
