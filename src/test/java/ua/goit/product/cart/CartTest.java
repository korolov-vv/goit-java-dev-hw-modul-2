package ua.goit.product.cart;

import org.junit.Before;
import org.junit.Test;
import ua.goit.product.Product;
import ua.goit.product.storage.StorageFilling;

import java.math.BigDecimal;
import java.util.List;

public class CartTest {

    @Before
    public void fillTestStorage(){
        List<Product> list = List.of(
                new Product("A", new BigDecimal("1.25"), new BigDecimal("3.00"), 3),
                new Product("B", new BigDecimal("4.25"), null, null),
                new Product("C", new BigDecimal("1.00"), new BigDecimal("5.00"), 6),
                new Product("D", new BigDecimal("0.75"), null, null)
        );
        new StorageFilling().fillStorage(list);
    }

    @Test
    public void calculateTotalCost_HappyPath(){
        //When
        Cart cart = new Cart("AABBDCD");
        //Then
        assert cart.calculateTotalCost().equals(new BigDecimal("13.50"));
    }

    @Test
    public void calculateTotalCost_ProductIsNotExistInStorage(){
        //When
        Cart cart = new Cart("AABBDCDf");
        //Then
        assert cart.calculateTotalCost().equals(new BigDecimal("13.50"));
    }
}