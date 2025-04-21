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

        // Add entities to the world
        enemy = createEnemySpaceShip(gameData);
        world.addEntity(enemy);

    }

    private Entity createEnemySpaceShip(GameData gameData) {
        int randomX = (int)(Math.random() * (gameData.getDisplayWidth()- 50));
        int randomY = (int)(Math.random() * (gameData.getDisplayHeight()- 50));

        Entity enemySpaceShip = new EnemySpaceShip();
        enemySpaceShip.setPolygonCoordinates(-15,-15,30,0,-15,15);
        enemySpaceShip.setX((double) gameData.getDisplayHeight() /2);
        enemySpaceShip.setY((double) gameData.getDisplayWidth() /2);
        enemySpaceShip.setRadius(20);
        enemySpaceShip.setX(randomX);
        enemySpaceShip.setY(randomY);
        return enemySpaceShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        if (enemy != null) {
            world.removeEntity(enemy);
        }
    }

}
