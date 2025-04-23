import dk.sdu.cbse.bulletsystem.Bullet;

module Bullet {
    exports dk.sdu.cbse.bulletsystem;
    requires Common;
    requires CommonBullet;
    provides dk.sdu.cbse.common.services.IGamePluginService with dk.sdu.cbse.bulletsystem.BulletPlugin;
    provides dk.sdu.cbse.common.bullet.BulletSPI with Bullet;
    provides dk.sdu.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.bulletsystem.BulletControlSystem;
}