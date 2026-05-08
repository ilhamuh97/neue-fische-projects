package repos;

import interfaces.OrderRepoInterface;
import records.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderMapRepo implements OrderRepoInterface {
    HashMap<String, Order> orders;

    public OrderMapRepo() {
        this.orders = new HashMap<>();
    }

    public OrderMapRepo(HashMap<String, Order> orders) {
        this.orders = orders;
    }

    public HashMap<String, Order> getOrders() {
        return orders;
    }

    public void setOrders(HashMap<String, Order> orders) {
        this.orders = orders;
    }

    public void add(Order order) {
        orders.put(order.id(), order);
    }

    public void remove(Order order) {
        orders.remove(order.id(), order);
    }

    public Order getById(String id) {
        return orders.get(id);
    }

    public ArrayList<Order> getAll() {
        return new ArrayList<>(orders.values());
    }

    public BigDecimal getTotalPrice(String orderId) {
        Order foundOrder = orders.get(orderId);
        if(foundOrder == null) {
            System.out.println("Order with ID " + orderId + " does not exist");
            return null;
        }

        return foundOrder.totalSum();
    }

    public Order editQuantity(String orderId, String productId, int quantity) {
        Order foundOrder = orders.get(orderId);
        if(foundOrder == null) {
            System.out.println("Order with ID " + orderId + " does not exist");
            return null;
        }

        return null;
    }
}
