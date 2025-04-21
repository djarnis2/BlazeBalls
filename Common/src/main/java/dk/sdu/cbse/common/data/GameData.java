package dk.sdu.cbse.common.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Holds display dimensions and input data.
 */
public class GameData {
    private int displayWidth  = 1280 ;
    private int displayHeight = 1024;
    private final GameKeys keys = new GameKeys();
    private float delta;
    private final Map<String, Object> data = new HashMap<>();

    public void setData(String key, Object value) {
        data.put(key, value);
    }

    public Object getData(String key) {
        return data.get(key);
    }
    public float getDelta() {
        return delta;
    }

    public void setDelta(float delta) {
        this.delta = delta;
    }

    public GameKeys getKeys() {
        return keys;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }
}
