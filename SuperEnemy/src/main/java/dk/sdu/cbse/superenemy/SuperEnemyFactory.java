package dk.sdu.cbse.superenemy;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;

public class SuperEnemyFactory {
    public static Entity createSuperEnemy(GameData gameData) {
        int randomX = (int)(gameData.getDisplayWidth()- 50);
        int randomY = (int)(gameData.getDisplayHeight()- 50);

        Entity superEnemy = new SuperEnemy();
        superEnemy.setPolygonCoordinates(-30,-30,60,0,-30,30);
        superEnemy.setRadius(30);
        superEnemy.setX(randomX);
        superEnemy.setY(randomY);
        superEnemy.setRotation(235);
        superEnemy.setData("shootCooldown", 0f);
        superEnemy.setData("dirCooldown", 0f);
        superEnemy.setData("type", "SuperEnemy");
        superEnemy.setData("health", 5);

        return superEnemy;
    }
}