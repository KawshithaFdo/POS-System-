package view.tm;

public class CartTm {
    private String code;
    private String Description;
    private int qty;
    private double unitprice;
    private double discount;
    private double total;

    @Override
    public String toString() {
        return "CartTm{" +
                "code='" + code + '\'' +
                ", Description='" + Description + '\'' +
                ", qty=" + qty +
                ", unitprice=" + unitprice +
                ", discount=" + discount +
                ", total=" + total +
                '}';
    }

    public CartTm(String code, String description, int qty, double unitprice, double discount, double total) {
        this.setCode(code);
        setDescription(description);
        this.setQty(qty);
        this.setUnitprice(unitprice);
        this.setDiscount(discount);
        this.setTotal(total);
    }

    public CartTm() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
