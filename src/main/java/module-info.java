module com.example.labtask333 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.labtask333 to javafx.fxml;
    exports com.example.labtask333;
}