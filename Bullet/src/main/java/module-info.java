module Bullet {
    exports dk.sdu.cbse.playerbulletsystem;
    requires Common;
    requires CommonBullet;
    provides dk.sdu.cbse.common.services.IGamePluginService with dk.sdu.cbse.playerbulletsystem.BulletPlugin;
    provides dk.sdu.cbse.common.bullet.BulletSPI with dk.sdu.cbse.playerbulletsystem.PlayerBullet;
    provides dk.sdu.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.playerbulletsystem.BulletControlSystem;
}