package com.teamapk.snake.model;

import com.teamapk.brick.Model;
import com.teamapk.brick.Vector;
import com.teamapk.snake.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * Snake model (singleton class)
 */
public final class Snake extends Model {

    private static Snake singleElement;

    public final List<Vector> breaks = new ArrayList<>();
    private SnakeDirection direction = SnakeDirection.RIGHT;

    public int length = Config.INITIAL_SNAKE_LENGTH;

    private Snake() {
        this.position = new Vector(10, 250);
        metrics = new Vector(Config.SNAKE_WIDTH, Config.SNAKE_WIDTH, 1);
        motion = new Vector(Config.INITIAL_SNAKE_SPEED, 0);
        breaks.add(position);
    }

    public static void init() {
        singleElement = new Snake();
    }

    public static Snake getInstance() {
        return singleElement;
    }

    /**
     * Get snake's direction
     *
     * @return Snake's direction
     */
    public SnakeDirection getDirection() {
        return direction;
    }

    /**
     * Set snake's direction
     *
     * @param direction Snake's direction
     */
    public void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }

    /**
     * Add the current position to the breaks' list
     */
    public void addBreak() {
        breaks.add(position);
    }

    /**
     * Add the breakPosition to the breaks' list
     *
     * @param breakPosition breakPosition Vector
     */
    public void addBreak(Vector breakPosition) {
        breaks.add(breakPosition);
    }

    /**
     * Returns the number of the snake components (parts from break to break/head)
     *
     * @return the number
     */
    public int getSnakeComponentCount() {
        return breaks.size();
    }

    /**
     * Returns a snake component with position and metrics, which is a straight part of the snake from a break to a
     * break/head
     *
     * @param i The number of the component. 0 is the tale, {@link #getSnakeComponentCount()} is connected with the head.
     * @return a Model
     */
    public Vector[] getSnakeComponentAsModel(int i) { // TODO as Model (it adds currently automatically to the Storage)
        Vector a, b;

        a = breaks.get(i);
        if (i + 1 < breaks.size())
            b = breaks.get(i + 1);
        else
            b = position;

        // If the snake jumpED on the side
        if (a.getX() == 0)
            a = new Vector(Config.WIDTH - Config.SNAKE_WIDTH, a.getY());
        else if (a.getX() == Config.WIDTH)
            a = new Vector(1, a.getY());
        else if (a.getY() == 0)
            a = new Vector(a.getX(), Config.HEIGHT - Config.SNAKE_WIDTH);
        else if (a.getY() == Config.HEIGHT)
            a = new Vector(a.getX(), 1);

        // If the snake jumpS on the side
        if (b.getX() == 0)
            b = new Vector(1, b.getY());
        else if (b.getX() == Config.WIDTH)
            b = new Vector(Config.WIDTH - Config.SNAKE_WIDTH, b.getY());
        else if (b.getY() == 0)
            b = new Vector(b.getX(), 1);
        else if (b.getY() == Config.HEIGHT)
            b = new Vector(b.getX(), Config.HEIGHT - Config.SNAKE_WIDTH);

        if (a.getX() + a.getY() > b.getX() + b.getY()) {// b is closer to the origo than a
            return new Vector[]{
                    new Vector(b.getX(), b.getY(), Math.min(a.getZ(), b.getZ())),
                    new Vector(a.getX() + Config.SNAKE_WIDTH - b.getX(), a.getY() + Config.SNAKE_WIDTH - b.getY(), Math.abs(a.getZ() - b.getZ()) + 1)
            };
        } else { // b is closer to the origo than a
            return new Vector[]{
                    new Vector(a.getX(), a.getY(), Math.min(a.getZ(), b.getZ())),
                    new Vector(b.getX() + Config.SNAKE_WIDTH - a.getX(), b.getY() + Config.SNAKE_WIDTH - a.getY(), Math.abs(a.getZ() - b.getZ()) + 1)
            };
        }
    }

    public enum SnakeDirection {
        UP, DOWN, LEFT, RIGHT
    }
}
