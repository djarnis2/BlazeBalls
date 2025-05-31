module EnemyBullet {
    exports dk.sdu.cbse.enemybulletsystem;
    requires Common;
    requires CommonBullet;
    provides dk.sdu.cbse.common.services.BulletSPI with dk.sdu.cbse.enemybulletsystem.EnemyBullet;
}
