package es.uji.prac4.Modelo;

import es.uji.prac4.Vista.ImplementacionVista;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface Modelo {
    List<String> createSongNameList(String fichero_canciones) throws IOException;
}
