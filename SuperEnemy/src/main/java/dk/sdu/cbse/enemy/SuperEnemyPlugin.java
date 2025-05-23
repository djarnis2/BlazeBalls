package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

public class SuperEnemyPlugin implements IGamePluginService {
    private Entity superEnemy;



    @Override
    public void start(GameData gameData, World world) {
        Entity enemy = SuperEnemyFactory.createSuperEnemy(gameData);
        world.addEntity(enemy);
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove all enemy entities
        world.getEntities(SuperEnemy.class)
                .forEach(world::removeEntity);
    }
}
