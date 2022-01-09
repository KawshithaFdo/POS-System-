package model;

public class ItemDetails {
    private String itemcode;
    private int qty;
    private double discount;

    @Override
    public String toString() {
        return "ItemDetails{" +
                "itemcode='" + itemcode + '\'' +
                ", qty=" + qty +
                ", discount=" + discount +
                '}';
    }

    public ItemDetails(String itemcode, int qty, double discount) {
        this.setItemcode(itemcode);
        this.setQty(qty);
        this.setDiscount(discount);
    }

    public ItemDetails() {
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
