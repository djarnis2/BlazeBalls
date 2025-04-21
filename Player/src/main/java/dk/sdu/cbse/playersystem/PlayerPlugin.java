package dk.sdu.cbse.playersystem;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {
    private Player player;

    /**
     * Required public No-arg-constructor for ServiceLoader discovery.
     */
    public PlayerPlugin() {}


    @Override
    public void start(GameData gameData, World world) {
        player = createPlayerEntity(gameData);
        world.addEntity(player);
    }

    private Player createPlayerEntity(GameData gameData) {
        Player playerEntity = new Player();
        playerEntity.setPolygonCoordinates(-5,-5,10,0,-5,5);
        playerEntity.setX((double) gameData.getDisplayHeight() /2);
        playerEntity.setY((double) gameData.getDisplayWidth() /2);
        playerEntity.setRadius(8);
        return playerEntity;
    }

    @Override
    public void stop(GameData gameData, World world) {
        if (player != null) {
            world.removeEntity(player);
        }
    }
}
