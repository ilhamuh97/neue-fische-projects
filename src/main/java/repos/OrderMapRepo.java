package repos;

import interfaces.OrderRepoInterface;
import records.Order;
import records.OrderedProduct;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        Order order = orders.get(orderId);

        if(order == null) {
            throw new IllegalArgumentException("Order not found");
        }

        return order.totalSum();
    }

    public void editQuantity(String orderId, String orderedProductId, int quantity) {
        if(quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }

        Order order = orders.get(orderId);
        if(order == null) {
            throw new IllegalArgumentException("Order not found");
        }

        OrderedProduct orderedProduct = order.orderedProducts().get(orderedProductId);
        if(orderedProduct == null) {
            throw new IllegalArgumentException("Ordered product not found");
        }

        OrderedProduct updatedProduct = new OrderedProduct(orderedProduct.product(), quantity);
        order.orderedProducts().put(orderedProductId, updatedProduct);
        recalculateTotalPrice(orderId);
    }

    private void recalculateTotalPrice(String orderId) {
        Order order = orders.get(orderId);
        BigDecimal totalPrice = BigDecimal.ZERO;

        for(OrderedProduct orderedProduct : order.orderedProducts().values()) {
            BigDecimal orderedProductPrice = orderedProduct.product().price();
            BigDecimal orderedProductQuantity = BigDecimal.valueOf(orderedProduct.quantity());
            BigDecimal subTotal = orderedProductPrice.multiply(orderedProductQuantity);
            totalPrice = totalPrice.add(subTotal);
        }

        Order updatedOrder = new Order(
                order.id(),
                order.orderedProducts(),
                totalPrice.setScale(2, RoundingMode.HALF_UP)
        );

        orders.put(updatedOrder.id(), updatedOrder);
    }
}
