package es.uji.prc.Vista;

import es.uji.prc.Metodos.InvalidNameLikedItem;
import es.uji.prc.Metodos.InvalidNumberOfClustersException;
import es.uji.prc.Modelo.Modelo;
import es.uji.prc.Controlador.Controlador;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;


public class ImplementacionVista implements Vista{
    Stage stage;
    private Modelo modelo;
    private Controlador controlador;
    private Stage segundaVentana;
    Scene scena;
    protected int num_spinner;
    protected String algoritmoElegido;
    protected String distanciaElegida;
    protected String cancionElegida;
    protected Button botonRecomendar;
    protected Button botonRecomendarOtraVez;
    protected ListView<String> listaCanciones;
    protected ListView<String> listaRecomendaciones;

    public ImplementacionVista(Stage stage){
        this.stage = stage;
    }

    public void crearGUI() throws IOException {
        stage.setTitle("Musicman");
        //Segunda ventana
        segundaVentana = new Stage();

        //Reocmendación
        Label label = new Label("Tipo de recomendación");
        //label.setTextFill(Color.rgb(239, 184, 16)); // Establece el color del texto en blanco
        //label.setFont(Font.font("Liberatio", 20));

        RadioButton kmeansBoton = new RadioButton("Recommendación basada en las colaboraciones");
        //kmeansBoton.setTextFill(Color.rgb(239, 184, 16));
        //kmeansBoton.setFont(Font.font("Liberatio", 12));
        RadioButton knnBoton = new RadioButton("Recommendación basada en el género");
       // knnBoton.setTextFill(Color.rgb(239, 184, 16));
        //knnBoton.setFont(Font.font("Liberatio", 12));

        ToggleGroup metodo = new ToggleGroup();
        kmeansBoton.setToggleGroup(metodo);
        knnBoton.setToggleGroup(metodo);

        //kmeans seleccionado
        kmeansBoton.setSelected(true);
        algoritmoElegido = "kmeans";

        //Acciones
        kmeansBoton.setOnAction(e -> {
            algoritmoElegido = "kmeans";
        });
        knnBoton.setOnAction(e -> {
            algoritmoElegido = "knn";
        });

        //Tipo de distancia
        Label distancia = new Label("Tipo de distancia");
        //distancia.setTextFill(Color.rgb(239, 184, 16)); // Establece el color del texto en blanco
        //distancia.setFont(Font.font("Liberatio", 20));
        ToggleButton Euclidiana = new ToggleButton("Euclidiana");
        //Euclidiana.setTextFill(Color.rgb(239, 184, 16));
       // Euclidiana.setFont(Font.font("Liberatio", 12));
        //Euclidiana.setStyle("-fx-background-color: rgb(28, 28, 28); -fx-border-style: dashed; -fx-border-color: rgb(239, 184, 16)");
        ToggleButton Manhattan = new ToggleButton("Manhattan");
        //Manhattan.setTextFill(Color.rgb(239, 184, 16));
       // Manhattan.setFont(Font.font("Liberatio", 12));
        //Manhattan.setStyle("-fx-background-color: rgb(28, 28, 28); -fx-border-style: dashed; -fx-border-color: rgb(239, 184, 16)");

        ToggleGroup distancias = new ToggleGroup();
        Euclidiana.setToggleGroup(distancias);
        Manhattan.setToggleGroup(distancias);

        Euclidiana.setSelected(true);
        distanciaElegida = "Euclidiana";

        Euclidiana.setOnAction(e ->{
            distanciaElegida = "Euclidiana";
        });
        Manhattan.setOnAction(e -> {
            distanciaElegida = "Mahattan";
        });


        //Titulos de canciones
        Label titulos = new Label("Titulos de canciones");
        //titulos.setTextFill(Color.rgb(239, 184, 16));

        //Se crea lista de canciones
        listaCanciones = new ListView<>();
        controlador.setSongList();
        listaCanciones.setStyle("-fx-text-fill: rgb(239, 184, 16);");

        botonRecomendar = new Button("Recomendar");

        listaCanciones.setOnMouseClicked(e -> {
            cancionElegida = listaCanciones.getSelectionModel().getSelectedItem();
            botonRecomendar.setText("Recomendaciones de: " + cancionElegida);
            if(e.getClickCount() == 2){
                botonRecomendar.fire();
            }
        });

        BooleanBinding sePuedePulsarRecomendar = Bindings.createBooleanBinding(() ->
                metodo.getSelectedToggle() != null && distancias.getSelectedToggle() != null
                && listaCanciones.getSelectionModel().getSelectedItem() != null && !listaCanciones.getSelectionModel().getSelectedItem().isEmpty(),
                metodo.selectedToggleProperty(), distancias.selectedToggleProperty(), listaCanciones.getSelectionModel().selectedItemProperty());

        botonRecomendar.disableProperty().bind(sePuedePulsarRecomendar.not());

        listaRecomendaciones = new ListView<>();
        Label podríaGustarte = new Label();

        botonRecomendar.setOnAction(e -> {
            try {
                controlador.setDistance(distanciaElegida);
                List<String> listaRecomendacionesAux = controlador.setCancionesRecomendadas();
                ObservableList<String> observableRecomendaciones = FXCollections.observableArrayList(listaRecomendacionesAux);
                listaRecomendaciones.setItems(observableRecomendaciones);
            }catch (Exception ex){
                ex.printStackTrace();
            }
            podríaGustarte.setText("Si te gustó" + cancionElegida + "te puede gustar: ");
            segundaVentana.show();
        });

        VBox tipo_recomendacion = new VBox(label, kmeansBoton, knnBoton);
        tipo_recomendacion.setSpacing(10);

        HBox tipo_distancia = new HBox(Euclidiana, Manhattan);
        tipo_distancia.setSpacing(10);


        VBox panel = new VBox(tipo_recomendacion, distancia, tipo_distancia, titulos, listaCanciones, botonRecomendar);
        panel.setSpacing(10);
        panel.setBackground(new Background(new BackgroundFill(Color.rgb(185, 190, 198), null, null))); // Establece el color de fondo oscuro

        //Inicializamos los elementos del segundo panel
        VBox vBox_ventana_2 = new VBox();
        HBox hBox_ventana_2 = new HBox();

        //etiquetas
        Label num_recomendaciones = new Label("Numero de recomendaciones: ");
        Spinner<Integer> spinner = new Spinner<Integer>(1, 20, 1);
        Button cerrar = new Button("Cerrar");
        segundaVentana.setTitle("Canciones Recomendadas");


        this.scena = new Scene(panel);
        stage.setScene(scena);
        stage.setWidth(500); // Ancho
        stage.setHeight(600);
        stage.resizableProperty().set(true);
        stage.centerOnScreen();
        stage.show();

        hBox_ventana_2.getChildren().addAll(spinner);
        hBox_ventana_2.setSpacing(210);

        vBox_ventana_2.getChildren().addAll(num_recomendaciones,hBox_ventana_2,podríaGustarte, listaRecomendaciones, cerrar);
        vBox_ventana_2.setSpacing(10);

        Scene segundaEscena = new Scene(vBox_ventana_2, 500, 400);
        segundaVentana.setScene(segundaEscena);
        segundaVentana.setWidth(400); // Ancho
        segundaVentana.setHeight(300); // Alto
        segundaVentana.centerOnScreen();
        segundaVentana.setResizable(true);

        num_spinner = spinner.getValue();

        spinner.valueProperty().addListener((observable -> {
            num_spinner = spinner.getValue();
            try {
                List<String> listaRecomendacionesAux2 = controlador.setCancionesRecomendadas();
                ObservableList<String> observableRecomendaciones2 = FXCollections.observableArrayList(listaRecomendacionesAux2);
                listaRecomendaciones.setItems(observableRecomendaciones2);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }));

        cerrar.setOnAction(e ->{
            segundaVentana.close();
            stage.close();
        });

    }

    public void setControlador(Controlador controlador){
        this.controlador = controlador;
    }
    public void setModelo(Modelo modelo){
        this.modelo = modelo;
    }
    @Override
    public Integer getNum_spinner(){
        return num_spinner;
    }
    @Override
    public ListView<String> getListaCanciones(){
        return listaCanciones;
    }
    @Override
    public ListView<String> getListaRecomendados(){
        return listaRecomendaciones;
    }
    @Override
    public String getMethod(){
        return algoritmoElegido;
    }
    @Override
    public String getDistance(){ return distanciaElegida; }
    @Override
    public String getSong(){ return cancionElegida; }
}
