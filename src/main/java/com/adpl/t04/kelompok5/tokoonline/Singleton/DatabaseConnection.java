package com.adpl.t04.kelompok5.tokoonline.Singleton;

import java.util.ArrayList;
import java.util.List;

import com.adpl.t04.kelompok5.tokoonline.Builder.Transaction;
import com.adpl.t04.kelompok5.tokoonline.FactoryMethod.Product;

// koneksi database hanya boleh satu instance saja
// kita tidak ingin membuat koneksi secara terus menerus
public class DatabaseConnection {

    private static DatabaseConnection instance;

    private List<Product> products;
    private List<Transaction> transactionHistory;

    private DatabaseConnection() {
        products = new ArrayList<>();
        transactionHistory = new ArrayList<>();
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void showProduct() {
        System.out.println();
        System.out.println("============== LIST PRODUCT ==============");

        for (Product product : products) {
            if (product.getStatus()) {
                System.out.println(product);
                System.out.println();
            }
        }
    }

    public Product findProductById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    // update stock
    public boolean updateProductStock(Product product, int amount) {
        for (Product p : products) {
            if (p.getId() == product.getId()) {
                // stock tidak cukup
                if (p.getStock() < amount) {
                    return false;
                }
                p.setStock(p.getStock() - amount);
                // jika stock habis
                if (p.getStock() == 0) {
                    p.setStatus(false);
                }
                return true;
            }
        }
        return false;
    }

    public DatabaseConnection getCopyOfDatabase(){
        DatabaseConnection copy = new DatabaseConnection();
        copy.products = new ArrayList<>(this.products);
        copy.transactionHistory = new ArrayList<>(this.transactionHistory);
        return copy;
    }

    // ================= TRANSACTION HISTORY =================

    public void addTransactionHistory(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void showTransactionHistory() {
        System.out.println();
        System.out.println("========== TRANSACTION HISTORY ==========");

        if (transactionHistory.isEmpty()) {
            System.out.println("Belum ada transaksi.");
            return;
        }

        int i = 1;

        for (Transaction transaction : transactionHistory) {
            System.out.println("Transaksi #" + i++);
            System.out.println(transaction);
            System.out.println();
        }
    }
}