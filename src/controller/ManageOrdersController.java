package controller;

import DataBase.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.CartTm;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageOrdersController {
    public ComboBox cmbCustomer;
    public ComboBox cmbOrder;
    public TableColumn colQty;
    public TableView tblOrder;
    public TableColumn colUnitPrice;
    public TableColumn colDescription;
    public TableColumn colItemId;
    public TextField Qty;
    public AnchorPane manageOrdersContext;

    public void initialize(){
        try {
            loadCustomerIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        cmbCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                loadItemIds((String) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbOrder.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                getdata(cmbCustomer.getValue());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        });


    }
    public void getdata(Object id) throws SQLException, ClassNotFoundException {

    }

    private void setOrderData(String newValue) {
    }


    private void loadItemIds(String newValue) throws SQLException, ClassNotFoundException {
        List<String> OrderIds = new OrderController().getOrderIds(newValue);
        System.out.println(OrderIds);
        cmbOrder.getItems().addAll(OrderIds);
    }


    public void loadCustomerIds() throws SQLException, ClassNotFoundException {
        List<String> customerIds = new CustomerController().getCustomerIds();
        cmbCustomer.getItems().addAll(customerIds);
    }
    public void removeItem(ActionEvent actionEvent) {
    }

    public void CancelOrder(ActionEvent actionEvent) {
    }

    public void EditOrder(ActionEvent actionEvent) {
    }

    public void Back(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/ReceptionistForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) manageOrdersContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
