package ua.goit.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.goit.Application;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class IntegrationTests {
    private ConfigurableInputStream in;
    private ByteArrayOutputStream out;


    @Before
    public void init() {
        in = new ConfigurableInputStream();
        out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));
    }

    @After
    public void clear() {
        out.reset();
    }

    @Test
    public void enterProductsHappyPath(){
        //Given
        in.add("aaacccdb");
        //When
        Application.main(new String[0]);
        //Then
        assertEquals("Please enter products:\n" +
                "Total coast for aaacccdb = 11.00\n", getActualResult());
    }

    @Test
    public void enterProductsEnteredNotExistProduct(){
        //Given
        in.add("aaacccdbx");
        //When
        Application.main(new String[0]);
        //Then
        assertEquals("Please enter products:\n" +
                "The product X is not found\n" +
                "Total coast for aaacccdbx = 11.00\n", getActualResult());
    }

    @Test
    public void enterProductsEmptyList(){
        //Given
        in.add("");
        in.add("");
        in.add("cccddddddaaaab");
        //When
        Application.main(new String[0]);
        //Then
        assertEquals("Please enter products:\n" +
                "Please enter products:\n" +
                "Please enter products:\n" +
                "Total coast for cccddddddaaaab = 16.00\n", getActualResult());
    }

    private String getActualResult() {
        return out.toString(StandardCharsets.UTF_8).replaceAll("\r\n", "\n");
    }
}
