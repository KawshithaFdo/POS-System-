package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdministratorController {
    public AnchorPane AdministratorContext;

    public void ManageItemsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManageItems.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) AdministratorContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void SystemReportsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/SystemReports.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) AdministratorContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void Back(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DashBoard.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) AdministratorContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
