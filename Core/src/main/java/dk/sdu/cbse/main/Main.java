package dk.sdu.cbse.main;

import javafx.application.Application;
import javafx.stage.Stage;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {

    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage window) throws Exception {
        AnnotationConfigApplicationContext springContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        Game game = springContext.getBean(Game.class);
        game.start(window);
    }
}
