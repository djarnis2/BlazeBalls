module dk.sdu.cbse.enemy {
    requires dk.sdu.cbse.common;
    requires dk.sdu.cbse.common.bullet;
    requires dk.sdu.cbse.enemybulletsystem;
    uses dk.sdu.cbse.common.bullet.BulletSPI;
    provides dk.sdu.cbse.common.services.IGamePluginService with dk.sdu.cbse.enemy.EnemySpaceShipPlugin;
    provides dk.sdu.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.enemy.EnemySpaceShipControlSystem, dk.sdu.cbse.enemy.EnemySpawnSystem;

}