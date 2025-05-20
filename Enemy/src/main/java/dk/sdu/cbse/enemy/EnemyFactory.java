package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;

public class EnemyFactory {

    public static Entity createEnemy(GameData gameData) {
        int randomX = (int)(Math.random() * (gameData.getDisplayWidth()- 50));
        int randomY = (int)(Math.random() * (gameData.getDisplayHeight()- 50));

        Entity enemySpaceShip = new EnemySpaceShip();
        enemySpaceShip.setPolygonCoordinates(-15,-15,30,0,-15,15);
        enemySpaceShip.setX((double) gameData.getDisplayHeight() /2);
        enemySpaceShip.setY((double) gameData.getDisplayWidth() /2);
        enemySpaceShip.setRadius(20);
        enemySpaceShip.setX(randomX);
        enemySpaceShip.setY(randomY);
        enemySpaceShip.setData("shootCooldown", 0f);
        enemySpaceShip.setData("dirCooldown", 0f);
        enemySpaceShip.setData("type", "enemy");
        enemySpaceShip.setData("health", 2);
        return enemySpaceShip;
    }
}
