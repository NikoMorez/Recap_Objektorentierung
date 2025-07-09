package org.example;

import java.util.ArrayList;
import java.util.List;


public class OrderListRepo implements OrderRepoInterface {

    @Override
    public void addOrder(Order order) {
        orders.add(order);
    }

    public OrderListRepo() {
        this.orders = new ArrayList<>();
    }

    @Override
    public boolean removeOrder(String id) {

        return orders.removeIf(o -> o.id().equals(id));
    }

    @Override
    public Order getOrderById(String orderId) {


       for(Order o : orders) {

           if(o.id().equals(orderId)) {

               return o;
           }
       }
       return null;

    }

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    private ArrayList<Order> orders;


    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void addOrders(ArrayList<Order> orders) {
        this.orders.addAll(orders);
    }
}
