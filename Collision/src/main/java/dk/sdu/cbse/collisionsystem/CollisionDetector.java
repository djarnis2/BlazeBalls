package dk.sdu.cbse.collisionsystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

import java.util.ArrayList;
import java.util.List;

public class CollisionDetector implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        List<Entity> entities = new ArrayList<>(world.getEntities());
        for (Entity entity : entities) {
            entity.setData("collidesWith", null);
            entity.setData("collidesWithType", null);
        }
        for (int i = 0; i < entities.size(); i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                Entity entity1 = entities.get(i);
                Entity entity2 = entities.get(j);
                if (this.collides(entity1,entity2)) {
                    entity1.setData("collidesWith", entity2);
                    entity1.setData("collidesWithType", entity2.getData("type"));
                    entity2.setData("collidesWith", entity1);
                    entity2.setData("collidesWithType", entity1.getData("type"));
                }
            }
        }
    }
    public Boolean collides(Entity e1, Entity e2) {
        float dx = (float) e1.getX() - (float) e2.getX();
        float dy = (float) e1.getY() - (float) e2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy); // Pythagoras
        return distance < e1.getRadius() + e2.getRadius(); // true if distance is smaller than adding the two entities radius.
    }
}
