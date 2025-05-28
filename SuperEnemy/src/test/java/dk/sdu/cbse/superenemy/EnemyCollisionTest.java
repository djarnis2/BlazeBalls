package dk.sdu.cbse.superenemy;

import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnemyCollisionTest {
    private SuperEnemyPostProcessingSystem superEnemyPostProcessingSystem = new SuperEnemyPostProcessingSystem();
    private Entity superEnemy;
    private World world;
    private GameData gameData;


    @BeforeEach
    void setup() {
        gameData = new GameData();
        world = new World();
        superEnemy = new SuperEnemy();
        superEnemy.setData("health", 1);
        world.addEntity(superEnemy);
    }

    @Test
    void TestEnemyExistBeforeCollision() {
        superEnemyPostProcessingSystem.process(gameData, world);
        assertTrue(superEnemy.getData("health").equals(1), "SuperEnemy should have health that equals 1 before collision");
    }

    @Test
    void TestSuperEnemyIsDestroyedAtCollision() {

        superEnemy.setData("collidesWith", new Bullet());
        superEnemy.setData("collidesWithType", "bullet");

        superEnemyPostProcessingSystem.process(gameData, world);

        assertFalse(world.getEntities().contains(superEnemy), "SuperEnemy should be removed from the world after collision");

    }


}
