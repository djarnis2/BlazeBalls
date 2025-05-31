package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import java.util.List;

public class AsteroidPostProcessingSystem implements IPostEntityProcessingService {

    ScoreClient scoreClient = new ScoreClient();
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Asteroid.class)) {
            Entity collider = (Entity) entity.getData("collidesWith");
            if (collider != null && !(collider instanceof Asteroid)) {
                if (collider.getData("type").equals("bullet")) {
                    int size = ((Asteroid) entity).getSize();
                    scoreClient.givePoints(size*100);
                }
                List<Asteroid> fragments = AsteroidFactory.splitAsteroid((Asteroid)entity,gameData);
                fragments.forEach(world::addEntity);
                world.removeEntity(entity); // The original asteroid
                world.removeEntity(collider); // player or enemy only
            }
            entity.setData("collidesWith", null); // clean up
        }
    }
}
