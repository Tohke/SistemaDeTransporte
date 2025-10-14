module com.st.sistemadetransporte {
    requires javafx.controls;
    requires javafx.fxml;


    // JAVA FX
    //requires org.controlsfx.controls;
    //requires org.kordamp.bootstrapfx.core;

    // MONGODB
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;


    opens com.st.sistemadetransporte to javafx.fxml;
    exports com.st.sistemadetransporte;
}