package es.uji.prc.Modelo;

import es.uji.prc.Metodos.Distance;
import es.uji.prc.Metodos.InvalidNameLikedItem;
import es.uji.prc.Metodos.InvalidNumberOfClustersException;

import java.io.IOException;
import java.util.List;

public interface Modelo {
    List<String> createSongNameList(String fichero_canciones) throws IOException;
    List<String> recommendedSongs(String method, Distance distance, String ficheroCanciones, String song, int numRecomendaciones) throws IOException, InvalidNumberOfClustersException, InvalidNameLikedItem;
}
