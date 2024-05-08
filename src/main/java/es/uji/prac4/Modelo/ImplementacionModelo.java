package es.uji.prac4.Modelo;

import es.uji.prac4.Metodos.*;
import es.uji.prac4.Vista.Vista;

public class ImplementacionModelo implements Modelo{
    private Vista vista;
    private Algorithm algorithm;
    protected RecSys recSys;
    private Distance distance;

    public ImplementacionModelo(){
        super();
    }
    public void setDistancia(String tipo_distancia){
        if(tipo_distancia.equals("Ecuclidiana")){
            this.distance = new EuclideanDistance();
        } else {
            this.distance = new ManhattanDistance();
        }
    }
    public void setAlgoritmo(String algoritmo){
        if(algoritmo.equals("KNN")){
            this.algorithm = new KNN(distance);
        } else {
            this.algorithm = new KMeans(5, 200, 4321, distance);
        }
    }

    public void setVista(Vista vista){
        this.vista = vista;
    }

}
