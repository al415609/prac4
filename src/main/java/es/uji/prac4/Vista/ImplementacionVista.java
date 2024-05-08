package es.uji.prac4.Vista;

import es.uji.prac4.Controlador.ImplementacionControlador;
import es.uji.prac4.Modelo.ImplementacionModelo;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class ImplementacionVista {
    Stage stage;
    private ImplementacionControlador implementacionControlador;
    Scene scena;
    private ImplementacionModelo implementacionModelo;
    protected int num_spinner;
    protected ListView<String> lista_canciones;

    public ImplementacionVista(){
        super();
    }

    public ImplementacionVista(Stage stage){
        this.stage = stage;
    }

    public void crearGUI() {
        //Reocmendación
        Label label = new Label("Tipo de recomendación");
        label.setTextFill(Color.rgb(239, 184, 16)); // Establece el color del texto en blanco
        label.setFont(Font.font("Liberatio", 20));
        RadioButton rb1 = new RadioButton("Recommendación basada en las colaboraciones");
        rb1.setTextFill(Color.rgb(239, 184, 16));
        rb1.setFont(Font.font("Liberatio", 12));
        RadioButton rb2 = new RadioButton("Recommendación basada en el género");
        rb2.setTextFill(Color.rgb(239, 184, 16));
        rb2.setFont(Font.font("Liberatio", 12));

        ToggleGroup tg1 = new ToggleGroup();
        rb1.setToggleGroup(tg1);
        rb2.setToggleGroup(tg1);

        //Tipo de distancia
        Label distancia = new Label("Tipo de distancia");
        distancia.setTextFill(Color.rgb(239, 184, 16)); // Establece el color del texto en blanco
        distancia.setFont(Font.font("Liberatio", 20));
        ToggleButton Euclidiana = new ToggleButton("Euclidiana");
        Euclidiana.setTextFill(Color.rgb(239, 184, 16));
        Euclidiana.setFont(Font.font("Liberatio", 12));
        //Euclidiana.setStyle("-fx-background-color: rgb(28, 28, 28); -fx-border-style: dashed; -fx-border-color: rgb(239, 184, 16)");
        ToggleButton Manhattan = new ToggleButton("Manhattan");
        Manhattan.setTextFill(Color.rgb(239, 184, 16));
        Manhattan.setFont(Font.font("Liberatio", 12));
        //Manhattan.setStyle("-fx-background-color: rgb(28, 28, 28); -fx-border-style: dashed; -fx-border-color: rgb(239, 184, 16)");

        ToggleGroup distancias = new ToggleGroup();
        Euclidiana.setToggleGroup(distancias);
        Manhattan.setToggleGroup(distancias);

        //Titulos de canciones
        Label titulos = new Label("Titulos de canciones");
        ScrollPane canciones = new ScrollPane();

        VBox tipo_recomendacion = new VBox(label, rb1, rb2);
        tipo_recomendacion.setSpacing(10);

        HBox tipo_distancia = new HBox(Euclidiana, Manhattan);
        tipo_distancia.setSpacing(10);

        //Acciones
        // rb1.setOnAction(e -> controlador.);
        // rb2.setOnAction();

        VBox panel = new VBox(tipo_recomendacion, distancia, tipo_distancia, titulos, canciones);
        panel.setSpacing(10);
        panel.setBackground(new Background(new BackgroundFill(Color.rgb(28, 28, 28), null, null))); // Establece el color de fondo oscuro


        this.scena = new Scene(panel);
        stage.setScene(scena);
        stage.setMinWidth(400); // Ancho mínimo
        stage.setMaxWidth(400); // Ancho máximo
        stage.setMinHeight(300); // Alto mínimo
        stage.setMaxHeight(300);
        stage.setX(458D);
        stage.setY(241D);
        stage.show();
    }

    public void setControlador(ImplementacionControlador implementacionControlador){
        this.implementacionControlador = implementacionControlador;
    }
    public void setModelo(ImplementacionModelo implementacionModelo){
        this.implementacionModelo = implementacionModelo;
    }
    public int getNum_spinner(){
        return num_spinner;
    }
    public ListView<String> getListaCanciones(){
        return lista_canciones;
    }
}
