package com.aswin.entities;

import java.awt.Point;
import java.util.LinkedList;

import com.aswin.utils.Direction;
import com.aswin.utils.Movable;

public class Snake extends AbstractEntity implements Movable {

    private LinkedList<Point> body;
    private Direction direction;

    public Snake() {
        super(5, 5);
        body = new LinkedList<>();
        direction = Direction.RIGHT;
        body.add(position);
    }

    public LinkedList<Point> getBody() {
        return body;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void move() {
        Point newHead = new Point(position);

        switch (direction) {
            case UP:
                newHead.y--;
                break;
            case DOWN:
                newHead.y++;
                break;
            case LEFT:
                newHead.x--;
                break;
            case RIGHT:
                newHead.x++;
                break;
        }

        position = newHead;
        body.addFirst(newHead);
        body.removeLast();
    }

    @Override
    public void update() {
        move();
    }

    public void grow() {
        body.addLast(new Point(body.getLast()));
    }

}
