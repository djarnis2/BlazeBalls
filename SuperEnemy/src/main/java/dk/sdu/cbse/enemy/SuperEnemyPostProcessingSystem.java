package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

public class SuperEnemyPostProcessingSystem implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(SuperEnemy.class)) {
            Entity entity = (Entity) enemy.getData("collidesWith");
            String type = (String) enemy.getData("collidesWithType");

            if (entity == null || type == null) continue; // Check if handled in other postProcessing module.

            enemy.setData("collidesWith", null);
            entity.setData("collidesWith", null);

            int health = (int) enemy.getData("health");

            switch (type) {
                case "bullet":
                    world.removeEntity(entity);
                    switch (health) {
                        case 1:
                            world.removeEntity(enemy);
                            break;

                        case 2:
                            enemy.setData("health", 1);
                            break;
                        case 3:
                            enemy.setData("health", 2);
                            break;
                        case 4:
                            enemy.setData("health", 3);
                            break;
                        case 5:
                            enemy.setData("health", 4);
                            break;
                    }
                    break;
                case "player":
                case "asteroid":
                    world.removeEntity(enemy);
                    world.removeEntity(entity);
                    break;
            }
        }
    }
}
