package ua.goit;

import ua.goit.input.Console;
import ua.goit.product.cart.Cart;
import ua.goit.product.storage.ProductsStorage;
import ua.goit.product.storage.StorageFilling;

import java.util.Locale;

public class Controler {
    Console console;
    ProductsStorage storage;

    public Controler(Console console) {
        this.console = console;
        this.storage = ProductsStorage.getProductsStorage();
        new StorageFilling();
    }

    public void run() {
        console.write("Please enter products:");
        String products = console.read();
        Cart thisCart = new Cart(products.toUpperCase(Locale.ROOT));
        System.out.println(thisCart.calculateTotalCost());
    }
}