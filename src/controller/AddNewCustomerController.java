package controller;

import DataBase.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Customer;

import java.sql.*;

public class AddNewCustomerController {
    public AnchorPane AddNewCustomerContext;
    public TextField txtId;
    public TextField txtname;
    public TextField txtAddress;
    public TextField txtcity;
    public TextField txtProvince;
    public TextField txtPostalCode;
    public TextField txttitle;


    public void saveCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        Customer c1=new Customer(
                txtId.getText(),txtname.getText(),txtAddress.getText(),txtcity.getText(),txtProvince.getText(),txtPostalCode.getText(),txttitle.getText()
        );

        if (new CustomerController().saveCustomer(c1)){
            new Alert(Alert.AlertType.CONFIRMATION,"Saved..").show();
            txtId.setText("");
            txtname.setText("");
            txttitle.setText("");
            txtAddress.setText("");
            txtcity.setText("");
            txtProvince.setText("");
            txtPostalCode.setText("");
        }else{
            new Alert(Alert.AlertType.WARNING,"Try Again..").show();
        }

    }

}
