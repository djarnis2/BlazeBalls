module EnemyBullet {
    exports dk.sdu.cbse.enemybulletsystem;
    requires Common;
    requires CommonBullet;
    provides dk.sdu.cbse.common.bullet.BulletSPI with dk.sdu.cbse.enemybulletsystem.EnemyBullet;
    provides dk.sdu.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.enemybulletsystem.EnemyBulletControlSystem;
}
