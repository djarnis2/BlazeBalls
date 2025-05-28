import dk.sdu.cbse.bulletsystem.NormalBullet;
import dk.sdu.cbse.common.services.BulletSPI;

module Bullet {
    exports dk.sdu.cbse.bulletsystem;
    requires Common;
    requires CommonBullet;
    provides dk.sdu.cbse.common.services.IGamePluginService with dk.sdu.cbse.bulletsystem.BulletPlugin;
    provides BulletSPI with NormalBullet;
    provides dk.sdu.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.bulletsystem.BulletControlSystem;
}