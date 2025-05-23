module SuperEnemy {
    requires Common;
    requires CommonBullet;
    uses dk.sdu.cbse.common.bullet.BulletSPI;
    provides dk.sdu.cbse.common.services.IGamePluginService with dk.sdu.cbse.enemy.SuperEnemyPlugin;
    provides dk.sdu.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.enemy.SuperEnemyControlSystem,dk.sdu.cbse.enemy.SuperEnemySpawnSystem;
    provides dk.sdu.cbse.common.services.IPostEntityProcessingService with dk.sdu.cbse.enemy.SuperEnemyPostProcessingSystem;
}