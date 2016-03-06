package com.teamapk.snake.controller;

import com.teamapk.brick.Modifier;
import com.teamapk.brick.util.GameOverException;
import com.teamapk.snake.model.AppStorage;
import com.teamapk.snake.model.Food;

public final class FoodGeneratorModifier extends Modifier {

    @Override
    public void doRound() throws GameOverException {
        if (AppStorage.food == null) {
            AppStorage.food = new Food();
        }
    }
}
