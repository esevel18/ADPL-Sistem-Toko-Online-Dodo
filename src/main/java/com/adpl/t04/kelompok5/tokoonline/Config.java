package com.adpl.t04.kelompok5.tokoonline;

import com.adpl.t04.kelompok5.tokoonline.FactoryMethod.*;
import com.adpl.t04.kelompok5.tokoonline.Singleton.DatabaseConnection;

// data to load before app start

public class Config {
    public static void loadData() {
        DatabaseConnection database = DatabaseConnection.getInstance();

        // factory method
        ProductCreator electronicCreator = new ElectronicCreator();
        ProductCreator groceryCreator = new GroceryCreator();
        ProductCreator bookCreator = new BookCreator();
        ProductCreator fashionCreator = new FashionCreator();
        ProductCreator homeLivingCreator = new HomeLivingCreator();
        ProductCreator toyCreator = new ToyCreator();

        database.addProduct(
                electronicCreator.createProduct(
                        1,
                        "Monitor",
                        500_000,
                        40));

        database.addProduct(
                groceryCreator.createProduct(
                        2,
                        "Beras",
                        85_000,
                        100));

        database.addProduct(
                bookCreator.createProduct(
                        3,
                        "Buku Dodo",
                        150_000,
                        25));

        database.addProduct(
                fashionCreator.createProduct(
                        4,
                        "Hoodie Oversize",
                        180_000,
                        30));

        database.addProduct(
                homeLivingCreator.createProduct(
                        5,
                        "Rice Cooker",
                        450_000,
                        15));

        database.addProduct(
                toyCreator.createProduct(
                        6,
                        "LEGO Star Wars",
                        750_000,
                        8));

        database.addProduct(
                electronicCreator.createProduct(
                        7,
                        "Mechanical Keyboard",
                        650_000,
                        20));

        database.addProduct(
                groceryCreator.createProduct(
                        8,
                        "Susu",
                        28_000,
                        60));

        database.addProduct(
                fashionCreator.createProduct(
                        9,
                        "Sepatu Lari",
                        700_000,
                        12));

        database.addProduct(
                homeLivingCreator.createProduct(
                        10,
                        "Lampu Meja LED",
                        120_000,
                        18));
    }
}
