package controller;

import DataBase.DbConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewItemController {
    public AnchorPane AddNewItemContext;
    public TextField txtId;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtPackSize;
    public TextField txtqtyOnHand;

    public void AddItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Item i1=new Item(
              txtId.getText(),txtDescription.getText(),txtPackSize.getText(),Double.parseDouble(txtUnitPrice.getText()),Integer.valueOf(txtqtyOnHand.getText())
        );
            if (new ItemController().SaveItem(i1)){
                new Alert(Alert.AlertType.CONFIRMATION,"Saved..").show();
                txtId.setText("");
                txtDescription.setText("");
                txtUnitPrice.setText("");
                txtPackSize.setText("");
                txtqtyOnHand.setText("");
            }else{
                new Alert(Alert.AlertType.WARNING,"Try Again..").show();
            }


    }

}
