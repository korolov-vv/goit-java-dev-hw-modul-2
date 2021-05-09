package ua.goit.product.storage;

import ua.goit.product.Product;
import ua.goit.product.ProductException;

import java.util.concurrent.ConcurrentHashMap;

public class ProductsStorage implements Storage {
    private static final ProductsStorage PRODUCTS_STORAGE = new ProductsStorage();
    private final ConcurrentHashMap<String, Product> storage = new ConcurrentHashMap<>();

    public static ProductsStorage getProductsStorage() {
        return PRODUCTS_STORAGE;
    }

    @Override
    public void addToStorage(Product prod) {
        storage.putIfAbsent(prod.getName(), prod);
    }

    @Override
    public Product getProduct(String name) {
        if (!storage.containsKey(name)){
            throw new ProductException("The product " + name + " is not found");
        }
       return storage.get(name);
    }
}
