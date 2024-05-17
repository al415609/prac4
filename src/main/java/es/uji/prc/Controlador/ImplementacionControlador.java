package es.uji.prc.Controlador;

import es.uji.prc.Metodos.*;
import es.uji.prc.Vista.Vista;
import es.uji.prc.Modelo.Modelo;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.List;

public class ImplementacionControlador implements Controlador{
    private Modelo modelo;
    private Vista vista;
    private Distance distance;
    private final String sep = FileSystems.getDefault().getSeparator();
    private final String ruta_recursos = "src" + sep + "main" + sep + "resources" + sep;
    private final String ruta_nombres =  ruta_recursos + "songs_test_names.csv";
    @Override
    public void setSongList() throws IOException {
        vista.getListaCanciones().getItems().addAll(modelo.createSongNameList(ruta_nombres));

    }
    @Override
    public List<String> setCancionesRecomendadas() throws InvalidNameLikedItem, InvalidNumberOfClustersException, IOException {
        return modelo.recommendedSongs(vista.getMethod(),distance, ruta_nombres, vista.getSong(),vista.getNum_spinner());

    }
    @Override
    public void setDistance(String distancia) {
        if(distancia.equals("Euclidiana")){
            this.distance = new EuclideanDistance();
        } else {
            this.distance = new ManhattanDistance();
        }
    }
    public void setVista(Vista vista){
        this.vista = vista;
    }
    public void setModelo(Modelo modelo){
        this.modelo = modelo;
    }
}
