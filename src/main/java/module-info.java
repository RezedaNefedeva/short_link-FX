module org.rezeda.diplomfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens org.rezeda.diplomfx to javafx.fxml;
    exports org.rezeda.diplomfx;
    opens org.rezeda.diplomfx.controllers to javafx.fxml;
    exports org.rezeda.diplomfx.controllers;
}