package es.uji.prac4;
import es.uji.prac4.Controlador.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainSongRecSys extends Application{
    private Controlador controlador = new Controlador();
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Musicman");

        stage.show();


    }

}
