module dk.sdu.cbse.enemybulletsystem {
    exports dk.sdu.cbse.enemybulletsystem;
    requires dk.sdu.cbse.common;
    requires dk.sdu.cbse.common.bullet;
    provides dk.sdu.cbse.common.bullet.BulletSPI with dk.sdu.cbse.enemybulletsystem.EnemyBullet;
    provides dk.sdu.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.enemybulletsystem.EnemyBulletControlSystem;
}
