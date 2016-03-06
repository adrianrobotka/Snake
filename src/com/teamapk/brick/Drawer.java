package com.teamapk.brick;

import android.graphics.Canvas;

/**
 * Draw a model to the canvas
 */
public abstract class Drawer {
    /**
     * Create Drawer and add itself to the Storage
     */
    public Drawer() {
        Storage.getInstance().addDrawer(this);
    }

    /**
     * Draw the model to the canvas
     */
    public abstract void draw(Canvas canvas);
}
