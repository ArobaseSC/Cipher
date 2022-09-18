module org.arobase.cipher {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens org.arobase.cipher to javafx.fxml;
    exports org.arobase.cipher;
}