package dk.sdu.cbse.bulletsystem;

import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import java.util.ArrayList;
import java.util.List;

public class BulletPostProcessingSystem implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        List<Entity> deleteBullets = new ArrayList<>();

        for (Entity bullet : world.getEntities(Bullet.class)) {
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
