package com.teamapk.snake.controller;


import android.util.Log;
import com.teamapk.brick.Modifier;
import com.teamapk.brick.Vector;
import com.teamapk.brick.util.GameOverException;
import com.teamapk.snake.Config;
import com.teamapk.snake.model.Snake;

public class SnakeTaleModifier extends Modifier {



    @Override
    public void doRound() throws GameOverException {
        Snake snake = Snake.getInstance();
        int sumLength = 0;
        for (int i = 0; i < snake.breaks.size() - 1 + 1; ++i)
            sumLength += distance(i);

        int plus = sumLength - snake.length;
        while (plus > 0) {
            int distance0 = distance(0);
            if (distance0 <= plus) { // while loop must continue
                plus -= distance0;
                snake.breaks.remove(0);
            } else { // while loop will terminate
                Vector v1 = snake.breaks.get(0),
                        v2 = snake.breaks.size() == 1 ? snake.position : snake.breaks.get(1);

                // If v2 is on the other side, change it
                if (v1.getX() == 0)
                    v1 = new Vector(Config.WIDTH - Config.SNAKE_WIDTH, v1.getY(), v1.getZ());
                else if (v1.getX() == Config.WIDTH)
                    v1 = new Vector(1, v1.getY(), v1.getZ());
                if (v1.getY() == 0)
                    v1 = new Vector(v1.getX(), Config.HEIGHT - Config.SNAKE_WIDTH, v1.getZ());
                else if (v1.getY() == Config.HEIGHT)
                    v1 = new Vector(v1.getX(), 1, v1.getZ());


                switch (different(v1, v2)) {
                    case -2: // Invalid breaks
                        return;
                    case -1: // 0th and 1st are the same
                        snake.breaks.remove(0);
                        break;
                    case 0:
                        int newX = v1.getX() + (v2.getX() > v1.getX() ? +plus : -plus);
                        snake.breaks.set(0, new Vector(newX, v1.getY(), v1.getZ()));
                        break;
                    case 1:
                        int newY = v1.getY() + (v2.getY() > v1.getY() ? +plus : -plus);
                        snake.breaks.set(0, new Vector(v1.getX(), newY, v1.getZ()));
                        break;
                    case 2:
                        int newZ = v1.getZ() + (v2.getZ() > v1.getZ() ? +plus : -plus);
                        snake.breaks.set(0, new Vector(v1.getX(), v1.getY(), newZ));
                        break;
                }
                plus = 0;
            }
        }
    }


    /**
     * Returns the distance between the ith and the (i+1)th Vector in {@link Snake#breaks}. If i==the size of
     * {@link Snake#breaks}-1, the distance of the last break and the head of the snake
     *
     * @param i The index. Must be smaller than the size of {@link Snake#breaks}-1
     * @return The distance
     */
    private int distance(int i) {
        Snake snake = Snake.getInstance();
        Vector v1 = snake.breaks.get(i),
                v2 = i < snake.breaks.size() - 1 ? snake.breaks.get(i + 1) : snake.position;

        // Handle jumps on the borders
        if (v1.getX() == 0)
            v1 = new Vector(Config.WIDTH - Config.SNAKE_WIDTH, v1.getY(), v1.getZ());
        else if (v1.getX() == Config.WIDTH)
            v1 = new Vector(1, v1.getY(), v1.getZ());
        if (v1.getY() == 0)
            v1 = new Vector(v1.getX(), Config.HEIGHT - Config.SNAKE_WIDTH, v1.getZ());
        else if (v1.getY() == Config.HEIGHT)
            v1 = new Vector(v1.getX(), 1, v1.getZ());

        int different = different(v1, v2); // Which coordinate is the different?

        switch (different) {
            case 0:
                return Math.abs(v1.getX() - v2.getX());
            case 1:
                return Math.abs(v1.getY() - v2.getY());
            case 2:
                return Math.abs(v1.getZ() - v2.getZ());
            case -2:
                return 0; // Invalid list
        }
        return 0;
    }

    /**
     * Determines, which coordinate is different in between the two {@link Vector}s
     *
     * @param v1 1st {@link Vector}
     * @param v2 2nd {@link Vector}
     * @return 0, 1, 2, if X/Y/Z is the result, -1, if all 3 coords are the same, -2, if 2 or more coords are different
     */
    private static int different(Vector v1, Vector v2) {
        int different = -1; // Which coordinate is the different?

        // X?
        if (v1.getX() != v2.getX())
            different = 0;
        // Y?
        if (v1.getY() != v2.getY()) {
            if (different != -1) {
                Log.e(SnakeTaleModifier.class.getName(), "Invalid snake.breaks list");
                return -2;
            }
            different = 1;
        }
        // Z?
        if (v1.getZ() != v2.getZ()) {
            if (different != -1) {
                Log.e(SnakeTaleModifier.class.getName(), "Invalid snake.breaks list");
                return -2;
            }
            different = 2;
        }
        return different;
    }
}
