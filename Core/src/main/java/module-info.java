module dk.sdu.cbse.main {
    requires dk.sdu.cbse.common;
    requires dk.sdu.cbse.common.bullet;
    requires javafx.graphics;

    opens dk.sdu.cbse.main to javafx.graphics;

    uses dk.sdu.cbse.common.services.IGamePluginService;
    uses dk.sdu.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.cbse.common.services.IPostEntityProcessingService;
}