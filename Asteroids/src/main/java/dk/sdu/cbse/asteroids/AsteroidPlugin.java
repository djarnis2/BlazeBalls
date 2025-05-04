package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < 3; i++) {
            float x = (float) Math.random() * (gameData.getDisplayWidth() - 50);
            float y = (float) Math.random() * (gameData.getDisplayHeight() - 50);
            Entity asteroid = AsteroidFactory.createAsteroid(gameData, 3 , x, y);
            world.addEntity(asteroid);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.getEntities(Asteroid.class)
                .forEach(world::removeEntity);
    }
}
