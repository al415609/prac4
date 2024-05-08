package es.uji.prac4.Modelo;

import es.uji.prac4.Vista.Vista;

public interface Modelo {
    void setVista(Vista vista);
    void setDistancia(String tipo_distancia);
    void setAlgoritmo(String algoritmo);
}
