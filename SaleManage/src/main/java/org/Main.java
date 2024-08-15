package org.example;

import controller.ProductManage;
import data.Product;
import dataconnection.DatabaseConnection;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try {
            Connection connection = new DatabaseConnection().getConnection();
            ProductManage inventoryManager = new ProductManage(connection);

            // Them san pham
            Product product = new Product(1, "Laptop", 999.99, 50, 1);
            inventoryManager.addProduct(product);

            // Hien thi ton kho
            inventoryManager.displayInventory();

            //Cap nhat thong tin san pham
            Product productUpdate = new Product(1, "Iphone 24", 5000, 100, 1);
            inventoryManager.updateProduct(productUpdate);

            // Xoa san pham
            //inventoryManager.deleteProduct(1);

            // Cap nhat so luong san pham
            Thread thread1 = new Thread(() -> {
                try {
                    inventoryManager.updateStock(1, -5);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            Thread thread2 = new Thread(() -> {
                try {
                    inventoryManager.updateStock(1, -3);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

            // ktra ton kho thap
            inventoryManager.checkLowStock(1100);

            // Tao bc doanh thu
            inventoryManager.generateSalesReport();

        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
