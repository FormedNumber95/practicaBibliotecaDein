module es.aketzagonzalez {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.base;
    opens es.aketzagonzalez.ctrl to javafx.fxml;
    opens es.aketzagonzalez.model to javafx.base;
    exports es.aketzagonzalez.practicaBibliotecaDein;
}