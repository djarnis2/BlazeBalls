module dk.sdu.cbse.playersystem {
    uses dk.sdu.cbse.common.bullet.BulletSPI;
    requires dk.sdu.cbse.common;
    requires dk.sdu.cbse.common.bullet;
    provides dk.sdu.cbse.common.services.IGamePluginService with dk.sdu.cbse.playersystem.PlayerPlugin;
    provides dk.sdu.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.playersystem.PlayerControlSystem;

}