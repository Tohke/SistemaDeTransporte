module com.modelo {
    requires javafx.controls;
    requires javafx.fxml;


    // JAVA FX
    //requires org.controlsfx.controls;
    //requires org.kordamp.bootstrapfx.core;

    // MONGODB
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;
    requires java.desktop;


    opens com.st.sistemadetransporte to javafx.fxml;
    exports com.st.sistemadetransporte;
    exports Controllers;
    opens Controllers to javafx.fxml;
    exports modelo;
    opens modelo to javafx.fxml;
    exports Controllers.OperatorsVersion;
    opens Controllers.OperatorsVersion to javafx.fxml;
}