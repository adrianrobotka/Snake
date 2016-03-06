package com.teamapk.snake.controller;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.TextView;
import com.beesmarter.teamapk.snake.R;
import com.teamapk.snake.model.Snake;
import com.teamapk.snake.view.GameDrawer;

public final class GameActivity extends Activity {
    private static final String LOGTAG = GameActivity.class.getSimpleName();
    private GameDrawer drawer;
    private AppController controller = AppController.getInstance();

    // Helper vars
    private float screenWidth, screenHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;

        drawer = (GameDrawer) findViewById(R.id.gameDrawer);
        controller.setDrawerCallback(new Runnable() {
            @Override
            public void run() {
                drawer.post(new Runnable() {
                    @Override
                    public void run() {
                        drawer.invalidate();
                    }
                });
            }
        });

        controller.setGameOverCallback(new Runnable() {
            @Override
            public void run() {
                Log.i(LOGTAG, "Game over");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setContentView(R.layout.gameover);
                        TextView tv = (TextView) findViewById(R.id.gameoverLabel);
                        tv.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Wanted.ttf"));
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        controller.start();
        Log.i(LOGTAG, "AppController.start()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        controller.stop();
        Log.i(LOGTAG, "AppController.stop()");
    }

    @Override
    public synchronized boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN)
            return false;

        float height = event.getY();
        float width = event.getX();

        Snake.SnakeDirection direction;
        if (width < screenWidth / 3) {
            direction = Snake.SnakeDirection.LEFT;
        } else if (width > (screenWidth / 3) * 2) {
            direction = Snake.SnakeDirection.RIGHT;
        } else if (height < screenHeight / 2) {
            direction = Snake.SnakeDirection.UP;
        } else {
            direction = Snake.SnakeDirection.DOWN;
        }

        Snake.getInstance().setDirection(direction);
        return false;
    }
}
