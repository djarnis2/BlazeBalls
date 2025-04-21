package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface IPostEntityProcessingService {
    /**
     *
     * @param gameData
     * @param world
     */

    void process(GameData gameData, World world);
}
