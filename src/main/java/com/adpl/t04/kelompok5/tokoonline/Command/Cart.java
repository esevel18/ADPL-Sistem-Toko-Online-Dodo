package com.adpl.t04.kelompok5.tokoonline.Command;

import java.util.List;

import com.adpl.t04.kelompok5.tokoonline.Decorator.TransactionItem;

import java.util.ArrayList;

public class Cart {
    private List<TransactionItem> items = new ArrayList<>();

    public void addTransactionItem(TransactionItem transactionItem){
        items.add(transactionItem);
        System.out.println(transactionItem.getProduct().getName() + " berhasil ditambahkan ke keranjang!");
    }

    public void removeTransactionItem(TransactionItem transactionItem){
        items.remove(transactionItem);
        System.out.println(transactionItem.getProduct().getName() + " berhasil dihapus dari keranjang!");
    }

    public void showCart(){
        System.out.println("\n==== Isi Keranjang ===");
        if(items.isEmpty()){
            System.out.println("Keranjang kosong!");
            return;
        }

        double total = 0;
        for(TransactionItem item : items) {
            System.out.println(item);
            total += item.getTotalPrices();
        }

        System.out.println("--------------------------");
        System.out.println("Total: Rp." + total);
    }

    public void clearCart(){
        items.clear();
    }

    public List<TransactionItem> getItems() {
        return items;
    }
}
