package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ReceptionistFormController {
    public AnchorPane CashierOrdersViewContext;

    public void ManageOrdersOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ManageOrders.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) CashierOrdersViewContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void MakeOrdersOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/MakeOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) CashierOrdersViewContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void Back(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DashBoard.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) CashierOrdersViewContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
