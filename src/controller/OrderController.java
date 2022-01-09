package controller;

import DataBase.DbConnection;
import model.ItemDetails;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrderController {

    public String getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM `Orders` ORDER BY OrderId DESC LIMIT 1").executeQuery();
        if (rst.next()){
            int tempid = Integer.parseInt(rst.getString(1).split("-")[1]);
            tempid=tempid+1;
            if(tempid<9){
                return "O-00"+tempid;
            }else if(tempid<99){
                return "O-0"+tempid;
            }else{
                return "O-"+tempid;
            }

        }else{
            return "O-001";
        }
    }


    public boolean placeorder(Order order) throws SQLException {
        Connection con=null;
        try {
            con=DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stm = con.
                    prepareStatement("INSERT INTO `Orders` VALUES (?,?,?)");
            stm.setObject(1,order.getOrderId());
            stm.setObject(2,order.getOrderDate());
            stm.setObject(3,order.getCustomerId());

           if (stm.executeUpdate()>0){

              if (saveOrderDetail(order.getOrderId(),order.getItems())){
                  con.commit();
                  return true;
              }else{
                  con.rollback();
                  return false;
              }
           }else{
               con.rollback();
               return false;
           }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            con.setAutoCommit(true);
        }

        return false;
    }
    public List<String> getOrderIds(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Orders WHERE CustId='"+id+"'").executeQuery();
        List<String> ids=new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }
    private boolean saveOrderDetail(String orderId, ArrayList<ItemDetails> items) throws SQLException, ClassNotFoundException {
        for (ItemDetails temp:items
             ) {
            PreparedStatement stm = DbConnection.getInstance().getConnection().
                    prepareStatement("INSERT INTO `order detail` VALUES (?,?,?,?)");
            stm.setObject(1,orderId);
            stm.setObject(2,temp.getItemcode());
            stm.setObject(3,temp.getQty());
            stm.setObject(4,temp.getDiscount());

            if (stm.executeUpdate()>0){
                if(updateqty(temp.getItemcode(),temp.getQty())){

                }else{
                    return false;
                }
            }else {
                return false;
            }
        }
        return true;
    }

    private boolean updateqty(String itemcode,int qty) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().
                prepareStatement("UPDATE Item SET QtyOnHand=(QtyOnHand-" + qty + ")WHERE ItemCode='" + itemcode + "'");
           return stm.executeUpdate()>0;
    }
}
