package es.uji.prc.Vista;

import javafx.scene.control.ListView;

public interface Vista {
    public ListView<String> getListaCanciones();
    public Integer getNum_spinner();
    ListView<String> getListaRecomendados();
    String getMethod();
    String getDistance();
    String getSong();
}
