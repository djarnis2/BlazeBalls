module EnemyBullet {
    exports dk.sdu.cbse.enemybulletsystem;
    requires Common;
    requires CommonBullet;
    provides dk.sdu.cbse.common.bullet.BulletSPI with dk.sdu.cbse.enemybulletsystem.EnemyBullet;
}
