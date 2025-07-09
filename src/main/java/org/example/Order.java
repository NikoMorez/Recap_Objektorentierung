package org.example;

import java.util.ArrayList;
import java.util.List;


import static org.example.PrintOut.TestPrint;

public record Order(String id, List<Product> products, double TotalSum) {

    public Order(String id, List<Product> products) {
        this(id, products, CalculateSum(products));
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", products=" + products +
                ", TotalSum=" + TotalSum +
                '}';
    }

    private static double CalculateSum(List<Product> products) {

        double sum = 0.0;

        for( Product product : products ) {
            sum += product.price()*product.quantity();
        }

        return sum;

    }

    public Order changeQuantity(String productId, int newQuantity) {
        List<Product> updatedProducts = new ArrayList<>();

        for (Product p : products) {
            if (p.id().equals(productId)) {
                Product updated = p.withQuantity(newQuantity);
                updatedProducts.add(updated);
                TestPrint("Produkt");
                TestPrint(p.toString());
                TestPrint(updated.toString());
            } else {
                updatedProducts.add(p);
            }
        }

        return new Order(id, updatedProducts);
    }


}
