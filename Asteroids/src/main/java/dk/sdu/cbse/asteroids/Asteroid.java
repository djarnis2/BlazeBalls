package dk.sdu.cbse.asteroids;
import dk.sdu.cbse.common.data.Entity;

public class Asteroid extends Entity {
    private int size;

    public Asteroid (int size) {
        this.size = size;
        this.setData("size", size);
    }


    public int getSize() {
        return size;
    }

    public boolean canSplit() {
        return size > 1;
    }
}
