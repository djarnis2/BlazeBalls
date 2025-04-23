module Enemy {
    requires Common;
    requires CommonBullet;
    uses dk.sdu.cbse.common.bullet.BulletSPI;
    provides dk.sdu.cbse.common.services.IGamePluginService with dk.sdu.cbse.enemy.EnemySpaceShipPlugin;
    provides dk.sdu.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.enemy.EnemySpaceShipControlSystem, dk.sdu.cbse.enemy.EnemySpawnSystem;

}