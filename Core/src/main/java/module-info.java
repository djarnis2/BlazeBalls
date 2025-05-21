module Core {
    requires Common;
    requires CommonBullet;
    requires javafx.graphics;
    requires Enemy;
    requires SuperEnemy;


    opens dk.sdu.cbse.main to javafx.graphics;

    uses dk.sdu.cbse.common.services.IGamePluginService;
    uses dk.sdu.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.cbse.common.services.IPostEntityProcessingService;
}