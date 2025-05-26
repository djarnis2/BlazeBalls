package dk.sdu.cbse.superenemy;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class SuperEnemySpawnSystem implements IEntityProcessingService {
    private static final float SPAWN_INTERVAL = 30.0f;

    @Override
    public void process(GameData gameData, World world) {
        Float clock = (Float) gameData.getData("superEnemySpawnTimer");
        if (clock == null) {
            clock = 0.0f;
        }

        clock += gameData.getDelta();

        if (clock > SPAWN_INTERVAL) {
            clock = 0.0f;
            Entity newEnemy = SuperEnemyFactory.createSuperEnemy(gameData);
            world.addEntity(newEnemy);

        }
        gameData.setData("superEnemySpawnTimer", clock);
    }
}