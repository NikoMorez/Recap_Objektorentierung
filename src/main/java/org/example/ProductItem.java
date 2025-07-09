package org.example;

public record ProductItem(String id, String name, double price, int stock) {

    public ProductItem inStock(int newStock) {
        return new ProductItem(id, name, price, newStock);
    }
}
