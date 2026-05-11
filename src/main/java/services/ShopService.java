package services;

import records.Order;
import records.OrderedProduct;
import records.Product;
import repos.OrderMapRepo;
import repos.ProductRepo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class ShopService {
    ProductRepo productRepo;
    OrderMapRepo orderMapRepo;

    public ShopService(ProductRepo productRepo) {
        this.productRepo = productRepo;
        this.orderMapRepo = new OrderMapRepo();
    }

    public void addOrder(String[] productIds) {
        HashMap<String, OrderedProduct> orderedProducts = new HashMap<>();
        BigDecimal totalPrice = new BigDecimal(String.valueOf(BigDecimal.ZERO));
        for (String productId : productIds) {
            Product product = productRepo.getProductById(productId);
            if (product == null) {
                System.out.println("Product with ID " + productId + " does not exist");
                continue;
            }

            if (orderedProducts.get(productId) == null) {
                OrderedProduct orderedProduct = new OrderedProduct(product, 1);
                orderedProducts.put(productId, orderedProduct);
            } else {
                OrderedProduct foundOrderedProduct = orderedProducts.get(productId);
                OrderedProduct orderedProduct = new OrderedProduct(product, foundOrderedProduct.quantity() + 1);
                orderedProducts.put(productId, orderedProduct);
            }

            totalPrice = totalPrice.add(product.price());

        }

        if (orderedProducts.isEmpty()) {
            System.out.println("Cannot create an empty order");
            return;
        }

        this.storeProductsToNewOrder(orderedProducts, totalPrice);
    }

    public void printAllOrders() {
        System.out.println(this.getAllOrders());
    }

    public ArrayList<Order> getAllOrders() {
        return orderMapRepo.getAll();
    }

    public Order getOrder(String orderId) {
        return orderMapRepo.getById(orderId);
    }

    public ProductRepo getProductRepo(){
        return productRepo;
    }

    private void storeProductsToNewOrder(
            HashMap<String, OrderedProduct> orderedProducts,
            BigDecimal totalPrice
    ) {
        Order order = new Order(this.generateOrderId(), orderedProducts, totalPrice);
        orderMapRepo.add(order);
        System.out.println("Order created successfully. Your order ID is: " + order.id());
    }

    private String generateOrderId() {
        return Integer.toString(orderMapRepo.getOrders().size() + 1);
    }

}
