package dk.sdu.cbse.playersystem;

import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;

public class PlayerPostProcessingSystem implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity : world.getEntities(Player.class)) {
            Entity damager = (Entity) entity.getData("collidesWith");
            if (damager instanceof Bullet) {
                Player player = (Player) entity;
                world.removeEntity(damager);
                player.damage();
                if (player.getLifePoints() > 0) {
                    System.out.println(player.getLifePoints());
                }
                else {
                    world.removeEntity(player);
                    System.out.println("YOU DIED");
                }
            }
        }
    }
}
