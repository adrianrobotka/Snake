package com.teamapk.snake.controller;


import android.util.Log;
import com.teamapk.brick.Controller;
import com.teamapk.brick.util.ProcessIndicator;
import com.teamapk.snake.model.AppStorage;
import com.teamapk.snake.model.Snake;
import com.teamapk.snake.view.FoodDrawer;
import com.teamapk.snake.view.SnakeDrawer;

public final class AppController extends Controller {
    private static final String LOGTAG = AppController.class.getSimpleName();
    private static AppController instance = null;
    private boolean gameEnded = true;

    private AppController() {
    }

    public static AppController getInstance() {
        if (instance != null)
            return instance;
        else
            return instance = new AppController();
    }

    @Override
    public void init(ProcessIndicator indicator) throws IllegalStateException {
        super.init(indicator);
        Log.d(LOGTAG, "AppController.init()");
    }

    @Override
    public void start() {
        super.start();
        gameEnded = false;
    }

    /**
     * It will be true on start and after gameOver
     *
     * @return gameEnded boolean
     */
    public boolean isGameEnded() {
        return gameEnded;
    }

    @Override
    protected void createModels() {
        Snake.init();
    }

    @Override
    protected void createDrawers() {
        new SnakeDrawer();
        new FoodDrawer();
    }

    @Override
    protected void createModifiers() {
        // Objects add their own to the Storage in their constructor
        new SnakeDirectionModifier();
        new SnakeReflectModifier();
        new SnakeTaleModifier();
        new FoodGeneratorModifier();
        new SnakeCollidesModifier();
        new SnakeFoodCollisionModifier();
    }

    @Override
    protected void onGameOver() {
        gameEnded = true;
        super.onGameOver();
    }
}
