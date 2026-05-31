package com.adpl.t04.kelompok5.tokoonline.FactoryMethod;
// only behavior diff
// gunakan interface dari pada abstract class
public interface ProductCreator {
    public Product createProduct(int id, String name, double price, int stock);
}
