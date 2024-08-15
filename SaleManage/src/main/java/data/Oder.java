package data;

import java.util.Date;

public class Oder {
    private int orderId;
    private Product product;
    private int quantity;
    private Date orderDate;
    private double totalPrice;

    public void Order(int orderId, Product product, int quantity, Date orderDate) {
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.totalPrice = product.getPrice() * quantity;
    }

    // Getters và Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

}
