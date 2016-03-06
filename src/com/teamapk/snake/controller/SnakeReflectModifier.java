package com.teamapk.snake.controller;

import com.teamapk.brick.Modifier;
import com.teamapk.brick.Vector;
import com.teamapk.brick.util.GameOverException;
import com.teamapk.snake.Config;
import com.teamapk.snake.model.Snake;

public class SnakeReflectModifier extends Modifier {


    @Override
    public void doRound() throws GameOverException {
        Snake snake = Snake.getInstance();
        Vector sPositionHigh = snake.position.add(snake.metrics);
        Vector sPositionLow = snake.position;

        if (sPositionHigh.getY() >= Config.HEIGHT) {
            // Upper crash
            snake.addBreak(new Vector(sPositionLow.getX(), Config.HEIGHT));
            snake.position = new Vector(sPositionLow.getX(), 1);
        } else if (sPositionLow.getY() <= 0) {
            // Lower crash
            snake.addBreak(new Vector(sPositionLow.getX(), 0));
            snake.position = new Vector(sPositionLow.getX(), Config.HEIGHT - 10);
        } else if (sPositionLow.getX() <= 0) {
            // Left crash
            snake.addBreak(new Vector(0, sPositionLow.getY()));
            snake.position = new Vector(Config.WIDTH - snake.metrics.getY(), sPositionLow.getY());
        } else if (sPositionHigh.getX() >= Config.WIDTH) {
            // Right crash
            snake.addBreak(new Vector(Config.WIDTH, sPositionLow.getY()));
            snake.position = new Vector(1, sPositionLow.getY());
        }
    }
}
