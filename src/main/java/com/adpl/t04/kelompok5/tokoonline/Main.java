package com.adpl.t04.kelompok5.tokoonline;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.adpl.t04.kelompok5.tokoonline.Command.*;
import com.adpl.t04.kelompok5.tokoonline.Decorator.*;
import com.adpl.t04.kelompok5.tokoonline.Strategy.*;
import com.adpl.t04.kelompok5.tokoonline.FactoryMethod.Product;
import com.adpl.t04.kelompok5.tokoonline.Singleton.DatabaseConnection;

public class Main {

    public static void main(String[] args) {
        // singleton 
        DatabaseConnection database = DatabaseConnection.getInstance();
        // factory method
        Config.loadData();

        Scanner sc = new Scanner(System.in);

        System.out.println("======================================================================");
        System.out.println("\t\tSELAMAT DATANG DI TOKO ONLINE DODO\t\t");
        System.out.println("======================================================================");

        while (true) {
            System.out.println();
            System.out.println("Silahkan pilih menu berikut ini...");
            System.out.println("1. Lihat produk yang tersedia");
            System.out.println("2. Lihat riwayat transaksi");
            System.out.println("3. Keluar");

            System.out.print("Masukkan pilihan anda: ");
            String cmd = sc.nextLine();

            if (cmd.equals("3")) {
                System.out.println("Terima kasih telah menggunakan aplikasi toko online dodo.");
                break;
            }

            switch (cmd) {
                case "1" -> {
                    // command
                    Cart cart = new Cart();

                    database.showProduct();

                    System.out.println();
                    System.out.println("Ketik '$' untuk melihat keranjang / checkout");

                    while (true) {
                        System.out.print("Pilih produk yang diinginkan: ");
                        String productCode = sc.nextLine().trim().toLowerCase();

                        if (productCode.equals("$")) {
                            break;
                        }

                        System.out.print("Berapa banyak yang diinginkan: ");
                        int amount;

                        try {
                            amount = Integer.parseInt(sc.nextLine());

                            if (amount <= 0) {
                                System.out.println("Jumlah harus lebih dari 0!");
                                continue;
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Jumlah harus berupa angka!");
                            continue;
                        }

                        Product product;

                        try {
                            product = database.findProductById(Integer.parseInt(productCode));
                        } catch (NumberFormatException e) {
                            System.out.println("ID produk harus angka!");
                            continue;
                        }

                        if (product == null) {
                            System.out.println("Produk tidak ditemukan!");
                            continue;
                        }

                        if (product.getStock() < amount) {
                            System.out.println("Stock tidak mencukupi!");
                            continue;
                        }

                        TransactionItem transactionItem = new TransactionItem(product, amount);
                        CartCommand addCommand = new AddToCartCommand(cart, transactionItem);

                        CartInvoker invoker = new CartInvoker();
                        invoker.setCartCommand(addCommand);

                        invoker.runCommand();
                    }

                    // ================= CART =================

                    System.out.println();
                    System.out.println("============== KERANJANG ==============");

                    cart.showCart();

                    // ================= CHECKOUT =================
                    System.out.println();
                    System.out.println("============== CHECKOUT ==============");

                    // Transaction item yang sudah dibuat akan dibungkus dengan beberapa behavior tambahan
                    // bungkus kado, garansi, strategi pembayaran dan strategi pengiriman

                    // decorator
                    System.out.print("Gunakan bungkus kado? (y/n): ");
                    String kado = sc.nextLine().trim().toLowerCase();

                    while (!kado.equals("y") && !kado.equals("n")) {
                        System.out.println("Input tidak valid!");
                        System.out.println();
                        System.out.print("Gunakan bungkus kado? (y/n): ");
                        kado = sc.nextLine().trim().toLowerCase();
                    }

                    System.out.print("Tambahkan garansi pecah belah? (y/n): ");
                    String guarantee = sc.nextLine().trim().toLowerCase();

                    while (!guarantee.equals("y") && !guarantee.equals("n")) {
                        System.out.println("Input tidak valid!");
                        System.out.print("Tambahkan garansi pecah belah? (y/n): ");
                        guarantee = sc.nextLine().trim().toLowerCase();
                    }

                    // Strategy
                    System.out.println();
                    System.out.println("Pilih pengiriman:");
                    System.out.println("1. Regular");
                    System.out.println("2. Express");

                    System.out.print("Pilihan: ");
                    String deliveryMethod = sc.nextLine().trim();

                    while (!deliveryMethod.equals("1") && !deliveryMethod.equals("2")) {
                        System.out.println("Input tidak valid!");
                        System.out.println();
                        System.out.println("Pilih pengiriman:");
                        System.out.println("1. Regular");
                        System.out.println("2. Express");

                        System.out.print("Pilihan: ");
                        deliveryMethod = sc.nextLine().trim();
                    }

                    System.out.println();
                    System.out.println("Pilih metode pembayaran:");
                    System.out.println("1. Virtual Account");
                    System.out.println("2. Gopay");

                    System.out.print("Pilihan: ");
                    String paymentMethod = sc.nextLine().trim();

                    while (!paymentMethod.equals("1") && !paymentMethod.equals("2")) {
                        System.out.println("Input tidak valid!");
                        System.out.println();
                        System.out.println("Pilih metode pembayaran:");
                        System.out.println("1. Virtual Account");
                        System.out.println("2. Gopay");

                        System.out.print("Pilihan: ");
                        paymentMethod = sc.nextLine().trim();
                    }

                    System.out.println();
                    System.out.print("Lakukan checkout? (y/n): ");
                    String checkout = sc.nextLine().trim().toLowerCase();

                    while (!checkout.equals("y") && !checkout.equals("n")) {
                        System.out.println("Input tidak valid!");
                        System.out.println();
                        System.out.print("Lakukan checkout? (y/n): ");
                        checkout = sc.nextLine().trim().toLowerCase();
                    }

                    if (checkout.equals("y")) {
                        List<ItemComponent> finalItems = new ArrayList<>();

                        // tambahkan decorator
                        for(TransactionItem item : cart.getItems()) {
                            ItemComponent decoratedItem = item;
                            if(kado.equals("y")) {
                                decoratedItem = new GiftWrap(decoratedItem);
                            }
                            if(guarantee.equals("y")) {
                                decoratedItem = new FragileGuarantee(decoratedItem);
                            }
                            finalItems.add(decoratedItem);
                        }

                        // hitung total
                        double subtotal = 0;
                        for(ItemComponent item : finalItems) {
                            subtotal += item.getPrice();
                        }

                        // tambah ongkir
                        DeliveryContext deliveryStrategy = new DeliveryContext();

                        if(deliveryMethod.equals("1")){
                            deliveryStrategy.setDeliveryStrategy(new RegularDelivery());
                        } else {
                            deliveryStrategy.setDeliveryStrategy(new ExpressDelivery());
                        }

                        double deliveryFee = deliveryStrategy.calculateFee();

                        double total = subtotal + deliveryFee;
                        
                        System.out.println("Silahkan lakukan pembayaran anda...");
                        
                        // suruh user bayar sesuai dengan strategi yang dipilih
                        PaymentContext payment = new PaymentContext();
                        if(paymentMethod.equals("1")){
                            payment.setPayementStrategy(new VirtualAccount());
                        } else {
                            payment.setPayementStrategy(new Gopay());
                        }

                        payment.executeStrategy(total);

                        for(TransactionItem item : cart.getItems()){
                            database.updateProductStock(item.getProduct(), item.getQuantitiy());
                        }
                        database.addTransactionHistory(new Transaction(finalItems, deliveryStrategy, payment, subtotal, deliveryFee, total));
                        cart.clearCart();
                        System.out.println("Checkout berhasil!");
                        deliveryStrategy.deliver();
                    } else {
                        System.out.println("Checkout dibatalkan.");
                    }
                }

                case "2" -> {
                    // tampilkan riwayat transaksi
                    database.showTransactionHistory();
                }

                default -> {
                    System.out.println("Input tidak valid!");
                }
            }
        }

        sc.close();
    }
}