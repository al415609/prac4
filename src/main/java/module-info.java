module es.uji.prac {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.uji.prac4.Vista to javafx.fxml;
    exports es.uji.prac4.Vista;
    exports es.uji.prac4;
    opens es.uji.prac4 to javafx.fxml;
}