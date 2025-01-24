module es.aketzagonzalez {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
	requires java.sql;
	requires javafx.graphics;
    opens es.aketzagonzalez.ctrl to javafx.fxml;
    exports es.aketzagonzalez.practicaBibliotecaDein;
}