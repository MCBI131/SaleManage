package controller;

import data.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProductManage {
    private Connection connection;

    public ProductManage(Connection connection) {
        this.connection = connection;
    }

    // Them sp
    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO Products (name, price, quantity, supplier_id) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, product.getName());
        statement.setDouble(2, product.getPrice());
        statement.setInt(3, product.getQuantity());
        statement.setInt(4, product.getSupplierId());
        statement.executeUpdate();
        System.out.println("Product added: " + product.getName());
    }

    // cap nhat tt
    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE Products SET name=?, price=?, quantity=?, supplier_id=? WHERE product_id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, product.getName());
        statement.setDouble(2, product.getPrice());
        statement.setInt(3, product.getQuantity());
        statement.setInt(4, product.getSupplierId());
        statement.setInt(5, product.getProductId());
        statement.executeUpdate();
        System.out.println("Product updated: " + product.getName());
    }
    // xoa sp
    public void deleteProduct(int productId) throws SQLException {
        String sql = "DELETE FROM Products WHERE product_id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, productId);
        statement.executeUpdate();
        System.out.println("Product deleted with ID: " + productId);
    }

    // hien thi tt sp trong kho
    public void displayInventory() throws SQLException {
        String sql = "SELECT * FROM Products";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int productId = resultSet.getInt("product_id");
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            int quantity = resultSet.getInt("quantity");
            int supplierId = resultSet.getInt("supplier_id");
            System.out.println("Product ID: " + productId + ", Name: " + name + ", Price: " + price + ", Quantity: " + quantity + ", Supplier ID: " + supplierId);
        }
    }

    // lay sp theo id
   public Product getProductById(int productId) throws SQLException {
        String sql = "SELECT * FROM Products WHERE product_id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, productId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            int quantity = resultSet.getInt("quantity");
            int supplierId = resultSet.getInt("supplier_id");
            return new Product(productId, name, price, quantity, supplierId);
        }
        return null;
    }


    // cap nhat sl sp
    public synchronized void updateStock(int productId, int quantityChange) throws SQLException {
        Product product = getProductById(productId);
        if (product != null) {
            int newQuantity = product.getQuantity() + quantityChange;
            product.setQuantity(newQuantity);
            updateProduct(product);
            System.out.println("Stock updated for product ID: " + productId + " | New quantity: " + newQuantity);
        } else {
            System.out.println("Product not found with ID: " + productId);
        }
    }

    // bao cao doanh thu
    public void generateSalesReport() throws SQLException {
        String sql = "SELECT product_id, SUM(quantity) as total_quantity, SUM(total_price) as total_revenue FROM Orders GROUP BY product_id";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int productId = resultSet.getInt("product_id");
            int totalQuantity = resultSet.getInt("total_quantity");
            double totalRevenue = resultSet.getDouble("total_revenue");
            System.out.println("Product ID: " + productId + " | Total Quantity Sold: " + totalQuantity + " | Total Revenue: " + totalRevenue);
        }
    }

    // Kiem tra muc do ton kho thap
    public void checkLowStock(int threshold) throws SQLException {
        String sql = "SELECT * FROM Products WHERE quantity <= ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, threshold);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int productId = resultSet.getInt("product_id");
            String name = resultSet.getString("name");
            int quantity = resultSet.getInt("quantity");
            System.out.println("Low stock alert for Product ID: " + productId + " | Name: " + name + " | Quantity: " + quantity);
        }
    }
}
