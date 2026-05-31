package com.adpl.t04.kelompok5.tokoonline.Command;

import com.adpl.t04.kelompok5.tokoonline.Decorator.TransactionItem;

public class AddToCartCommand implements CartCommand{
    private Cart cart;
    private TransactionItem transactionItem;

    public AddToCartCommand(Cart cart, TransactionItem transactionItem){
        this.cart = cart;
        this.transactionItem = transactionItem;
    }

    @Override
    public void execute() {
        cart.addTransactionItem(transactionItem);
    }
}
