import dk.sdu.cbse.common.services.BulletSPI;

module EnemyBullet {
    exports dk.sdu.cbse.enemybulletsystem;
    requires Common;
    requires CommonBullet;
    provides BulletSPI with dk.sdu.cbse.enemybulletsystem.EnemyBullet;
}
