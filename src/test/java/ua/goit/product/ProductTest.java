package ua.goit.product;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class ProductTest {
    String productName;
    Product product;

    @Before
    public void createProduct() {
        productName = "X";
        product = new Product(productName, new BigDecimal("1"), new BigDecimal("4"), 5);
    }

    @Test
    public void test_getName_HappyPath() {
        //Given
        //When
        //Then
        assert product.getName().equals(productName);
    }

    @Test
    public void testCalculateProductCost_QuantityLessThen_0() {
        long expectedQuantity = -12;
        //Given
        //When
        //Then
        Assert.assertThrows("Wrong product quantity, must be > 0", ProductException.class,
                () -> product.calculateProductCost(expectedQuantity));
    }

    @Test
    public void testCalculateProductCost_QuantityIs_0() {
        long expectedQuantity = 0;
        //Given
        //When
        //Then
        assert product.calculateProductCost(expectedQuantity).equals(new BigDecimal("0"));
    }

    @Test
    public void testCalculateProductCost_QuantityLessThenPromoQuantity() {
        long expectedQuantity = 3;
        //Given
        //When
        //Then
        assert product.calculateProductCost(expectedQuantity).equals(new BigDecimal("3"));
    }

    @Test
    public void testCalculateProductCost_QuantityEqualsQuantity() {
        long expectedQuantity = 5;
        //Given
        //When
        //Then
        assert product.calculateProductCost(expectedQuantity).equals(new BigDecimal("4"));
    }

    @Test
    public void testCalculateProductCost_QuantiyIsMoreThenPromoQuantity() {
        long expectedQuantity = 13;
        //Given
        //When
        //Then
        assert product.calculateProductCost(expectedQuantity).equals(new BigDecimal("11"));
    }

    @Test
    public void testCalculateProductCost_PromoQuantityIsNull() {
        product = new Product(productName, new BigDecimal("1"), null, null);
        long expectedQuantity = 13;
        //Given
        //When
        //Then
        assert product.calculateProductCost(expectedQuantity).equals(new BigDecimal("13"));
    }
}