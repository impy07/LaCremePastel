module com.ispwproject.lacremepastel {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.sql;

    opens com.ispwproject.lacremepastel.model to com.google.gson;

}