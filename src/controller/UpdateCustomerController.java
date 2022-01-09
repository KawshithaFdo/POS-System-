package controller;

import DataBase.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Customer;

import java.sql.*;

public class UpdateCustomerController {
    public AnchorPane UpdateCustomerContext;
    public TextField txtId;
    public TextField txtname;
    public TextField txtAddress;
    public TextField txttitle;
    public TextField txtcity;
    public TextField txtProvince;
    public TextField txtPostalCode;

    public void OpenUpdateTexts(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


       String customerId=txtId.getText();
       Customer c1=new CustomerController().getCustomer(customerId);
        if(c1==null){

            new Alert(Alert.AlertType.WARNING,"Empty Result Set..").show();


        }else{
            setData(c1);
        }

    }
    public void setData(Customer c){
        txtId.setText(c.getId());
        txttitle.setText(c.getTitle());
        txtname.setText(c.getName());
        txtAddress.setText(c.getAddress());
        txtcity.setText(c.getCity());
        txtProvince.setText(c.getProvince());
        txtPostalCode.setText(c.getPostalcode());
    }

    public void UpdateCustomer(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {

        Customer c1=new Customer(txtId.getText(),txtname.getText(),txtAddress.getText(),txtcity.getText(),txtProvince.getText(),
                txtPostalCode.getText(),txttitle.getText());

        if(new CustomerController().updateCustomer(c1)){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated...").show();
            txtId.setText("");
            txtname.setText("");
            txttitle.setText("");
            txtAddress.setText("");
            txtcity.setText("");
            txtProvince.setText("");
            txtPostalCode.setText("");
        }else{
            new Alert(Alert.AlertType.WARNING,"Try Again...").show();
        }
    }


}
