package com.teamapk.snake.controller;

import android.util.Log;
import com.teamapk.brick.Modifier;
import com.teamapk.brick.Vector;
import com.teamapk.brick.util.GameOverException;
import com.teamapk.snake.model.Snake;

/**
 * Change direction of the snake
 */
public class SnakeDirectionModifier extends Modifier {
    private Snake.SnakeDirection direction;
    private Snake.SnakeDirection previousDirection;

    public SnakeDirectionModifier() {
        previousDirection = Snake.getInstance().getDirection();
    }

    @Override
    public void doRound() throws GameOverException {
        Snake snake = Snake.getInstance();
        direction = snake.getDirection();
        if (previousDirection != direction) {
            if (
                    (previousDirection == Snake.SnakeDirection.DOWN && direction == Snake.SnakeDirection.UP) ||
                            (previousDirection == Snake.SnakeDirection.LEFT && direction == Snake.SnakeDirection.RIGHT) ||
                            (previousDirection == Snake.SnakeDirection.UP && direction == Snake.SnakeDirection.DOWN) ||
                            (previousDirection == Snake.SnakeDirection.RIGHT && direction == Snake.SnakeDirection.LEFT)
                    )
                return;
            // Add break
            snake.addBreak();

            int amount = snake.motion.getX() > snake.motion.getY() ? snake.motion.getX() : snake.motion.getY();
            amount = amount == 0 ? 1 : amount;
            switch (direction) {
                case UP:
                    Log.v("SnakeDirection", "Direction: UP");
                    snake.motion = new Vector(0, amount);
                    break;
                case DOWN:
                    Log.v("SnakeDirection", "Direction: DOWN");
                    snake.motion = new Vector(0, -amount);
                    break;
                case LEFT:
                    Log.v("SnakeDirection", "Direction: LEFT");
                    snake.motion = new Vector(-amount, 0);
                    break;
                case RIGHT:
                    Log.v("SnakeDirection", "Direction: RIGHT");
                    snake.motion = new Vector(amount, 0);
                    break;
            }
            previousDirection = direction;
        }
    }
}
