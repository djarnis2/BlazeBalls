package dk.sdu.cbse.playersystem;

import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.BulletSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class PlayerControlSystem implements IEntityProcessingService {
    private static final float ROTATION_SPEED = 180f; // angle degrees per second
    private static final float MOVEMENT_SPEED = 200f; // units per second
    private static final float BULLET_COOLDOWN = 0.5f;

    @Override
    public void process(GameData gameData, World world) {
        float delta = gameData.getDelta();

        for (Entity player : world.getEntities(Player.class)) {
            if (player.getData("bulletCooldown") == null) {
                player.setData("bulletCooldown", 0f);
            }

            float bulletCooldown = (float) player.getData("bulletCooldown");
            bulletCooldown -= delta;

            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                player.setRotation(player.getRotation() - ROTATION_SPEED * delta);
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                player.setRotation(player.getRotation() + ROTATION_SPEED * delta);
            }
            if (gameData.getKeys().isDown(GameKeys.UP)) {
                float radians = (float) Math.toRadians(player.getRotation());
                float changeX = (float)Math.cos(radians) * MOVEMENT_SPEED * delta;
                float changeY = (float)Math.sin(radians) * MOVEMENT_SPEED * delta;
                player.setX(player.getX() + changeX);
                player.setY(player.getY() + changeY);
            }
            if (gameData.getKeys().isDown(GameKeys.SPACE) && bulletCooldown <= 0f) {
                getBulletSPIs()
                        .stream()
                        .filter(spi -> spi.getType().equals("normal"))
                        .findFirst()
                        .ifPresent(spi -> world.addEntity(spi.createBullet(player, gameData)));

                bulletCooldown = BULLET_COOLDOWN;
            }

            player.setData("bulletCooldown", bulletCooldown);

            // Don't let player go out of bounds
            if (player.getX() < 0) {
                player.setX(0);
            }

            if (player.getX() > gameData.getDisplayWidth()) {
                player.setX(gameData.getDisplayWidth());
            }

            if (player.getY() < 0) {
                player.setY(0);
            }

            if (player.getY() > gameData.getDisplayHeight()) {
                player.setY(gameData.getDisplayHeight());
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(toList());
    }
}
