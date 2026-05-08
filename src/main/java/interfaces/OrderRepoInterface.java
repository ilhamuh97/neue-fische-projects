package interfaces;

import records.Order;

import java.util.ArrayList;

public interface OrderRepoInterface {
    void add(Order order);
    void remove(Order order);
    Order getById(String id);
    ArrayList<Order> getAll();
}
