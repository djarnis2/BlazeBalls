package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;

/**
 * This service provider interface (SPI) is for creating bullets in the game.
 * Implementations of this interface define different types of bullets,
 * e.g., normal or enemy.
 * Implementations of this interface can define any shape, size or behavior of a bullet.
 * If no matching bullet is found during runtime use the {@link #DEFAULT_TYPE}
 */
public interface BulletSPI {
    String DEFAULT_TYPE = "normal";
    /**
     *
     * @return a string representing the bullet type
     */
    String getType();

    /**
     *
     * @param e is the entity firing the bullet
     * @param gameData Holds data like display size, key commands and delta time. Delta time is constant
     *      *                 and not dependent on framerate.
     * @return a new {@code Entity} representing the bullet added to the {@code world}
     *
     * Pre-condition:
     * {@code e} is an active entity with shooting capabilities in the {@code world}
     * {@code gameData} is not null.
     * Post-condition:
     * A new bullet entity is created based on {@code e} position and rotation
     *
     */
    Entity createBullet(Entity e, GameData gameData);
}