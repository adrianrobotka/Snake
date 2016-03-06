package com.teamapk.brick;

import com.teamapk.brick.util.GameOverException;

/**
 * Able to modify the models
 * <p>
 * A rule to do modifications.
 */
public abstract class Modifier {

    /**
     * Initialize modifier
     */
    public Modifier() {
        Storage.getInstance().addModifier(this);
    }

    /**
     * A method that is called frame by frame to do class' target
     */
    public abstract void doRound() throws GameOverException;
}
