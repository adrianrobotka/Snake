package com.teamapk.snake.controller;

import com.teamapk.brick.Modifier;
import com.teamapk.brick.util.GameOverException;
import com.teamapk.snake.Config;
import com.teamapk.snake.model.AppStorage;
import com.teamapk.snake.model.Snake;

import static com.teamapk.brick.util.CollisionDetector.collides;

public class SnakeFoodCollisionModifier extends Modifier {

    @Override
    public void doRound() throws GameOverException {
        Snake snake = Snake.getInstance();
        if (collides(snake.position, snake.metrics, AppStorage.food.position, AppStorage.food.metrics)) {
            AppStorage.food = null;
            snake.length += Config.SNAKE_LENGTH_INCREMENT;
        }
    }
}
