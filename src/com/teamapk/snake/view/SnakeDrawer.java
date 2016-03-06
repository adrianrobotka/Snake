package com.teamapk.snake.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.teamapk.brick.Drawer;
import com.teamapk.brick.Model;
import com.teamapk.brick.Vector;
import com.teamapk.snake.Config;
import com.teamapk.snake.model.Snake;

import java.util.ArrayList;
import java.util.List;

/**
 * Draw the snake
 */
public class SnakeDrawer extends Drawer {

    /**
     * Draw with this paint
     */
    private Paint paint;

    /**
     * Create SnakeDrawer and add itself to the Storage
     */
    public SnakeDrawer() {

        paint = new Paint();
        paint.setColor(Config.COLOR_SNAKE);
        paint.setStrokeWidth(0);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void draw(Canvas canvas) {
        Snake snake = Snake.getInstance();
        for (int i = 0; i < snake.getSnakeComponentCount(); ++i) {
            Vector[] component = snake.getSnakeComponentAsModel(i);
            canvas.drawRect(
                    component[0].getX(),
                    component[0].getY(),
                    component[0].getX() + component[1].getX(),
                    component[0].getY() + component[1].getY(),
                    paint);
        }
    }
}
