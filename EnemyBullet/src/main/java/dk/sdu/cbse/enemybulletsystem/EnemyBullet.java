package dk.sdu.cbse.enemybulletsystem;

import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;

public class EnemyBullet implements BulletSPI {

    @Override
    public String getType() {
        return "enemy";
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();
//        bullet.setPolygonCoordinates(3, -3, 3, 3, -3, 3, -3, -3);
        bullet.setPolygonCoordinates(
                0, -12,  // Top spids
                3, -3,   // Mellem top og højre spids
                12, -3,  // Højre spids
                6, 1.5,  // Mellem højre og nedre højre spids
                7.5, 9,  // Nedre højre spids
                0, 4.5,  // Mellem nedre højre og nedre venstre spids
                -7.5, 9, // Nedre venstre spids
                -6, 1.5, // Mellem nedre venstre og venstre spids
                -12, -3, // Venstre spids
                -3, -3   // Mellem venstre og top spids
        );
        double changeX = Math.cos(Math.toRadians(shooter.getRotation()));
        double changeY = Math.sin(Math.toRadians(shooter.getRotation()));
        bullet.setX(shooter.getX() + changeX * 20);
        bullet.setY(shooter.getY() + changeY * 20);
        bullet.setRotation(shooter.getRotation());
        bullet.setRadius(1);
        return bullet;
    }
}
