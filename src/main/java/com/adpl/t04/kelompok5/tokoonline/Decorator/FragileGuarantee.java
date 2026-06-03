package com.adpl.t04.kelompok5.tokoonline.Decorator;

public class FragileGuarantee extends ItemDecorator{
    public FragileGuarantee(ItemComponent item){
        super(item);
    }

    @Override
    public String getDetails() {
        return wrappee.getDetails() + " + Garansi Pecah Belah ";
    }

    @Override
    public double getPrice() {
        return wrappee.getPrice() + 50_000;
    }
}
