package edu.pk.java8.basics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by prajena on 2/27/17.
 */
public class PredicateDemonstration {
    public static void main(String[] args) {
        // Examples as we do always (without Predicate). Here for every time we have to define separate method to filter.
        System.out.println("Examples as we do always (without Predicate)>>>");
        List<Product> products = ProductStore.findProductByAvailability(true);
        System.out.println("Items in Stock are :");
        report(products);

        products = ProductStore.findProductByUnit(Unit.PER_KILO);
        System.out.println("Items with Unit type KILO :");
        report(products);

        products = ProductStore.findProductWithMinPrice(100L);
        System.out.println("Items with Min Price 100 : ");
        report(products);

        // Examples with Predicate (from Java 8 onwards). Here the single method fulfills all the requirements
        // ony by passing the filter condition as predicate
        System.out.println("\n\nExamples with Predicate (from Java 8 onwards)>>>");
        products = ProductStore.findProductWithFilter(p -> p.isAvailable());
        System.out.println("Items in Stock are :");
        report(products);

        products = ProductStore.findProductWithFilter(p -> p.getUnit() == Unit.PER_KILO);
        System.out.println("Items with Unit type KILO :");
        report(products);

        products = ProductStore.findProductWithFilter(p -> p.getPrice() >= 100L);
        System.out.println("Items with Min Price 100 : ");
        report(products);

    }

    private static void report(List<Product> products) {
        for (Product p : products) {
            System.out.println(p);
        }
    }
}

class ProductStore {
    private static final List<Product> productList = new ArrayList<>();

    static {
        productList.add(new Product(1001L, "Cadbury Diary Milk Small", 20d, Unit.PER_PIECE, true));
        productList.add(new Product(1002L, "Sugar", 150d, Unit.PER_KILO, true));
        productList.add(new Product(1003L, "Refined Oil", 50d, Unit.PER_LITRE, true));
        productList.add(new Product(1004L, "Water Bottle", 80d, Unit.PER_PIECE, true));
        productList.add(new Product(1005L, "Salt", 20d, Unit.PER_KILO, false));
        productList.add(new Product(1006L, "Atta", 50d, Unit.PER_KILO, false));
    }

    public static List<Product> findProductWithFilter(Predicate<Product> filter) {
        List<Product> result = new ArrayList<>();
        for (Product p : productList) {
            if (filter.test(p))
                result.add(p);
        }
        return result;
    }

    public static List<Product> findProductByAvailability(boolean availability) {
        List<Product> result = new ArrayList<>();
        for (Product p : productList) {
            if (availability == p.isAvailable())
                result.add(p);
        }
        return result;
    }

    public static List<Product> findProductByUnit(Unit perKilo) {
        List<Product> result = new ArrayList<>();
        for (Product p : productList) {
            if (perKilo == p.getUnit())
                result.add(p);
        }
        return result;
    }

    public static List<Product> findProductWithMinPrice(long price) {
        List<Product> result = new ArrayList<>();
        for (Product p : productList) {
            if (p.getPrice() >= price)
                result.add(p);
        }
        return result;
    }
}

class Product {
    private Long id;
    private String name;
    private Double price;
    private Unit unit;
    private boolean isAvailable;

    public Product(Long id, String name, Double price, Unit unit, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.isAvailable = isAvailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", unit=" + unit +
                ", isAvailable=" + isAvailable +
                '}';
    }
}

enum Unit {
    PER_PIECE, PER_KILO, PER_LITRE;
}