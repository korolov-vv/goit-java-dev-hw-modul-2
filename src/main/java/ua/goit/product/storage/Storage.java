package ua.goit.product.storage;

import ua.goit.product.Product;

public interface Storage {
    void addToStorage(Product product);

    Product getProduct(String name);
}
