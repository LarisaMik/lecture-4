package myprojects.automation.assignment4.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

/**
 * Hold Product information that is used among tests.
 */
public class ProductData {
    private String name;
    private int qty;
    private float price;

    public ProductData(String name, int qty, float price) {
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getQty() {
        return qty;
    }

    public String getPrice() {
        DecimalFormatSymbols separators = new DecimalFormatSymbols();
        separators.setDecimalSeparator(',');
        return new DecimalFormat("#0.00", separators).format(price);
    }

    /**
     * @return New Product object with random name, quantity and price values.
     */
    public static ProductData generate() {
        Random random = new Random();
        return new ProductData(
                "New Product " + UUID.randomUUID().toString(),
                random.nextInt(100) + 1,
                (float) Math.round(random.nextInt(100_00) + 1) / 100);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductData product = (ProductData) o;
        return name.equalsIgnoreCase(product.name) &&
                price == product.price &&
                qty ==  product.qty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, qty, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", qty='" + qty + '\'' +
                '}';
    }
}
