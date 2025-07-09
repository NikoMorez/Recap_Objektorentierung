package org.example;

public record Product(String id, String name, double price, int quantity, double sum) {

    public Product(String id, String name, double price, int quantity) {
        this(id,name,price,quantity, createSum(price,quantity));
    }

    public static Product fromItem(ProductItem item, int quantity) {
        return new Product(item.id(), item.name(), item.price(), quantity);
    }

    public double totalPrice() {
        return price * quantity;
    }

    public Product withQuantity(int newQuantity) {
        return new Product(id, name, price, newQuantity, createSum(price, newQuantity));
    }

    static double createSum(double price, int quantity) {
        return  (price * quantity);
    }

}
