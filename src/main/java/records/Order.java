package records;

import enums.OrderStatus;

import java.util.List;

public record Order(
        String id,
        List<Product> products,
        OrderStatus orderStatus
) {

    public Order withStatus(OrderStatus orderStatus) {
        return new Order(this.id, this.products, orderStatus);
    }
}
