package com.adpl.t04.kelompok5.tokoonline.Decorator;

// base decorator
public abstract class ItemDecorator implements ItemComponent{
    protected ItemComponent wrappee;

    public ItemDecorator(ItemComponent item){
        this.wrappee = item;
    }
}
