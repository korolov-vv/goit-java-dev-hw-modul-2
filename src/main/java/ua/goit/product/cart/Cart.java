package ua.goit.product.cart;

import ua.goit.product.Product;
import ua.goit.product.ProductException;
import ua.goit.product.storage.ProductsStorage;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Cart {

    private final ProductsStorage products = ProductsStorage.getProductsStorage();

    private final String shoppingList;

    public Cart(String shoppingList) {
        this.shoppingList = shoppingList;
    }

    public Map<Product, Long> separateProducts() {
        Map<Product, Long> cart;
        cart = Arrays.stream(shoppingList.split(""))
                .map((s) -> {
                    Product product;
                            try {
                                product = products.getProduct(s);
                            } catch (ProductException e) {
                                System.out.println(e.getMessage());
                                product = new Product(s, new BigDecimal("0"), null, null);
                            }
                            return product;
                        })
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return cart;
    }

    public BigDecimal calculateTotalCost() {
        Map<Product, Long> thisCart = separateProducts();
        Set<Product> prod = thisCart.keySet();
        final BigDecimal[] totalCost = {new BigDecimal("0")};

        prod.forEach((el) -> {
                    long quantity = thisCart.get(el);
                    try {
                        totalCost[0] = totalCost[0].add(el.calculateProductCost(quantity));
                    }catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                }
        );
//        System.out.println("Total coast for " + shoppingList + " " + totalCost[0]);
        return totalCost[0];
    }
}
