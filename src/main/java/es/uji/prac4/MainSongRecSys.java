package es.uji.prac4;
import es.uji.prac4.Controlador.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainSongRecSys extends Application{
    private ImplementacionControlador implementacionControlador = new ImplementacionControlador();
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Musicman");

        stage.show();


    }

}
