package dk.sdu.cbse.collisionsystem;

import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.superenemy.SuperEnemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.Buffer;

import static org.junit.jupiter.api.Assertions.*;

public class TestCollisionDetector {
    CollisionDetector collisionDetector;
    GameData gameData;
    World world;
    Entity bullet;
    Entity superEnemy;

    @BeforeEach
    void setUp() {
        collisionDetector = new CollisionDetector();
        gameData = new GameData();
        world = new World();
        bullet = new Bullet();
        superEnemy = new SuperEnemy();
    }

    @Test
    void TestBulletCollidesWithSuperEnemy() {
        bullet.setX(100);
        bullet.setY(100);
        bullet.setRadius(2);
        world.addEntity(bullet);
        superEnemy.setX(100);
        superEnemy.setY(100);
        superEnemy.setRadius(30);
        world.addEntity(superEnemy);

        collisionDetector.process(gameData,world);

        assertEquals(superEnemy, bullet.getData("collidesWith"), "Assumes bullet has collision superEnemy in getData map");
        assertEquals(bullet, superEnemy.getData("collidesWith"), "Assumes superenemy has collision bullet in getData map" );

    }

    @Test
    void TestBulletDoesNotCollidesWithSuperEnemy() {
        bullet.setX(100);
        bullet.setY(100);
        bullet.setRadius(2);
        world.addEntity(bullet);
        superEnemy.setX(20);
        superEnemy.setY(20);
        superEnemy.setRadius(30);
        world.addEntity(superEnemy);

        collisionDetector.process(gameData,world);

        assertNotEquals(superEnemy, bullet.getData("collidesWith"), "Assumes bullet has NO collision superEnemy in getData map");
        assertNotEquals(bullet, superEnemy.getData("collidesWith"), "Assumes superenemy NO has collision bullet in getData map" );

    }

}
