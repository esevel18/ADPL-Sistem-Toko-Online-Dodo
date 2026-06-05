package com.adpl.t04.kelompok5.tokoonline;

import java.util.Scanner;

import com.adpl.t04.kelompok5.tokoonline.Command.*;
import com.adpl.t04.kelompok5.tokoonline.Decorator.*;
import com.adpl.t04.kelompok5.tokoonline.Facade.CheckoutFacade;
import com.adpl.t04.kelompok5.tokoonline.FactoryMethod.Config;
import com.adpl.t04.kelompok5.tokoonline.FactoryMethod.Product;
import com.adpl.t04.kelompok5.tokoonline.Singleton.DatabaseConnection;

/*
    LIMITASI PROGRAM KAMI:
    - Program tidak bisa berjalan mundur, artinya ketika user sudah di halaman checkout, ia tidak
      bisa kembali ke halaman pemilihan produk
    - Program tidak bisa update tampilan stok produk secara realtime (saat user sedang meng-query), karena update
      baru akan terjadi pada akhir checkout
*/

public class Main {
    public static void main(String[] args) {
        // singleton agar hanya ada satu instansi yang konde ke db
        DatabaseConnection database = DatabaseConnection.getInstance();
        // factory method untuk creational data product yang ada di store
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
                    System.out.println("Ketik '$' untuk melihat keranjang dan checkout");
                    // update stock realtime (state yang jika user checkout batal, database asli tidak keganti)
                    DatabaseConnection copyOfDatabase = database.getCopyOfDatabase();

                    while (true) {
                        System.out.print("Pilih produk yang diinginkan: ");
                        String productCode = sc.nextLine().trim().toLowerCase();

                        if (productCode.equals("$")) {
                            break;
                        }

                        int id;
                        try {
                            id = Integer.parseInt(productCode);
                        } catch (Exception e) {
                            System.out.println("Input harus sesuai nomor yang tertera!!");
                            continue;
                        }

                        Product product;
                        product = database.findProductById(id);
                        if(product == null){
                            System.out.println("Produk tidak ditemukan, silahkan pilih kembali..");
                            continue;
                        }
                        
                        System.out.print("Berapa banyak yang diinginkan: ");
                        int amount;

                        try {
                            amount = Integer.parseInt(sc.nextLine());
                            if (amount <= 0) {
                                System.out.println("Jumlah harus lebih dari 0!");
                                continue;
                            } else if(amount > product.getStock()){
                                System.out.println("Jumlah permintaan melebihi stok!");
                                continue;
                            }
                            copyOfDatabase.updateProductStock(product, amount);
                        } catch (NumberFormatException e) {
                            System.out.println("Jumlah harus berupa angka!");
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
                    // yaitu bungkus kado dan garansi

                    // decorator
                    System.out.print("Gunakan bungkus kado? (y/n): ");
                    String giftWrapper = sc.nextLine().trim().toLowerCase();

                    while (!giftWrapper.equals("y") && !giftWrapper.equals("n")) {
                        System.out.println("Input tidak valid!");
                        System.out.println();
                        System.out.print("Gunakan bungkus kado? (y/n): ");
                        giftWrapper = sc.nextLine().trim().toLowerCase();
                    }

                    System.out.print("Tambahkan garansi pecah belah? (y/n): ");
                    String guarantee = sc.nextLine().trim().toLowerCase();

                    while (!guarantee.equals("y") && !guarantee.equals("n")) {
                        System.out.println("Input tidak valid!");
                        System.out.print("Tambahkan garansi pecah belah? (y/n): ");
                        guarantee = sc.nextLine().trim().toLowerCase();
                    }

                    // Strategy pengiriman dan pembayaran
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
                    String checkoutStatus = sc.nextLine().trim().toLowerCase();

                    while (!checkoutStatus.equals("y") && !checkoutStatus.equals("n")) {
                        System.out.println("Input tidak valid!");
                        System.out.println();
                        System.out.print("Lakukan checkout? (y/n): ");
                        checkoutStatus = sc.nextLine().trim().toLowerCase();
                    }

                    if (checkoutStatus.equals("y")) {
                        // facade
                        CheckoutFacade checkoutFacade = new CheckoutFacade();
                        // tambahkan decorator
                        checkoutFacade.addAddons(cart, giftWrapper, guarantee);

                        // tambah ongkir
                        checkoutFacade.addShippingMethod(deliveryMethod);
                        
                        System.out.println("Silahkan lakukan pembayaran anda...");
                        // suruh user bayar sesuai dengan strategi yang dipilih
                        checkoutFacade.addPaymentMethod(paymentMethod);

                        // bayar
                        checkoutFacade.pay();
                        // =================== END OF CHECKOUT LOGIC ====================

                        // Perbarui database
                        for(TransactionItem item : cart.getItems()){
                            database.updateProductStock(item.getProduct(), item.getQuantitiy());
                        }
                        database.addTransactionHistory(checkoutFacade.getTransaction());
                        System.out.println("Checkout berhasil!");
                    } else {
                        System.out.println("Checkout dibatalkan.");
                    }
                    cart.clearCart();
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