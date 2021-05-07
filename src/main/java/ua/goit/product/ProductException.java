package ua.goit.product;

import java.util.function.Supplier;

public class ProductException extends RuntimeException {
    public ProductException(String s) {
        super(s);
    }
}
