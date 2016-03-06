package com.teamapk.brick;

import java.util.ArrayList;

/**
 * Stores the models and run round of ModelModifiers and ModelObservers
 */
public class Storage {
    /**
     * Instance of itself
     */
    protected static Storage storage = new Storage();

    /**
     * Models' list
     */
    protected final ArrayList<Model> models = new ArrayList<>();

    /**
     * Model modifiers' list
     */
    protected final ArrayList<Modifier> modifiers = new ArrayList<>();

    /**
     * Model drawers' list
     */
    protected final ArrayList<Drawer> drawers = new ArrayList<>();

    /**
     * Get instance of itself
     *
     * @return instance
     */
    public static Storage getInstance() {
        return storage;
    }

    /**
     * Add a model to the storage. Model's constructor call this.
     *
     * @param model the Model object
     */
    public void addModel(Model model) {
        models.add(model);
    }

    /**
     * Add a Modifier to the storage. Modifier's constructor call this.
     *
     * @param modifier The modifier
     */
    protected void addModifier(Modifier modifier) {
        modifiers.add(modifier);
    }

    /**
     * Add a Drawer to the storage. Drawer's constructor call this.
     *
     * @param drawer The drawer
     */
    protected void addDrawer(Drawer drawer) {
        drawers.add(drawer);
    }

    /**
     * Clean the storage
     */
    protected void clean() {
        models.clear();
        drawers.clear();
        modifiers.clear();
    }

    /**
     * Get the models
     *
     * @return Model list
     */
    public ArrayList<Model> getModels() {
        return models;
    }

    /**
     * Get a specific model list
     *
     * @param assignedFrom Parent of the models
     * @return Specified models' list
     */
    public ArrayList<Model> getModels(Class assignedFrom) {
        ArrayList<Model> list = new ArrayList<>();

        for (Model model : models)
            if (model.getClass().isAssignableFrom(assignedFrom))
                list.add(model);

        return list;
    }

    /**
     * Get a specific model
     *
     * @param id Id of the models
     * @return Specified models' list
     */
    public Model getModel(String id) {
        for (Model model : models)
            if (model.id != null && model.id.equals(id))
                return model;

        return null;
    }
}
