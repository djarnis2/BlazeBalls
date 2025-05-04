package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.Optional;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class EnemySpaceShipControlSystem implements IEntityProcessingService {
    private static final float ROTATION_MAX = 20f; // angle degrees per second
    private static final float MOVEMENT_SPEED = 200f; // units per second

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
            float randDir = (float) (Math.random() * ROTATION_MAX + 1);
            if (dirCooldown <= 0) {
                if (rand == 1) {
                    enemy.setRotation(enemy.getRotation() - randDir);
                } else if (rand == 2) {
                    enemy.setRotation(enemy.getRotation() + randDir);
                } else if (rand == 3) {
                    // Do nothing (but some future implementation possible)
                }
                dirCooldown = 0.15f;
            }

            // enemy shoots on rand == 4
            if (rand == 4) {
                if (shootCooldown <= 0) {
                    // optionally if enemy bullet is present, use this
                    Optional<BulletSPI> bulletSPI = getBulletSPIs()
                            .stream()
                            .filter(spi -> "enemy".equals(spi.getType()))
                            .findFirst();
                    // if no bullet of type enemy is found, fallback to DEFAULT_TYPE
                    if (bulletSPI.isEmpty()) {
                        bulletSPI = getBulletSPIs()
                                .stream()
                                .filter(spi -> BulletSPI.DEFAULT_TYPE.equals(spi.getType()))
                                .findFirst();
                    }

                    bulletSPI.ifPresent(spi -> world.addEntity(spi.createBullet(enemy, gameData)));
                    shootCooldown = 1.0f;
                }

            }
            float radians = (float) Math.toRadians(enemy.getRotation());
            float changeX = (float) Math.cos(radians) * MOVEMENT_SPEED * delta;
            float changeY = (float) Math.sin(radians) * MOVEMENT_SPEED * delta;
            enemy.setX(enemy.getX() + changeX);
            enemy.setY(enemy.getY() + changeY);
            enemy.setData("shootCooldown", shootCooldown);
            enemy.setData("dirCooldown", dirCooldown);

            if (enemy.getX() < 0) {
                enemy.setRotation((enemy.getRotation() + 180) % 360);
            }

            if (enemy.getX() > (gameData.getDisplayWidth())) {
                enemy.setRotation((enemy.getRotation() + 180) % 360);
            }

            if (enemy.getY() < 0) {
                enemy.setRotation((enemy.getRotation() + 180) % 360);
            }

            if (enemy.getY() > (gameData.getDisplayHeight())) {
                enemy.setRotation((enemy.getRotation() + 180) % 360);
            }
        }
    }

    private Collection<BulletSPI> getBulletSPIs() {

        return ServiceLoader
                .load(BulletSPI.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(toList());
    }
}
