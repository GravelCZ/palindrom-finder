package cz.vesely.palindromy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PalindromFinderApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PalindromFinderApplication.class.getResource("palindromy.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800  , 400);
        stage.setTitle("Palindromy");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}