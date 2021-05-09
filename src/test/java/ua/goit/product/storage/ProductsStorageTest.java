package ua.goit.product.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.goit.product.Product;
import ua.goit.product.ProductException;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ProductsStorageTest {
    ProductsStorage testStorage = new ProductsStorage();
    Product product = new Product("P", new BigDecimal("1"), new BigDecimal("10"), 11);

    @Before
    public void fillTestStorage() {
        testStorage.addToStorage(product);
    }

    @Test
    public void testAddToStorage_HappyPath() {
        //Then
        assert testStorage.getProduct(product.getName()).equals(product);
    }

    @Test
    public void testAddToStorage_TheProductHasAlreadyExistInStorage() {
        //When
        testStorage.addToStorage(product);
        //Then
        ArrayList<Product> list = new ArrayList<>();
        list.add(testStorage.getProduct(product.getName()));
        assert list.size() == 1;
    }

    @Test
    public void testAddToStorage_TheProductIsNull() {
        //When
        product = null;
        //Then
        Assert.assertThrows("", NullPointerException.class,
                () -> testStorage.addToStorage(product));
    }

    @Test
    public void testGetProduct_HappyPath() {
        //When
        Product thisProduct = testStorage.getProduct(product.getName());
        //Then
        assert thisProduct.getName().equals(product.getName());
    }

    @Test
    public void testGetProduct_TheProductDoesNotExistInStorage() {
        //When
        String productName = "Z";
        //Then
        Assert.assertThrows("", ProductException.class,
                () -> testStorage.getProduct(productName));
    }
}