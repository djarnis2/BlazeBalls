package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

public class EnemySpawnSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        Float enemySpawnTimer = (Float) gameData.getData("enemySpawnTimer");
        if (enemySpawnTimer == null) {
            enemySpawnTimer = 0.0f;
        }

        enemySpawnTimer += gameData.getDelta();

        if (enemySpawnTimer > 3.0f) {
            enemySpawnTimer = 0.0f;
            Entity newEnemy = createEnemySpaceShip(gameData);
            world.addEntity(newEnemy);

        }
        gameData.setData("enemySpawnTimer", enemySpawnTimer);

    }

    private Entity createEnemySpaceShip(GameData gameData) {
        int randomX = (int) (Math.random() * (gameData.getDisplayWidth() - 50));
        int randomY = (int) (Math.random() * (gameData.getDisplayHeight() - 50));

        Entity enemy = new EnemySpaceShip();
        enemy.setPolygonCoordinates(-15, -15, 30, 0, -15, 15);
        enemy.setX(randomX);
        enemy.setY(randomY);
        enemy.setRadius(20);
        enemy.setData("shootCooldown", 0f);
        enemy.setData("dirCooldown", 0f);
        return enemy;
    }

}
