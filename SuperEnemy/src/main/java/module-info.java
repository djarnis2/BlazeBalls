import dk.sdu.cbse.common.services.BulletSPI;

module SuperEnemy {
    requires Common;
    requires CommonBullet;
    uses BulletSPI;
    provides dk.sdu.cbse.common.services.IGamePluginService with dk.sdu.cbse.superenemy.SuperEnemyPlugin;
    provides dk.sdu.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.superenemy.SuperEnemyControlSystem,dk.sdu.cbse.superenemy.SuperEnemySpawnSystem;
    provides dk.sdu.cbse.common.services.IPostEntityProcessingService with dk.sdu.cbse.superenemy.SuperEnemyPostProcessingSystem;
}