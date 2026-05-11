import records.Order;
import records.Product;
import repos.ProductRepo;
import services.ShopService;

import java.math.BigDecimal;

public class Main {
    static void main() {
        ShopService shopServiceA = getShopService();

        shopServiceA.printAllOrders();
        Order orderA = shopServiceA.getOrder("1");
        Order orderB = shopServiceA.getOrder("2");
        System.out.println(orderA);
        System.out.println(orderB);
    }

    private static ShopService getShopService() {
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

        String[] productIdsA = {"1", "2"};
        shopServiceA.addOrder(productIdsA);
        String[] productIdsB = {"3", "3"};
        shopServiceA.addOrder(productIdsB);
        return shopServiceA;
    }
}
