package com.teamapk.snake.model;

import com.teamapk.brick.Storage;

/**
 * Local model storage
 */
public final class AppStorage extends Storage {
    /**
     * Food' list
     */
    public static Food food = null;
    /**
     * Instance of itself
     */
    protected static AppStorage storage = new AppStorage();

    /**
     * Get instance of itself
     *
     * @return instance
     */
    public static AppStorage getInstance() {
        return storage;
    }
}
