package es.uji.prac4.Modelo;

import es.uji.prac4.Metodos.*;
import es.uji.prac4.Vista.ImplementacionVista;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImplementacionModelo implements Modelo{
    private ImplementacionVista implementacionVista;
    private Algorithm algorithm;
    protected RecSys recSys;
    private Distance distance;

    public ImplementacionModelo(){
        super();
    }

    public List<String> createSongNameList(String fichero_canciones) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fichero_canciones));
        List<String> nombre_canciones = new ArrayList<>();
        String line;

        while((line = br.readLine()) != null){
            nombre_canciones.add(line);
        }
        br.close();
        return nombre_canciones;

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

    public void setVista(ImplementacionVista implementacionVista){
        this.implementacionVista = implementacionVista;
    }

}
