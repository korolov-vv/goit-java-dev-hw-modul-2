package ua.goit.product.storage;

import ua.goit.product.Product;

import java.math.BigDecimal;
import java.util.List;

public class StorageFilling {
    private static StorageFilling storageFilling = new StorageFilling();
    private List<Product> list = List.of(
            new Product("A", new BigDecimal("1.25"), new BigDecimal("3.00"), 3),
            new Product("B", new BigDecimal("4.25"), null, null),
            new Product("C", new BigDecimal("1.00"), new BigDecimal("5.00"), 6),
            new Product("D", new BigDecimal("0.75"), null, null)
    );

    public StorageFilling() {
        fillStorage(list);
    }

    public void fillStorage(List<Product> listOfProducts) {
        for (Product product : listOfProducts) {
                ProductsStorage.getProductsStorage().addToStorage(product);
        }
    }
}
