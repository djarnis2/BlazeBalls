package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class AsteroidControlSystem implements IEntityProcessingService {
    private static final float ASTEROID_SPEED = 100f;

    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            float delta = gameData.getDelta();
            float speed = asteroid.getData("speed") != null ? (float) asteroid.getData("speed") : ASTEROID_SPEED;
            float angleRadians = (float) Math.toRadians(asteroid.getRotation());
            float dx = (float) Math.cos(angleRadians) * speed * delta;
            float dy = (float) Math.sin(angleRadians) * speed * delta;

            asteroid.setX(asteroid.getX() + dx);
            asteroid.setY(asteroid.getY() + dy);

            if (asteroid.getX() < 0) {
                asteroid.setX(gameData.getDisplayWidth());
            }

            if (asteroid.getX() > (gameData.getDisplayWidth())) {
                asteroid.setX(0);
            }

            if (asteroid.getY() < 0) {
                asteroid.setY(gameData.getDisplayHeight());
            }

            if (asteroid.getY() > (gameData.getDisplayHeight())) {
                asteroid.setY(0);
            }
        }
    }
}
