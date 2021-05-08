package ua.goit.product.storage;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoint;
import ua.goit.product.Product;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class StorageFillingTest {

    ProductsStorage testStorage;
    private List<Product> list = List.of(
            new Product("A", new BigDecimal("1.25"), new BigDecimal("3.00"), 3),
            new Product("B", new BigDecimal("4.25"), null, null),
            new Product("C", new BigDecimal("1.00"), new BigDecimal("5.00"), 6),
            new Product("D", new BigDecimal("0.75"), null, null)
    );

    @Before
    public void createStorage() {
        testStorage = new ProductsStorage();
    }

    @Test
    public void testStorageFilling_HappyPath() {
        //When
        fillStorage(list);
        //Then
        list.forEach((p) -> p.getName().equals(testStorage.getProduct(p.getName())));
    }

    @Test
    public void testStorageFilling_ListIsNull() {
        //Then
        assertThrows("", NullPointerException.class,
                () -> new StorageFilling().fillStorage(null));
    }

    public void fillStorage(List<Product> listOfProducts) {
        for (Product product : listOfProducts) {
            try {
                testStorage.addToStorage(product);
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
    }
}