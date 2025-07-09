package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductItemRepo {

    private final Map<String, ProductItem> products = new HashMap<>();

    public void addProduct(ProductItem item) {
        ProductItem existing = products.get(item.id());
        if (existing != null) {
            int newStock = existing.stock() + item.stock();
            products.put(item.id(), existing.inStock(newStock));
            StockLog.log("Lagerbestand erhöht bei bestehendem Produkt", item.id(), item.stock());
        } else {
            products.put(item.id(), item);
            StockLog.log("Neues Produkt hinzugefügt", item.id(), item.stock());
        }
    }

    public ProductItem getProduct(String id) {
        return products.get(id);
    }

    public List<ProductItem> getAll() {
        return new ArrayList<>(products.values());
    }

    public boolean reduceStock(String productId, int quantity) {
        ProductItem item = products.get(productId);
        if (item != null && item.stock() >= quantity) {
            products.put(productId, item.inStock(item.stock() - quantity));
            StockLog.log("Ausgang", productId, -quantity);
            return true;
        }
        return false;
    }

    public void increaseStock(String productId, int quantity) {
        ProductItem item = products.get(productId);
        if (item != null) {
            products.put(productId, item.inStock(item.stock() + quantity));
            StockLog.log("Eingang", productId, quantity);
        }
    }



    @Override
    public String toString() {
        return "ProductItemRepo{" +
                "products=" + products +
                '}';
    }
}
