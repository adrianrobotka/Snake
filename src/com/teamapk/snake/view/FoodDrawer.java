package com.teamapk.snake.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.teamapk.brick.Drawer;
import com.teamapk.snake.Config;
import com.teamapk.snake.model.AppStorage;
import com.teamapk.snake.model.Food;

/**
 * Draw the racket
 */
public class FoodDrawer extends Drawer {


    /**
     * Draw with this paint
     */
    private Paint paint;

    /**
     * Create Drawer and add itself to the Storage
     */
    public FoodDrawer() {

        paint = new Paint();
        paint.setColor(Config.COLOR_FOOD);
        paint.setStrokeWidth(0);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void draw(Canvas canvas) {
        Food food = AppStorage.food;
        if (food != null) {
            int l = food.position.getX(),
                    t = food.position.getY(),
                    r = food.metrics.getX() + l,
                    b = food.metrics.getY() + t;
            canvas.drawOval(new RectF(l, t, r, b), paint);
        }
    }
}
