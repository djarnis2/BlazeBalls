package dk.sdu.cbse.playersystem;

import dk.sdu.cbse.common.data.Entity;

public class Player extends Entity {
    private int lifePoints = 3;

    public int getLifePoints() {
        return lifePoints;
    }

    public void damage() {
        lifePoints --;
    }
}
