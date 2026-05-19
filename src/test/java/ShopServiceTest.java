import enums.OrderStatus;
import org.junit.jupiter.api.Test;
import records.Order;
import records.Product;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = shopService.addOrder(productsIds);

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")), OrderStatus.COMPLETED, Instant.now());
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_expectNull() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        //WHEN + THEN
        assertThrows(NullPointerException.class, () -> shopService.addOrder(productsIds));
    }

    @Test
    void getOrdersByOrderStatus_whenMatchNoMatch_shouldReturnCorrectedContainingStatus() {
        // GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        // WHEN
        shopService.addOrder(productsIds);
        List<Order> inDeliveryOrders = shopService.getOrdersByOrderStatus(OrderStatus.IN_DELIVERY);
        List<Order> completedOrders = shopService.getOrdersByOrderStatus(OrderStatus.COMPLETED);

        // THEN
        assertTrue(inDeliveryOrders.isEmpty());
        assertFalse(completedOrders.isEmpty());
    }

    @Test
    void updateOrder_checkEveryStatuses() {
        // GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        // WHEN
        Order newOrder = shopService.addOrder(productsIds);
        shopService.updateOrder(newOrder.id(), OrderStatus.PROCESSING);

        // THEN
        assertEquals(OrderStatus.PROCESSING, shopService.getOrderRepo().getOrderById(newOrder.id()).orderStatus());
        assertNotEquals(OrderStatus.COMPLETED, shopService.getOrderRepo().getOrderById(newOrder.id()).orderStatus());
        assertNotEquals(OrderStatus.IN_DELIVERY, shopService.getOrderRepo().getOrderById(newOrder.id()).orderStatus());
    }

    @Test
    void updateOrder_whenNoMatch_shouldThrowError() {
        // GIVEN
        ShopService shopService = new ShopService();

        // WHEN + THEN
        assertThrows(NullPointerException.class, () ->shopService.updateOrder("5", OrderStatus.PROCESSING));
    }
}
