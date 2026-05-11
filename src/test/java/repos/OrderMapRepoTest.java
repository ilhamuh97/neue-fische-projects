package repos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import records.Order;
import records.OrderedProduct;
import records.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

class OrderMapRepoTest {
    final String ORDER_ID_1 = "o1";

    final String PRODUCT_ID_1 = "1";
    final String PRODUCT_ID_2 = "2";
    final String PRODUCT_ID_3 = "3";

    final String ORDERED_PRODUCT_ID_1 = "op1";
    final String ORDERED_PRODUCT_ID_2 = "op2";
    final String ORDERED_PRODUCT_ID_3 = "op3";

    Order createOrder() {
        Product p1 = new Product(PRODUCT_ID_1, "A", BigDecimal.valueOf(4.49));
        Product p2 = new Product(PRODUCT_ID_2, "B", BigDecimal.valueOf(4.99));
        Product p3 = new Product(PRODUCT_ID_3, "C", BigDecimal.valueOf(5.69));

        OrderedProduct oP1 = new OrderedProduct(p1, 1);
        OrderedProduct oP2 = new OrderedProduct(p2, 2);
        OrderedProduct oP3 = new OrderedProduct(p3, 3);

        HashMap<String, OrderedProduct> oPs = new HashMap<>();
        oPs.put(ORDERED_PRODUCT_ID_1, oP1);
        oPs.put(ORDERED_PRODUCT_ID_2, oP2);
        oPs.put(ORDERED_PRODUCT_ID_3, oP3);

        BigDecimal totalPrice = BigDecimal.ZERO;
        for(OrderedProduct orderedProduct : oPs.values()) {
            BigDecimal orderedProductPrice =  orderedProduct.product().price();
            BigDecimal orderedProductQuantity = BigDecimal.valueOf(orderedProduct.quantity());
            BigDecimal sumPriceTota = orderedProductPrice.multiply(orderedProductQuantity);
            totalPrice = totalPrice.add(sumPriceTota);
        }

        return new Order(ORDER_ID_1, oPs, totalPrice);
    }

    @Test
    void getOrders_shouldReturn1_whenCalledWithALlOrders() {
        // Adjust
        Order order = createOrder();
        HashMap<String, Order> orders = new HashMap<>();
        orders.put(order.id(), order);
        OrderMapRepo orderMapRepo = new OrderMapRepo(orders);

        //Action
        HashMap<String, Order> allOrders = orderMapRepo.getOrders();

        //Assert
        assertEquals(1, allOrders.size());
    }

    @Test
    void setOrders_shouldReturn1_whenCalledWithOneOrder() {
        // Adjust
        Order order = createOrder();
        HashMap<String, Order> orders = new HashMap<>();
        orders.put(order.id(), order);
        OrderMapRepo orderMapRepo = new OrderMapRepo();

        //Action
        orderMapRepo.setOrders(orders);
        HashMap<String, Order> allOrders = orderMapRepo.getOrders();

        //Assert
        assertEquals(1, allOrders.size());
    }

    @Test
    void add_shouldReturn1_whenCalledWithOneOrder() {
        // Adjust
        Order order = createOrder();
        OrderMapRepo orderMapRepo = new OrderMapRepo();

        //Action
        orderMapRepo.add(order);
        HashMap<String, Order> allOrders = orderMapRepo.getOrders();

        //Assert
        assertEquals(1, allOrders.size());
    }

    @Test
    void remove_shouldReturn0_whenCalledWithOneOrder() {
        // Adjust
        Order order = createOrder();
        HashMap<String, Order> orders = new HashMap<>();
        orders.put(order.id(), order);
        OrderMapRepo orderMapRepo = new OrderMapRepo(orders);

        //Action
        orderMapRepo.remove(order);

        //Assert
        assertEquals(0, orderMapRepo.orders.size());
    }

    @Test
    void getById_shouldReturnOrderId1_whenCalledWithOrderId1r() {
        // Adjust
        Order order = createOrder();
        HashMap<String, Order> orders = new HashMap<>();
        orders.put(order.id(), order);
        OrderMapRepo orderMapRepo = new OrderMapRepo(orders);

        //Action
        Order foundOrder = orderMapRepo.getById(ORDER_ID_1);

        //Assert
        assertEquals(ORDER_ID_1, foundOrder.id());
    }

    @Test
    void getAll_shouldReturn1_whenCalledWithAllProduct() {
        // Adjust
        Order order = createOrder();
        HashMap<String, Order> orders = new HashMap<>();
        orders.put(order.id(), order);
        OrderMapRepo orderMapRepo = new OrderMapRepo(orders);

        //Action
        ArrayList<Order> allOrders = orderMapRepo.getAll();

        //Assert
        assertEquals(1, allOrders.size());
    }

    @Test
    void getTotalPrice_shouldReturnTotalPrice_whenCalledWithOrder1TotalPrice() {
        // Adjust
        Order order = createOrder();
        HashMap<String, Order> orders = new HashMap<>();
        orders.put(order.id(), order);
        OrderMapRepo orderMapRepo = new OrderMapRepo(orders);

        //Action
        BigDecimal totalPrice = orderMapRepo.getTotalPrice(ORDER_ID_1);

        //Assert
        assertEquals(BigDecimal.valueOf(31.54), totalPrice);
    }

    @Test
    void editQuantity_shouldReturn5_whenCalledWith5() {
        // Adjust
        Order order = createOrder();
        HashMap<String, Order> orders = new HashMap<>();
        orders.put(order.id(), order);
        OrderMapRepo orderMapRepo = new OrderMapRepo(orders);

        String orderId = ORDER_ID_1;

        //Action
        orderMapRepo.editQuantity(orderId, ORDERED_PRODUCT_ID_1, 5);
        Order foundOrder = orderMapRepo.getById(orderId);
        OrderedProduct orderedProduct = foundOrder.orderedProducts().get(ORDERED_PRODUCT_ID_1);


        //Assert
        assertEquals(5, orderedProduct.quantity());
        assertEquals(BigDecimal.valueOf(49.50).setScale(2, RoundingMode.HALF_UP), foundOrder.totalSum());
    }
}