package ua.goit.product;

import java.math.BigDecimal;

public class Product {
    String name;
    BigDecimal regularPrice;
    BigDecimal promoPrice;
    int promoQuantity;

    public Product(String name, BigDecimal regularPrice, BigDecimal promoPrice, int promoQuantity) {
        this.name = name;
        this.regularPrice = regularPrice;
        this.promoPrice = promoPrice;
        this.promoQuantity = promoQuantity;
    }

    public BigDecimal calculateProductCost(int quant) {
        int numOfPromoPrices = quant / promoQuantity;
        int numOfRegularPrices = quant % promoQuantity;
        BigDecimal productCost = null;
        if (quant < 0) {
            throw new ProductException("Wrong product quantity, must be > 0");
        }
        if (quant == 0) {
            productCost = new BigDecimal(0);
            return productCost;
        }
        if (numOfRegularPrices == 0) {
            productCost = promoPrice.multiply(new BigDecimal(numOfPromoPrices));
        } else if (numOfRegularPrices != 0) {
            productCost = promoPrice.multiply(new BigDecimal(numOfPromoPrices).add(regularPrice.multiply(new BigDecimal(numOfRegularPrices))));
        }
        return productCost;
    }
}
