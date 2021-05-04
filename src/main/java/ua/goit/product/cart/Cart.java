package ua.goit.product.cart;

import ua.goit.product.storage.ProductsStorage;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Cart {

    private final ProductsStorage products = ProductsStorage.getProductsStorage();

    private final String shoppingList;

    public Cart(String shoppingList) {
        this.shoppingList = shoppingList;
    }

    public Map<String, Long> separateProducts() {
        Map<String, Long> cart;
        cart = Arrays.stream(shoppingList.split(""))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return cart;
    }

    public BigDecimal calculateTotalCost() {
        Map<String, Long> thisCart = separateProducts();
        Iterator<Map.Entry<String, Long>> i = thisCart.entrySet().iterator();
        BigDecimal totalCost = new BigDecimal("0");
        while (i.hasNext()) {
            totalCost.add(products.getProduct(i.toString()).calculateProductCost(thisCart.get(i)));
        }
        return totalCost;
    }
}
