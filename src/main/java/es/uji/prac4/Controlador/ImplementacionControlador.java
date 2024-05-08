package es.uji.prac4.Controlador;

import es.uji.prac4.Metodos.Algorithm;
import es.uji.prac4.Metodos.Distance;
import es.uji.prac4.Vista.Vista;
import es.uji.prac4.Modelo.Modelo;

import java.io.IOException;
import java.nio.file.FileSystems;

public class ImplementacionControlador {
    private Modelo modelo;
    private Vista vista;
    private Distance distance;
    private Algorithm algorithm;
    private String sep = FileSystems.getDefault().getSeparator();
    private final String ruta_recursos = "src" + sep + "main" + sep + "java" + sep + "resources" + sep;
    private final String ruta_nombres = ruta_recursos + "songs_test_names.csv";

    public void setSongList() throws IOException {
        vista.getListaCanciones.getItems().addALL(modelo.createSongNameList(ruta_nombres));
    }

}
