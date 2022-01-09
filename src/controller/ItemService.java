package controller;

import model.Item;

import java.sql.SQLException;

public interface ItemService {
    public boolean SaveItem(Item i) throws SQLException, ClassNotFoundException;
    public boolean UpdateItem(Item i) throws SQLException, ClassNotFoundException;
    public Item GetItem(String id) throws SQLException, ClassNotFoundException;

}
