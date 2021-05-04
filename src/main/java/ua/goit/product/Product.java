package ua.goit.product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Optional;

@Getter
@Setter
public class Product {
    private @Setter(AccessLevel.NONE)
    String name;
    private @Setter(AccessLevel.NONE)
    BigDecimal regularPrice;
    private Optional<BigDecimal> promoPrice;
    private Optional<Integer> promoQuantity;

    public String getName() {
        return name;
    }

    public Product(String name, BigDecimal regularPrice, BigDecimal promoPrice, Integer promoQuantity) {
        this.name = name;
        this.regularPrice = regularPrice;
        this.promoPrice = Optional.ofNullable(promoPrice);
        this.promoQuantity = Optional.ofNullable(promoQuantity);
    }

    public BigDecimal calculateProductCost(long quant) {
        long numOfPromoPrices;
        long numOfRegularPrices;
        if (promoQuantity.isPresent()) {
            numOfPromoPrices = quant / promoQuantity.get();
            numOfRegularPrices = quant % promoQuantity.get();
        }else{
            numOfPromoPrices = 0;
            numOfRegularPrices = quant;
        }
        BigDecimal productCost;
        if (quant < 0) {
            throw new ProductException("Wrong product quantity, must be > 0");
        }
        if (quant == 0) {
            productCost = new BigDecimal(0);
            return productCost;
        }
        if(promoPrice.isPresent()) {
            if (numOfRegularPrices == 0) {
                productCost = promoPrice.get().multiply(new BigDecimal(numOfPromoPrices));
            } else {
                productCost = promoPrice.get().multiply(new BigDecimal(numOfPromoPrices).add(regularPrice.multiply(new BigDecimal(numOfRegularPrices))));
            }
        }else {
            productCost = regularPrice.multiply(new BigDecimal(numOfRegularPrices));
        }
            return productCost;
    }
}
