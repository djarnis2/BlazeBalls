module Bullet {
    exports dk.sdu.cbse.bulletsystem;
    requires Common;
    requires CommonBullet;
    provides dk.sdu.cbse.common.services.IGamePluginService with dk.sdu.cbse.bulletsystem.BulletPlugin;
    provides dk.sdu.cbse.common.services.BulletSPI with dk.sdu.cbse.bulletsystem.NormalBullet;
    provides dk.sdu.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.bulletsystem.BulletControlSystem;
}