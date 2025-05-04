package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;


public class EnemySpaceShipPlugin implements IGamePluginService {

    private Entity enemy;

    public EnemySpaceShipPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        // No enemies are spawn here
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove all enemy entities
        world.getEntities(EnemySpaceShip.class)
                .forEach(world::removeEntity);
    }
}
