package com.adpl.t04.kelompok5.tokoonline.Command;

public class CartInvoker{
    private CartCommand cartCommand;

    public void setCartCommand(CartCommand cartCommand) {
        this.cartCommand = cartCommand;
    }

    public void runCommand() {
        cartCommand.execute();
    }
}
