package com.teamapk.brick;

import com.teamapk.brick.util.GameOverException;
import com.teamapk.brick.util.ProcessIndicator;

/**
 * Initialize storage and create model logic
 */
public abstract class Controller {
    protected Runnable drawerCallback = null;
    protected Runnable gameOverCallback = null;
    protected ProcessIndicator indicator = null;
    protected Thread gameThread;
    protected float fps = 60;

    protected boolean run = false;

    /**
     * Set FPS
     *
     * @param fps The FPS
     */
    public void setFps(float fps) {
        this.fps = fps;
    }

    /**
     * Set process status indicator
     *
     * @param indicator The indicator
     */
    public void setIndicator(ProcessIndicator indicator) {
        this.indicator = indicator;
    }

    /**
     * Async prepare app logic. Create models, drawers and modifiers
     */
    public void init() {
        init(null);
    }

    /**
     * Async prepare app logic. Create models, drawers and modifiers
     *
     * @param indicator Callback to update process status
     */
    public void init(ProcessIndicator indicator) throws IllegalStateException {
        run = false;

        if (indicator != null)
            this.indicator = indicator;

        // Create a worker thread for the model calculations
        new Thread(new Runnable() {
            @Override
            public void run() {
                Storage.getInstance().clean();

                createModels();
                setProcessPercentage(50);

                createDrawers();
                setProcessPercentage(70);

                createBaseModifiers();
                setProcessPercentage(80);

                createModifiers();
                run = true;
                setProcessPercentage(100);
            }
        }).start();
    }

    public void setDrawerCallback(Runnable drawerCallback) {
        this.drawerCallback = drawerCallback;
    }

    public void setGameOverCallback(Runnable gameOverCallback) {
        this.gameOverCallback = gameOverCallback;
    }

    /**
     * Called frame by frame to do model modifications
     */
    private void doRound() throws GameOverException {
        // Modify models
        for (Modifier modifier : Storage.getInstance().modifiers)
            modifier.doRound();

        // Draw models
        drawerCallback.run();
    }

    protected void onGameOver() {
        gameOverCallback.run();
    }

    protected void setGameThread() {
        gameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Set FPS (10 is a correction value)
                int waitPerFrame = (int) (1000 / fps - 10);

                // Reduce load
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // TODO Is it thread safe?
                long before, after;
                while (run) {
                    // TODO Is it worth?
                    before = System.currentTimeMillis();
                    try {
                        doRound();
                    } catch (GameOverException e) {
                        run = false;
                        onGameOver();
                        break;
                    }
                    after = System.currentTimeMillis();

                    long wait;
                    try {
                        wait = waitPerFrame - (after - before);

                        if (wait > 0)
                            Thread.sleep(wait);// Could lead exception
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void start() {
        // Start a worker thread for the model calculations
        run = true;
        setGameThread();
        gameThread.start();
    }

    public void stop() {
        run = false;
    }

    /**
     * Callback to set init process percentage
     *
     * @param percentage Process' percentage
     */
    protected void setProcessPercentage(final int percentage) {
        if (indicator != null)
            indicator.setProcessPercentage(percentage);
    }

    /**
     * Create systematical modifiers
     */
    private void createBaseModifiers() {
        new MotionModifier();
    }

    /**
     * Create models
     */
    protected abstract void createModels();

    /**
     * Create drawers
     */
    protected abstract void createDrawers();

    /**
     * Create model modifiers
     */
    protected abstract void createModifiers();
}
