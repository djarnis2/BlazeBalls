package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class AsteroidSpawnSystem implements IEntityProcessingService {
    private static final float SPAWN_TIMER = 10.0f;


    @Override
    public void process(GameData gameData, World world) {
        float delta = gameData.getDelta();
        Float clock = (Float) gameData.getData("asteroidSpawnTimer");
        if (clock == null) {
            clock = 0.0f;
        }

        clock += delta;

        if (clock > SPAWN_TIMER) {
            clock = 0.0f;
            world.addEntity(AsteroidFactory.createAsteroid(
                    gameData,
                    3,
                    (float)Math.random() * (gameData.getDisplayWidth() - 50),
                    (float)Math.random() * (gameData.getDisplayHeight() - 50)));
        }
        gameData.setData("asteroidSpawnTimer", clock);

    }
}
