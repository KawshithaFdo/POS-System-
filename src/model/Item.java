package model;

import java.util.Objects;

public class Item {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(itemCode, item.itemCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemCode);
    }

    private String itemCode;
    private String description;
    private String packsize;
    private double unitprice;
    private int Qtyonhand;

    public Item() {
    }

    public Item(String itemCode, String description, String packsize, double unitprice, int qtyonhand) {
        this.setItemCode(itemCode);
        this.setDescription(description);
        this.setPacksize(packsize);
        this.setUnitprice(unitprice);
        setQtyonhand(qtyonhand);
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPacksize() {
        return packsize;
    }

    public void setPacksize(String packsize) {
        this.packsize = packsize;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public int getQtyonhand() {
        return Qtyonhand;
    }

    public void setQtyonhand(int qtyonhand) {
        Qtyonhand = qtyonhand;
    }
}
