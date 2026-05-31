package com.adpl.t04.kelompok5.tokoonline.Decorator;

public class GiftWrap extends ItemDecorator{
    public GiftWrap(ItemComponent item){
        super(item);
    }

    @Override
    public String getDetails() {
        return wrappee.getDetails() + " + Gift Wrap";
    }

    @Override
    public double getPrice() {
        return wrappee.getPrice() + 5000;
    }
}
