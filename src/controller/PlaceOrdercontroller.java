package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Customer;
import model.Item;
import model.ItemDetails;
import model.Order;
import view.tm.CartTm;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlaceOrdercontroller {
    public TextField txtqty;
    public ComboBox<String> cmbItem;
    public TextField txtqtyOnHand;
    public TextField txtunitprice;
    public ComboBox<String> cmbCustomer;
    public TextField txtname;
    public TextField txtaddress;
    public Label OrderId;
    public Label Orderdate;
    public TableView<CartTm> tblcart;
    public TableColumn colItemId;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public Label OrderTime;
    public Label lblDiscount;
    public Label lblTotal;
    public TableColumn colDiscount;
    public TableColumn colDescription;
    public TextField txtDiscription;
    public TextField txttitle;
    public TextField txtPackSize;
    public TextField txtDiscount;
    public Label remainingAmount;
    public TextField txtamountgiven;

    int cartSelectedRowForRemove=-1;

    public void initialize(){
        DateandTime();
        setOrderid();

        colItemId.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitprice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        try {
            loadCustomerIds();
            loadItemIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setCustomerData((String) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        cmbItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemData((String) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        tblcart.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
                cartSelectedRowForRemove= (int) newValue;
        });

    }

    private void setOrderid() {
        try {
            OrderId.setText(new OrderController().getOrderId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setItemData(String itemId) throws SQLException, ClassNotFoundException {
        Item i1=new ItemController().GetItem(itemId);
        if(i1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result set...").show();
        }else{
            txtDiscription.setText(i1.getDescription());
            txtPackSize.setText(i1.getPacksize());
            txtqtyOnHand.setText(String.valueOf(i1.getQtyonhand()));
            txtunitprice.setText(String.valueOf(i1.getUnitprice()));
        }
    }

    private void setCustomerData(String customerid) throws SQLException, ClassNotFoundException {
        Customer c1=new CustomerController().getCustomer(customerid);
        if(c1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result set...").show();
        }else{
            txtname.setText(c1.getName());
            txtaddress.setText(c1.getAddress());
            txttitle.setText(c1.getTitle());

        }
    }

    private void loadItemIds() throws SQLException, ClassNotFoundException {
        List<String> itemIds = new ItemController().getItemIds();
        cmbItem.getItems().addAll(itemIds);
    }

    public void loadCustomerIds() throws SQLException, ClassNotFoundException {
        List<String> customerIds = new CustomerController().getCustomerIds();
        cmbCustomer.getItems().addAll(customerIds);
    }

    private void DateandTime() {
        Date date=new Date();
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        Orderdate.setText(f.format(date));

        Timeline time=new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime currentTime = LocalTime.now();
            OrderTime.setText(
                    currentTime.getHour()+":"+currentTime.getMinute()+":"+
                            currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    ObservableList<CartTm> oblist= FXCollections.observableArrayList();
    double reamount=0;

    public void addtocart(ActionEvent actionEvent) {
        String description=txtDiscription.getText();
        int qtyonhand=Integer.parseInt(txtqtyOnHand.getText());
        double unitprice=Double.parseDouble(txtunitprice.getText());
        int qtyforcustomer=Integer.parseInt(txtqty.getText());
        double discount=Double.parseDouble(txtDiscount.getText());




        CartTm tm=new CartTm(
          cmbItem.getValue(),
          description,
          qtyforcustomer,
          unitprice,
          discount,
           qtyforcustomer * unitprice
        );
        int rowid=isExists(tm);
        if (rowid==-1){
            oblist.add(tm);
        }else{
            CartTm temp=oblist.get(rowid);
            CartTm newTm=new CartTm(
                    temp.getCode(),
                    temp.getDescription(),
                    temp.getQty()+qtyforcustomer,
                    unitprice,
                    discount+temp.getDiscount(),
                    (qtyforcustomer * unitprice)+temp.getTotal()
            );

            if(qtyonhand<temp.getQty()){
                new Alert(Alert.AlertType.WARNING,"Invalid QTY..").show();
                return;
            }

            oblist.remove(rowid);
            oblist.add(newTm);
        }

        tblcart.setItems(oblist);
        calculateCost();
    }
    void calculateCost(){
        double ttl=0;
        double disc=0;
        for (CartTm tm:oblist
        ) {
            ttl+=tm.getTotal();
            disc+=tm.getDiscount();
        }
        reamount=ttl-disc;
        lblDiscount.setText(disc+"/=");
        lblTotal.setText(reamount+"/=");
    }
    private int isExists(CartTm tm){
        for (int i = 0; i <oblist.size() ; i++) {
            if (tm.getCode().equals(oblist.get(i).getCode())){
                return i;
            }
        }
        return -1;
    }

    public void clear(ActionEvent actionEvent) {
        if(cartSelectedRowForRemove==-1){
            new Alert(Alert.AlertType.WARNING,"Please Select a row..").show();
        }else{
            oblist.remove(cartSelectedRowForRemove);
            calculateCost();
            tblcart.refresh();
        }
    }

    public void PlaceOrder(ActionEvent actionEvent) throws IOException {
        ArrayList<ItemDetails> items=new ArrayList<>();
        double disc=0;
        for (CartTm tempTm:oblist
             ) {
            disc+=tempTm.getDiscount();
            items.add(
                    new ItemDetails(
                            tempTm.getCode(),
                            tempTm.getQty(),
                            disc
                    )
            );
        }

        Order order=new Order(
                OrderId.getText(),
                Orderdate.getText(),
                cmbCustomer.getValue(),
                items
        );
        try {
            if (new OrderController().placeorder(order)){
                new Alert(Alert.AlertType.CONFIRMATION,"Success").show();
                setOrderid();
                txttitle.setText("");
                txtname.setText("");
                txtaddress.setText("");
                txtDiscription.setText("");
                txtPackSize.setText("");
                txtqtyOnHand.setText("");
                txtqty.setText("");
                txtunitprice.setText("");
                txtDiscount.setText("");
                remainingAmount.setText("0");
                txtamountgiven.setText("");
                //for (int i=0;i<oblist.size();i++) {
                    oblist.remove(0, oblist.size());
                    tblcart.refresh();
               // }
                calculateCost();


            }else{
                new Alert(Alert.AlertType.WARNING,"Try Again..").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void amountgiven(ActionEvent actionEvent) {
        if (txtamountgiven.getText().equals("")){
            new Alert(Alert.AlertType.WARNING,"Please Enter Amount Given.").show();
        }else {
            remainingAmount.setText(String.valueOf(Double.parseDouble(txtamountgiven.getText()) - (reamount)));
        }
    }
}
