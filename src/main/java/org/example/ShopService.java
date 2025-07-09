package org.example;

import static org.example.PrintOut.*;

public class ShopService {

    private final OrderRepoInterface orderRepo;
    private final ProductItemRepo productRepo;

    public ShopService(OrderRepoInterface orderRepo, ProductItemRepo productRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }


    public void placeOrder(Order order) {

        TestPrint("[ShopService] Neue Bestellung wird geprüft: " + order.id());

        boolean allProductsExist = true;

        for (Product p : order.products()) {
            if (productRepo.getProduct(p.id()) == null) {
                PrintError("Produkt nicht gefunden: " + p.id());
                allProductsExist = false;
                break;
            }
        }

        if (!allProductsExist) {
            PrintError("Ein oder mehrere Produkte existieren nicht!");
            return;
        }

        for (Product p : order.products()) {
            ProductItem item = productRepo.getProduct(p.id());
            if (item == null) {
                PrintError("Produkt nicht im Lager: " + p.id());
                return;
            }
            if (item.stock() < p.quantity()) {
                PrintError("Nicht genügend Lagerbestand für Produkt: " + p.id());
                return;
            }
        }


        for (Product p : order.products()) {
            productRepo.reduceStock(p.id(), p.quantity());
        }

        orderRepo.addOrder(order);
        PrintGreen("Bestellung erfolgreich aufgegeben: " + order.id());
    }

}
