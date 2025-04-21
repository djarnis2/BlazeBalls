package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class EnemySpaceShipControlSystem implements IEntityProcessingService {


    @Override
    public void process(GameData gameData, World world) {

        float delta = gameData.getDelta();

        for (Entity enemy : world.getEntities(EnemySpaceShip.class)) {
            // instantiating cooldowns if null
            if (enemy.getData("shootCooldown") == null) enemy.setData("shootCooldown", 0f);
            if (enemy.getData("dirCooldown") == null) enemy.setData("dirCooldown", 0f);

            float shootCooldown = (float) enemy.getData("shootCooldown");
            float dirCooldown = (float) enemy.getData("dirCooldown");

            shootCooldown -= delta;
            dirCooldown -= delta;
            // for choosing between rotations left or right, move or shoot
            int rand = (int) (Math.random() * 4 + 1);
            int randDir = (int) (Math.random() * 100 + 1);
            if (dirCooldown <= 0) {
                if (rand == 1) {
                    enemy.setRotation(enemy.getRotation() - randDir);
                } else if (rand == 2) {
                    enemy.setRotation(enemy.getRotation() + randDir);
                } else if (rand == 3) {
                    // Do nothing
                }
                dirCooldown = 2.0f;
            }


            if (rand == 4) {
                if (shootCooldown <= 0) {
                    getBulletSPIs()
                            .stream()
                            .filter(spi -> spi.getType().equals("enemy"))
                            .findFirst()
                            .ifPresent(
                            spi -> {
                                world.addEntity(spi.createBullet(enemy, gameData));
                            }
                    );

                    shootCooldown = 1.0f;
                }

            }
            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
            enemy.setX(enemy.getX() + changeX);
            enemy.setY(enemy.getY() + changeY);
            enemy.setData("shootCooldown", shootCooldown);
            enemy.setData("dirCooldown", dirCooldown);

            if (enemy.getX() < 1) {
                enemy.setRotation((enemy.getRotation() + 180) % 360);
            }

            if (enemy.getX() > (gameData.getDisplayWidth() - 1)) {
                enemy.setRotation((enemy.getRotation() + 180) % 360);
            }

            if (enemy.getY() < 1) {
                enemy.setRotation((enemy.getRotation() + 180) % 360);
            }

            if (enemy.getY() > (gameData.getDisplayHeight() - 1)) {
                enemy.setRotation((enemy.getRotation() + 180) % 360);
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {

        return ServiceLoader
                .load(BulletSPI.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(toList());
    }
}
