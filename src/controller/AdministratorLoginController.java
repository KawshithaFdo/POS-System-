package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdministratorLoginController {
    public JFXTextField username;
    public JFXPasswordField password;
    public AnchorPane AdministratorLoginContext;

    public void LogInOnaction(ActionEvent actionEvent) throws IOException {
        if((username.getText()).equals("")){
            new Alert(Alert.AlertType.WARNING, "Please Enter  User Name.", ButtonType.CLOSE).show();
            return;
        }
        if((password.getText()).equals("")){
            new Alert(Alert.AlertType.WARNING, "Please Enter Valid Password.", ButtonType.CLOSE).show();
            return;
        }
        URL resource = getClass().getResource("../view/AdministratorForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) AdministratorLoginContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void goBackOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DashBoard.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) AdministratorLoginContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
