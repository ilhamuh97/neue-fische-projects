import records.Product;
import repos.ProductRepo;
import services.ShopService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> cart = new ArrayList<>();
        ShopService shopService = getShopService();

        System.out.println("Available Products:");
        printAvailableProducts();
        while (true) {
            System.out.println();
            System.out.print("Enter product ID (or type 'done' to finish): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("done")) {
                break;
            }

            Product product = shopService.getProductRepo().getProductById(input);
            if (product != null) {
                cart.add(input);
                System.out.println(product.name() + " added.");
            } else {
                System.out.println("Invalid product ID.");
            }
        }

        if (cart.isEmpty()) {
            System.out.println("No products selected.");
            return;
        }

        String[] productIds = cart.toArray(new String[0]);
        shopService.addOrder(productIds);
        System.out.println();

        System.out.println("All Orders");
        shopService.printAllOrders();

        scanner.close();
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

        return new ShopService(productRepo);
    }

    private static void printAvailableProducts() {
        System.out.println("1 - product A");
        System.out.println("2 - product B");
        System.out.println("3 - product C");
        System.out.println("4 - product D");
        System.out.println("5 - product E");
    }
}