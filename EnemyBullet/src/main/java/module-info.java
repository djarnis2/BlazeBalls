module EnemyBullet {
    requires Common;
    requires CommonBullet;
    provides dk.sdu.cbse.common.services.BulletSPI with dk.sdu.cbse.enemybulletsystem.EnemyBullet;
}
