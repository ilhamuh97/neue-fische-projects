import enums.OrderStatus;
import lombok.Getter;
import records.Order;
import records.Product;
import repositories.OrderMapRepo;
import interfaces.OrderRepo;
import repositories.ProductRepo;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class ShopService {
    private final ProductRepo productRepo = new ProductRepo();
    private final OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder(List<String> productIds) throws NullPointerException {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product productToOrder = productRepo
                    .getProductById(productId)
                    .orElseThrow(() -> new NullPointerException("Product not found!"));

            products.add(productToOrder);
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.COMPLETED, Instant.now());
        return orderRepo.addOrder(newOrder);
    }

    public List<Order> getOrdersByOrderStatus(OrderStatus orderStatus) {
        return orderRepo.getOrders().stream()
                .filter(order -> order.orderStatus().equals(orderStatus))
                .toList();
    }

    public void updateOrder(String orderID, OrderStatus newOrderStatus) {
        Order newOrder = orderRepo.getOrderById(orderID);
        if(newOrder == null) {
            throw new NullPointerException("Order not found!");
        }

        newOrder = newOrder.withOrderStatus(newOrderStatus);
        orderRepo.addOrder(newOrder);
    }
}
