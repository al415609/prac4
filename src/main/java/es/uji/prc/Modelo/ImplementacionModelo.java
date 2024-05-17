package es.uji.prc.Modelo;

import es.uji.prc.Controlador.ImplementacionControlador;
import es.uji.prc.Metodos.*;
import es.uji.prc.Vista.Vista;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImplementacionModelo implements Modelo{
    private Vista vista;
    protected RecSys recSys;
    //private Distance distancia;

    public ImplementacionModelo(){
        super();
    }
    @Override
    public List<String> createSongNameList(String ficheroCanciones) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(ficheroCanciones));
        List<String> nombreCanciones = new ArrayList<>();
        String line;

        while((line = br.readLine()) != null){
            nombreCanciones.add(line);
        }
        br.close();
        return nombreCanciones;

    }

    private Map<String, String> setFileNames(){
        String sep = FileSystems.getDefault().getSeparator();
        Map<String, String> filenames = new HashMap<>();
        filenames.put("knn"+ "train", "src" + sep + "main" + sep + "resources" + sep + "songs_train.csv");
        filenames.put("knn" + "test", "src" + sep + "main" + sep + "resources" + sep + "songs_test.csv");
        filenames.put("kmeans" + "train", "src" + sep + "main" + sep + "resources" + sep + "songs_train_withoutnames.csv");
        filenames.put("kmeans" + "test", "src" + sep + "main" + sep + "resources" + sep + "songs_test_withoutnames.csv");
        return filenames;
    }

    private Map<String, Algorithm> setAlgorithms(Distance distance){
        Map<String, Algorithm> algorithms = new HashMap<>();
        algorithms.put("knn", new KNN(distance));
        algorithms.put("kmeans", new KMeans(15,200, 4321, distance));
        System.out.println(algorithms.get("knn"));
        return algorithms;
    }
    @Override
    public List<String> recommendedSongs(String method, Distance distance, String ficheroCanciones, String song, int numRecomendaciones) throws IOException, InvalidNumberOfClustersException, InvalidNameLikedItem {
        Map<String, String> fileNames = setFileNames();
        Map<String, Algorithm> algorithms = setAlgorithms(distance);
        Map<String, Table> tables = new HashMap<>();
        String[] stages = {"train" , "test"};
        for(String stage : stages){
            ReaderTemplate lectorKNN = new CSVLabeledFileReader(fileNames.get("knn" + stage));
            tables.put("knn" + stage, lectorKNN.readTableFromSource());
            ReaderTemplate lectorKMeans = new CSVUnlabeledFileReader(fileNames.get("kmeans" + stage));
            tables.put("kmeans" + stage, lectorKMeans.readTableFromSource());
        }

        //nombres de canciones
        List<String> nombresCanciones = createSongNameList(ficheroCanciones);
        System.out.println(method);
        this.recSys = new RecSys(algorithms.get(method));
        this.recSys.train(tables.get(method + "train"));
        this.recSys.run(tables.get(method + "test"), nombresCanciones);
        return this.recSys.recommend(song, numRecomendaciones);
    }

    public void setVista(Vista vista){
        this.vista = vista;
    }
}

