package com.adpl.t04.kelompok5.tokoonline.FactoryMethod;

public class BookCreator implements ProductCreator {
    private final Category category = new Category("Book");
    @Override
    public Product createProduct(int id, String name, double price, int stock) {
        return new Product(id, name, price, stock, category);
    }
}
