module Player {
    uses dk.sdu.cbse.common.services.BulletSPI;
    requires Common;
    requires CommonBullet;
    provides dk.sdu.cbse.common.services.IGamePluginService with dk.sdu.cbse.playersystem.PlayerPlugin;
    provides dk.sdu.cbse.common.services.IEntityProcessingService with dk.sdu.cbse.playersystem.PlayerControlSystem;
    provides dk.sdu.cbse.common.services.IPostEntityProcessingService with dk.sdu.cbse.playersystem.PlayerPostProcessingSystem;

}