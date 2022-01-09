package controller;

import DataBase.DbConnection;
import model.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemController implements ItemService{
    public List<String> getItemIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item ").executeQuery();
        List<String> ids=new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

    @Override
    public boolean SaveItem(Item i) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO Item VALUES (?,?,?,?,?)");
        stm.setObject(1,i.getItemCode());
        stm.setObject(2,i.getDescription());
        stm.setObject(3,i.getPacksize());
        stm.setObject(4,String.valueOf(i.getUnitprice()));
        stm.setObject(5,String.valueOf(i.getQtyonhand()));

        return stm.executeUpdate()>0;
    }

    @Override
    public boolean UpdateItem(Item i) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Item SET Description=?,Packsize=?,UnitPrice=?,QtyOnHand=? WHERE ItemCode=?");
        stm.setObject(1,i.getDescription());
        stm.setObject(2,i.getPacksize());
        stm.setObject(3,String.valueOf(i.getUnitprice()));
        stm.setObject(4,String.valueOf(i.getQtyonhand()));
        stm.setObject(5,i.getItemCode());

        return stm.executeUpdate()>0;
    }

    @Override
    public Item GetItem(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item WHERE ItemCode=?");
        stm.setObject(1,id);
        ResultSet rst= stm.executeQuery();
        if(rst.next()){
            return new Item(rst.getString(1),rst.getString(2),rst.getString(3),
                    rst.getDouble(4),rst.getInt(5)

            );


        }else{
            return null;
        }
    }


}
