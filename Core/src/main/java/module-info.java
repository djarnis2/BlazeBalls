module Core {
    requires Common;
    requires javafx.graphics;
    requires spring.context;
    requires commons.logging;

    opens dk.sdu.cbse.main to javafx.graphics, spring.core, spring.context, spring.beans;

    uses dk.sdu.cbse.common.services.IGamePluginService;
    uses dk.sdu.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.cbse.common.services.IPostEntityProcessingService;
    uses dk.sdu.cbse.common.services.IScoreService;
}