module cz.vesely.palindromy {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens cz.vesely.palindromy to javafx.fxml;
    exports cz.vesely.palindromy;
}