package com.aswin.entities;

import java.awt.Point;

public abstract class AbstractEntity {

    protected Point position;

    public AbstractEntity(int x, int y) {
        this.position = new Point(x, y);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public abstract void update();

}
