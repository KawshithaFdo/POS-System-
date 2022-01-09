package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Item;

import java.sql.*;

public class ModifyItemsController {
    public AnchorPane ModifyItemContext;
    public TextField txtId;
    public TextField txtDescription;
    public TextField txtPackSize;
    public TextField txtQtyOnhand;
    public TextField txtUnitPrice;

    public void ModifyItems(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {


        Item i1=new Item(txtId.getText(),txtDescription.getText(),txtPackSize.getText(),Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQtyOnhand.getText())
        );

        if(new ItemController().UpdateItem(i1)){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated...").show();
            txtId.setText("");
            txtDescription.setText("");
            txtPackSize.setText("");
            txtQtyOnhand.setText("");
            txtUnitPrice.setText("");
        }else{
            new Alert(Alert.AlertType.WARNING,"Try Again...").show();
        }

    }


    public void LoadItemData(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {


        String ItemId=txtId.getText();
        Item i1=new ItemController().GetItem(ItemId);
        if(i1==null){

            new Alert(Alert.AlertType.WARNING,"Empty Result Set..").show();


        }else{
            setData(i1);
        }


    }
    public void setData(Item i){
        txtId.setText(i.getItemCode());
        txtDescription.setText(i.getDescription());
        txtPackSize.setText(i.getPacksize());
        txtQtyOnhand.setText(String.valueOf(i.getQtyonhand()));
        txtUnitPrice.setText(String.valueOf(i.getUnitprice()));

    }
}
