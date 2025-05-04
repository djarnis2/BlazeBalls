package dk.sdu.cbse.bulletsystem;

import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

import java.util.ArrayList;
import java.util.List;

public class BulletControlSystem implements IEntityProcessingService {
    private static final float DEFAULT_BULLET_SPEED = 400f;

    @Override
    public void process(GameData gameData, World world) {
        List<Entity> deleteBullets = new ArrayList<>();


        for (Entity bullet : world.getEntities(Bullet.class)) {
            float delta = gameData.getDelta();
            float speed = DEFAULT_BULLET_SPEED;

            if (bullet.getData("speed") != null) {
                speed = (float) bullet.getData("speed");
            }

            float radians = (float) Math.toRadians(bullet.getRotation());
            float changeX = (float) Math.cos(radians) * speed * delta;
            float changeY = (float) Math.sin(radians) * speed * delta;
            bullet.setX(bullet.getX() + changeX);
            bullet.setY(bullet.getY() + changeY);

            if (bullet.getX() < 0) {
                deleteBullets.add(bullet);
            }

            if (bullet.getX() > (gameData.getDisplayWidth())) {
                deleteBullets.add(bullet);

            }

            if (bullet.getY() < 0) {
                deleteBullets.add(bullet);

            }

            if (bullet.getY() > (gameData.getDisplayHeight())) {
                deleteBullets.add(bullet);

            }
        }
        deleteBullets.forEach(world::removeEntity);

    }
}
