package es.uji.prc;
import es.uji.prc.Controlador.*;
import es.uji.prc.Modelo.ImplementacionModelo;
import es.uji.prc.Vista.ImplementacionVista;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainSongRecSys extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        ImplementacionControlador controlador = new ImplementacionControlador();
        ImplementacionModelo modelo = new ImplementacionModelo();
        ImplementacionVista vista = new ImplementacionVista(stage);
        modelo.setVista(vista);
        controlador.setVista(vista);
        controlador.setModelo(modelo);
        vista.setModelo(modelo);
        vista.setControlador(controlador);
        vista.crearGUI();
    }
    public static void main(String[] args){
        launch();
    }

}
