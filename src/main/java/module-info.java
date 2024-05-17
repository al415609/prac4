module es.uji.prc {
    requires javafx.controls;
    requires javafx.fxml;

    opens es.uji.prc.Vista to javafx.fxml;
    exports es.uji.prc.Vista;
    opens es.uji.prc to javafx.fxml;
    exports es.uji.prc;
    opens es.uji.prc.Controlador to javafx.fxml;
    exports es.uji.prc.Controlador;
    opens es.uji.prc.Modelo to javafx.fxml;
    exports es.uji.prc.Modelo;
    opens es.uji.prc.Metodos to javafx.fxml;
    exports es.uji.prc.Metodos;
}