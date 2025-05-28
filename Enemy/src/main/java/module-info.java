import dk.sdu.cbse.common.services.BulletSPI;

module Enemy {
    requires Common;
    requires CommonBullet;
    uses BulletSPI;
    provides dk.sdu.cbse.common.services.IGamePluginService with dk.sdu.cbse.enemy.EnemySpaceShipPlugin;
    provides dk.sdu.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.enemy.EnemySpaceShipControlSystem, dk.sdu.cbse.enemy.EnemySpawnSystem;
    provides dk.sdu.cbse.common.services.IPostEntityProcessingService with dk.sdu.cbse.enemy.EnemyPostProcessingSystem;
}