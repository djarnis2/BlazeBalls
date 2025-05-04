package dk.sdu.cbse.asteroids;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;

import java.util.ArrayList;
import java.util.List;

public class AsteroidFactory {
    private static final float BASE_SIZE = 8.0f;

    public static Entity createAsteroid(GameData gameData,int size, float x, float y) {
        Entity asteroid = new Asteroid(size);
        float scale = BASE_SIZE * size;

        asteroid.setPolygonCoordinates(
                -scale, -scale,
                scale, -scale,
                scale, scale,
                -scale, scale
        );

        asteroid.setX(x);
        asteroid.setY(y);
        asteroid.setRadius(scale);
        asteroid.setRotation((float)Math.random() * 360);

        // setting random speed on each entity
        float speed = 100f + (float) Math.random() * 50;
        asteroid.setData("speed", speed);
        return asteroid;
    }

    public static List<Asteroid> splitAsteroid(Asteroid original, GameData gameData) {
        int size = original.getSize();
        List<Asteroid> fragments = new ArrayList<>();
        if (original.canSplit()){
            for (int i = 0; i < 2; i++) { // create two new fragments with opposite angles
                float newRotation = (float)original.getRotation() +
                        (float)(Math.random() * 60 + 30) * (i == 0 ? 1 : -1);
                Asteroid newAsteroid = (Asteroid) createAsteroid(
                        gameData,
                        size - 1,
                        (float)original.getX(),
                        (float)original.getY());
                newAsteroid.setRotation(newRotation);
                fragments.add(newAsteroid);
            }
        }
        return fragments;
    }
}