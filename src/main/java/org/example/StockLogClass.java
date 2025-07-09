package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.example.PrintOut.*;

class StockLogClass {
    String type;
    String productId;
    int stock;
    LocalDateTime timestamp;

    public StockLogClass(String type, String productId, int stock) {
        this.type = type;
        this.productId = productId;
        this.stock = stock;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "StockLogClass{" +
                "type='" + type + '\'' +
                ", productId='" + productId + '\'' +
                ", stock=" + stock +
                ", timestamp=" + timestamp +
                '}';
    }

}



class StockLog {
    private static final List<StockLogClass> entries = new ArrayList<>();

    public static void log(String type, String productId, int quantity) {
        StockLogClass entry = new StockLogClass(type, productId, quantity);
        entries.add(entry);
        PrintLog(entry.toString());
    }

    public static List<StockLogClass> getLog() {
        return entries;
    }

    public static void printAllLogs() {
        PrintLog("___________Lagerprotokoll____________");
        for (StockLogClass entry : entries) {
            PrintLog(entry.toString());
        }
        PrintLog("______________________________________");
    }
}

