package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

/**
 * Spawns a new enemy every interval set in {@link #SPAWN_INTERVAL}.
 * The enemy is created with {@code EnemyFactory.createEnemy(gameData)}.
 */
public class EnemySpawnSystem implements IEntityProcessingService {

    private static final float SPAWN_INTERVAL = 15.0f;

    @Override
    public void process(GameData gameData, World world) {
        Float clock = (Float) gameData.getData("enemySpawnTimer");
        if (clock == null) {
            clock = 0.0f;
        }

        clock += gameData.getDelta();

        if (clock > SPAWN_INTERVAL) {
            clock = 0.0f;
            Entity newEnemy = EnemyFactory.createEnemy(gameData);
            world.addEntity(newEnemy);

        }
        gameData.setData("enemySpawnTimer", clock);

    }



}
