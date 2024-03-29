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

        BigDecimal productCost;

        if (quant < 0) {
            throw new ProductException("Wrong product quantity, must be > 0");
        } else if (quant == 0) {
            productCost = new BigDecimal(0);
            return productCost;
        } else if (promoQuantity.isPresent()) {

            if (quant < promoQuantity.get()) {
                productCost = regularPrice.multiply(new BigDecimal(quant));
            } else {
                long numOfPromoPrices = quant / promoQuantity.get();
                long numOfRegularPrices = quant % promoQuantity.get();
                BigDecimal promocost = promoPrice.get().multiply(new BigDecimal(numOfPromoPrices));
                BigDecimal regularcost = regularPrice.multiply(new BigDecimal(numOfRegularPrices));
                productCost = promocost.add(regularcost);
            }
        } else {
            productCost = regularPrice.multiply(new BigDecimal(quant));
        }
        return productCost;
    }
}
