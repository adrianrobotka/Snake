package com.teamapk.brick;

import java.util.ArrayList;

/**
 * Keep models in motion with their motion vector
 */
public final class MotionModifier extends Modifier {
    /**
     * Get all model
     */
    private static final ArrayList<Model> models = Storage.getInstance().getModels();

    @Override
    public void doRound() {
        for (Model model : models) {
            model.position = model.position.add(model.motion);
        }
    }
}
