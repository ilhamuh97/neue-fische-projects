import records.Order;
import records.Product;
import repos.ProductRepo;
import services.ShopService;

import java.math.BigDecimal;
import java.util.HashMap;

public class Main {
    static void main(String[] args) {
        Product productA = new Product("1", "product A", new BigDecimal("10.49"));
        Product productB = new Product("2", "product B", new BigDecimal("4.99"));
        Product productC = new Product("3", "product C", new BigDecimal("7.99"));
        Product productD = new Product("4", "product D", new BigDecimal("7.69"));
        Product productE = new Product("5", "product E", new BigDecimal("12.19"));

        ProductRepo productRepo = new ProductRepo();
        productRepo.add(productA);
        productRepo.add(productB);
        productRepo.add(productC);
        productRepo.add(productD);
        productRepo.add(productE);


        ShopService shopServiceA = new ShopService(productRepo);

        String[] productIds = {"1", "2"};
        shopServiceA.addNewOrder(productIds);

        shopServiceA.printAllOrders();
        Order order = shopServiceA.getOrder("1");
        System.out.println(order);
    }
}
