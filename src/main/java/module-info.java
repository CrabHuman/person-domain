module edu.gmu.cs321 {
    requires transitive javafx.controls;
    requires javafx.fxml;

    opens edu.gmu.cs321 to javafx.fxml;
    exports edu.gmu.cs321;
}