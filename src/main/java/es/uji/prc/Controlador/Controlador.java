package es.uji.prc.Controlador;

import es.uji.prc.Metodos.InvalidNameLikedItem;
import es.uji.prc.Metodos.InvalidNumberOfClustersException;

import java.io.IOException;
import java.util.List;

public interface Controlador {
    void setSongList() throws IOException;
    List<String> setCancionesRecomendadas() throws InvalidNameLikedItem, InvalidNumberOfClustersException, IOException;
    void setDistance(String distancia);
}
