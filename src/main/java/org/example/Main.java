package org.example;


import java.util.List;
import java.util.Scanner;

import static org.example.PrintOut.*;

//Todo : Alle Funktionen Testen (ProductItemRepo,Product)|| CSV Hinzufügen -- Auslesen -- Benutzen in ProductItem
// || Tests erstellen ||
// || Alle Funktionen mit einbilden in der While Schleife (Hinzufügen von Stock) || Problem beheben von Change Quantity wird noch nicht gespeichert

public class Main {
    public static void main(String[] args) {

        ProductItemRepo productItemRepo = new ProductItemRepo();


        ProductItem productItem1 = new ProductItem("P1", "Apfel", 1.2, 7);
        ProductItem productItem2 = new ProductItem("P2", "Bannane", 0.8, 21);
        ProductItem productItem3 = new ProductItem("P3", "Birne", 1.2, 25);
        ProductItem productItem4 = new ProductItem("P4", "Tomate", 0.8, 8);

        Product product1 = Product.fromItem(productItem1, 1);
        Product product2 = Product.fromItem(productItem2, 3);
        Product product3 = Product.fromItem(productItem3, 5);
        Product product4 = Product.fromItem(productItem4, 7);

        productItemRepo.addProduct(productItem1);
        productItemRepo.addProduct(productItem2);
        productItemRepo.addProduct(productItem3);
        productItemRepo.addProduct(productItem4);

        OrderRepoInterface orderRepo = new OrderMapRepo();
        ShopService shopService = new ShopService(orderRepo, productItemRepo);
        OrderListRepo orderListRepo = new OrderListRepo();

        Order order1 = new Order("O1", List.of(product1, product2, product3, product4));
        shopService.placeOrder(order1);
        orderListRepo.addOrder(order1);


        Scanner input = new Scanner(System.in);

        PrintLog("______ PRODUKTVERWALTUNGSSYSTEM ______");

        while (true) {
            PrintLog("""
                    
                    Was möchtest du tun?
                    [1] Produkt hinzufügen
                    [2] Bestellung aufgeben
                    [3] Bestellungen anzeigen
                    [4] Bestellungen anzahl umändern
                    [5] Lagerprotokoll anzeigen
                    [6] Alle Produkte anzeigen
                    [quit] Beenden
                    
                    Eingabe:""");

            String command = input.nextLine().trim().toLowerCase();

            switch (command) {
                case "1" -> {
                    Print("Produkt Id: ");
                    String id = input.nextLine();

                    Print("Produkt Name: ");
                    String name = input.nextLine();

                    double price = 0.0;
                    while (true) {
                        Print("Produkt Einzelpreis: ");
                        try {
                            price = Double.parseDouble(input.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            PrintError("Ungültiger Preis.");
                        }
                    }

                    int stock = 0;
                    while (true) {
                        Print("Lagerbestand: ");
                        try {
                            stock = Integer.parseInt(input.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            PrintError("Ungültige Zahl.");
                        }
                    }

                    productItemRepo.addProduct(new ProductItem(id, name, price, stock));
                    PrintGreen("Produkt erfolgreich gespeichert.");
                }

                case "2" -> {
                    Print("Wie viele Produkte möchtest du bestellen?");
                    int count = 0;
                    try {
                        count = Integer.parseInt(input.nextLine());
                    } catch (NumberFormatException e) {
                        PrintError("Ungültige Zahl.");
                        break;
                    }

                    List<ProductItem> allProducts = productItemRepo.getAll();
                    if (allProducts.isEmpty()) {
                        PrintError("Keine Produkte verfügbar.");
                        break;
                    }

                    newOrder:
                    for (int i = 0; i < count; i++) {
                        Print("Produkt-ID: ");
                        String id = input.nextLine();

                        Print("Menge: ");
                        int qty;
                        try {
                            qty = Integer.parseInt(input.nextLine());
                        } catch (NumberFormatException e) {
                            PrintError("Ungültige Menge.");
                            continue;
                        }

                        ProductItem item = productItemRepo.getProduct(id);
                        if (item == null) {
                            PrintError("Produkt nicht gefunden.");
                            continue;
                        }

                        if (item.stock() < qty) {
                            PrintError("Nicht genügend Bestand. Verfügbar: " + item.stock());
                            continue;
                        }

                        Product bestellProdukt = Product.fromItem(item, qty);
                        Order order = new Order("Bestellung_" + (int)(Math.random() * 250) + 1, List.of(bestellProdukt));
                        shopService.placeOrder(order);
                        productItemRepo.reduceStock(item.id(), qty);
                        orderListRepo.addOrder(order);
                        PrintGreen("Bestellung erfolgreich.");
                        break newOrder;
                    }
                }
                case "3" -> {
                    for(Order orderItem : orderListRepo.getAllOrders()){
                        PrintLog(orderItem.toString());
                        break;
                    }
                }
                case "4" -> {
                    try{


                    Print("Bestellungs Id: ");
                    String id = input.nextLine();
                    Order currentOrder = orderListRepo.getOrderById(id);


                    Print("Quantität ändern bei Id: ");
                    String ProductID = input.nextLine();

                    Print("Auf welche zahl soll sich das ganze verändern? ");
                    int Quantity = Integer.parseInt(input.nextLine());

                    currentOrder.changeQuantity(ProductID, Quantity);

                    }catch(Exception e){
                        PrintError("Ein Fehler ist enstanden");
                        break;
                    }
                    break;

                }
                case "5" -> {
                    StockLog.printAllLogs();
                    break;
                }

                case "6" -> {
                    PrintLog("____ ALLE PRODUKTE ____");
                    for (ProductItem item : productItemRepo.getAll()) {
                        PrintLog(item.toString());
                    }
                    PrintLog("________________________");
                }

                case "quit" -> {
                    PrintYellow("Programm wird beendet.");
                    return;
                }

                default -> PrintError("Unbekannter Befehl.");
            }
        }
    }

}

