module dk.sdu.cbse.playerbulletsystem {
    exports dk.sdu.cbse.playerbulletsystem;
    requires dk.sdu.cbse.common;
    requires dk.sdu.cbse.common.bullet;
    provides dk.sdu.cbse.common.services.IGamePluginService with dk.sdu.cbse.playerbulletsystem.BulletPlugin;
    provides dk.sdu.cbse.common.bullet.BulletSPI with dk.sdu.cbse.playerbulletsystem.PlayerBullet;
    provides dk.sdu.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.playerbulletsystem.BulletControlSystem;
}