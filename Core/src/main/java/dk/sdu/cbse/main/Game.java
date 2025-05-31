package dk.sdu.cbse.main;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.GameKeys;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.services.IGamePluginService;
import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.common.services.IScoreService;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;

public class Game {
    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
    private final Pane gameWindow = new Pane();
    private int points = 0;
    private final Text scoreText = new Text();

    private final List<IGamePluginService> gamePluginServices;
    private final List<IEntityProcessingService> entityProcessingServices;
    private final List<IPostEntityProcessingService> postEntityProcessingServices;
    private final List<IScoreService> scoreServices;


    public Game(List<IGamePluginService> gamePluginServices,
                List<IEntityProcessingService> entityProcessingServices,
                List<IPostEntityProcessingService> postEntityProcessingServices,
                List<IScoreService> scoreServices) {
        this.gamePluginServices = gamePluginServices;
        this.entityProcessingServices = entityProcessingServices;
        this.postEntityProcessingServices = postEntityProcessingServices;
        this.scoreServices = scoreServices;
    }

    public void start(Stage window) throws Exception {
        scoreText.setX(gameData.getDisplayWidth() - 240);
        scoreText.setY(80);
        scoreText.setText("Score: " + points);
        scoreText.setFont(Font.font(40));

        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        gameWindow.getChildren().add(scoreText);

        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(getClass().getResource("/background.png").toExternalForm() ,
                        gameData.getDisplayWidth(),
                        gameData.getDisplayHeight(),
                        false,
                        true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        gameWindow.setBackground(new Background(backgroundImage));

        Scene scene = new Scene(gameWindow);
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, true);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, true);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, true);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, true);
            }
        });
        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.LEFT)) {
                gameData.getKeys().setKey(GameKeys.LEFT, false);
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                gameData.getKeys().setKey(GameKeys.RIGHT, false);
            }
            if (event.getCode().equals(KeyCode.UP)) {
                gameData.getKeys().setKey(GameKeys.UP, false);
            }
            if (event.getCode().equals(KeyCode.SPACE)) {
                gameData.getKeys().setKey(GameKeys.SPACE, false);
            }

        });

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : getGamePluginServices()) {
            iGamePlugin.start(gameData, world);
        }

        // Lookup all Entities using ServiceLoader and add to map and gameWindow.
        for (Entity entity : world.getEntities()) {
            Polygon polygon = new Polygon(entity.getPolygonCoordinates());
            polygons.put(entity, polygon);
            gameWindow.getChildren().add(polygon);
        }


        render();
        window.setScene(scene);
        window.setTitle("BlazeBalls");
        window.show();
        window.setOnCloseRequest(e -> {
            for (IGamePluginService plugin : getGamePluginServices()) {
                plugin.stop(gameData, world);
            }
            Platform.exit();
        });
    }

    // render() bliver kaldt meget ofte - skal ikke være for tung
    public void render() {
        new AnimationTimer() {
            private long timer = 0;
            private float accumulator = 0;
            private final float STEP = 1f / 60f; // 60 frames pr second

            @Override
            public void handle(long now) {
                if (timer == 0) {
                    timer = now;
                    return;
                }
                float deltaTime = (now - timer) / 1_000_000_000f; // convert to seconds
                timer = now;

                accumulator += deltaTime;

                // updates game logic in 60 frames pr second
                while (accumulator >= STEP) {
                    gameData.setDelta(STEP);
                    update();
                    gameData.getKeys().update();
                    accumulator -= STEP;
                    draw();
                }
            }
        }.start();
    }

    private void update() {
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            entityProcessorService.process(gameData, world);
        }
        for (IPostEntityProcessingService postEntityProcessorService : getPostEntityProcessingServices()) {
            postEntityProcessorService.process(gameData, world);
        }
        for (IScoreService scoreService : getIScoreServices()) {
            points = scoreService.getScore();
        }
    }

    private void draw() {
        scoreText.setText("Score: " + points);
        for (Entity polygonEntity : polygons.keySet()) {
            if (!world.getEntities().contains(polygonEntity)) {
                Polygon removedPolygon = polygons.get(polygonEntity);
                polygons.remove(polygonEntity);
                gameWindow.getChildren().remove(removedPolygon);
            }
        }

        for (Entity entity : world.getEntities()) {
            Polygon polygon = polygons.get(entity);
            if (polygon == null) {
                polygon = new Polygon(entity.getPolygonCoordinates());
                polygons.put(entity, polygon);
                gameWindow.getChildren().add(polygon);
            }
            polygon.setTranslateX(entity.getX());
            polygon.setTranslateY(entity.getY());
            polygon.setRotate(entity.getRotation());
        }
    }

    public List<IGamePluginService> getGamePluginServices() {
        return gamePluginServices;
    }

    public List<IEntityProcessingService> getEntityProcessingServices() {
        return entityProcessingServices;
    }

    public List<IPostEntityProcessingService> getPostEntityProcessingServices() {
        return postEntityProcessingServices;
    }

    public List<IScoreService> getIScoreServices() {
        return scoreServices;
    }
}
