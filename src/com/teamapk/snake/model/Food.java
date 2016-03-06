package com.teamapk.snake.model;

import com.teamapk.brick.Model;
import com.teamapk.brick.Vector;
import com.teamapk.brick.util.CollisionDetector;
import com.teamapk.snake.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Food model
 */
public final class Food extends Model {

    private static final Random random = new Random();

    public Food() {
        metrics = new Vector(Config.FOOD_WIDTH, Config.FOOD_WIDTH, 1);
        motion = new Vector(0, 0);
        position = generatePosition();
    }

    /**
     * Generate random position
     *
     * @return random position
     */
    private Vector generatePosition() {
        int x, y, z;
        Vector a, b;
        Snake snake = Snake.getInstance();
        generate:
        while (true) {
            x = random.nextInt(Config.WIDTH-Config.FOOD_WIDTH);
            y = random.nextInt(Config.HEIGHT-Config.FOOD_WIDTH);
            z = random.nextInt(Config.NUMBER_OF_LEVELS);

            for (int i = 0; i < snake.getSnakeComponentCount() - 1; ++i) {
                Vector[] component = snake.getSnakeComponentAsModel(i);
                if (CollisionDetector.collides(component[0], component[1], new Vector(x, y, z), metrics)) // TODO sometimes one parameter is null
                    continue generate; // Generates new position
            }
            break;
        }
        return new Vector(x, y, z);

    }
}
