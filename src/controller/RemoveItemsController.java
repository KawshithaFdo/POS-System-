package controller;

import DataBase.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Customer;
import model.Item;

import java.sql.*;

public class RemoveItemsController {
    public TextField txtId;
    public TextField txtDescription;

    public void RemoveItems(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Item WHERE ItemCode=?");
            stm.setObject(1,txtId.getText());
            if (stm.executeUpdate()>0){
                new Alert(Alert.AlertType.CONFIRMATION,"Item Removed..").show();
                txtId.setText("");
                txtDescription.setText("");
            }else{
                new Alert(Alert.AlertType.WARNING,"Try Again..").show();
                txtId.setText("");
                txtDescription.setText("");
            }


    }

    public void LoadItemData(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


           Connection con=DbConnection.getInstance().getConnection();
            PreparedStatement stm = con.prepareStatement("SELECT Description FROM Item WHERE ItemCode=?");
            stm.setObject(1,txtId.getText());

            ResultSet rst = stm.executeQuery();
            if (rst.next()) {
                String tempdescription = rst.getString(1);
                txtDescription.setText(tempdescription);

            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result set...").show();
                txtId.setText("");
                txtDescription.setText("");
            }


    }
}
