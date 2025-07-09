package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderMapRepo implements OrderRepoInterface {
    private final Map<String, Order> ordersMap = new HashMap<>();

    @Override
    public void addOrder(Order order) {
        ordersMap.put(order.id(),order);
    }

    @Override
    public boolean removeOrder(String id) {
        return ordersMap.remove(id) != null;
    }


    @Override
    public Order getOrderById(String orderId) {
        return ordersMap.get(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        return List.copyOf(ordersMap.values());
    }
}
