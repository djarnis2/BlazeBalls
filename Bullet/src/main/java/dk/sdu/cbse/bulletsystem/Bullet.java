package dk.sdu.cbse.bulletsystem;

import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;

public class Bullet implements BulletSPI {
    private final String type = "normal";

    @Override
    public String getType() {
        return type;
    }
    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new dk.sdu.cbse.common.bullet.Bullet();
        bullet.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
        double changeX = Math.cos(Math.toRadians(shooter.getRotation()));
        double changeY = Math.sin(Math.toRadians(shooter.getRotation()));
        bullet.setX(shooter.getX() + changeX * 20);
        bullet.setY(shooter.getY() + changeY * 20);
        bullet.setRotation(shooter.getRotation());
        bullet.setRadius(1);
        return bullet;
    }
}
