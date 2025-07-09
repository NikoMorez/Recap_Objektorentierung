package org.example;

import java.util.List;

public interface OrderRepoInterface {
    public void addOrder(Order order);
    boolean removeOrder(String orderId);


    Order getOrderById(String orderId);
    List<Order> getAllOrders();

}
