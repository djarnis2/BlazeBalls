package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

/**
 * A Plugin Interface. Implementations found as JAR files in the mods-mvn folder,
 * can register and unregister entities of their own type.
 */
public interface IGamePluginService {
    /**
     * Called when the module is added to the game.
     * This method can create and register entities of the modules own type.
     *
     * @param gameData
     * Holds data like display size, key commands and delta time. Delta time should be constant
     *                 and not dependent on framerate.
     * @param world Holds data of all the current entities in the world.
     *              The module should add entities of its own type to this world.
     *
     * Pre-condition:
     *              The module is not active. {@code gameData} and {@code world} is not null.
     * Post-condition:
     *              Entities created by this module are added to the world and will be processed.
     */

    void start(GameData gameData, World world);

    /**
     * Called when the module is removed from the game.
     * It should remove all entities created by this plugin.
     *
     * @param gameData Holds data like display size, key commands and delta time
     * @param world The module is responsible for removing entities of its own type from the world.
     *
     * Pre-conditions:
     *              The module is active.
     * Post-conditions:
     *              The module and all its entities are removed from the world.
     */
    void stop(GameData gameData, World world);
}
