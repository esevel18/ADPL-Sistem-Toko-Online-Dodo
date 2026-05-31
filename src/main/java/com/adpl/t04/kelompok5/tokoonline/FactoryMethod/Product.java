package com.adpl.t04.kelompok5.tokoonline.FactoryMethod;

// Factory method design pattern
// kita mau agar less coupling antara concreate product dengan abstract classnya
// ketika ada semakin banyak produk yang ada -> proses creational akan semakin sulit untuk dilakukan
// memisahkan antara business logic dengan creational kita
// karena di setiap kaegori ada aturan yang berbeda beda
// dan akan semakin banyak kode yang ditulis secara berulang ulang

// pakai concrete class instead of interface
// karena hanya differ by data, bukan behavior

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private boolean status = true;
    private Category category;

    public Product(int id, String name, double price, int stock, Category category){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    @Override
    public String toString() {
        String s = String.format(
            "| no: %d | name: %s | price: %.2f | stock: %d | category: %s |",
            id, name, price, stock, category.getName()
        );

        return s;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }
    
    public Category getCategory() {
        return category;
    }

    public boolean getStatus(){
        return status;
    }

    //setter
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
}
