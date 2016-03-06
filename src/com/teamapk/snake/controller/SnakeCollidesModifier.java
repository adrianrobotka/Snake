package com.teamapk.snake.controller;

import android.util.Log;
import com.teamapk.brick.Modifier;
import com.teamapk.brick.Vector;
import com.teamapk.brick.util.CollisionDetector;
import com.teamapk.brick.util.GameOverException;
import com.teamapk.snake.model.Snake;

/***
 * Examines, whether the snake has collided itself.
 */
public class SnakeCollidesModifier extends Modifier {

    @Override
    public void doRound() throws GameOverException {
        Snake snake = Snake.getInstance();

        for (int i = 0; i < snake.getSnakeComponentCount()-2; ++i) {
            Vector[] component = snake.getSnakeComponentAsModel(i);
            if (CollisionDetector.collides(component[0], component[1], snake.position, snake.metrics)) {
                Log.d(getClass().getName(), "GAME OVER");
                throw new GameOverException();
            }
        }
    }
}
