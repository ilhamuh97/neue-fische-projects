package repos;

import org.junit.jupiter.api.Test;
import records.Product;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

class ProductRepoTest {

    public HashMap<String, Product> createProducts() {
        Product p1 = new Product("1", "A", BigDecimal.valueOf(4.49));
        Product p2 = new Product("2", "B", BigDecimal.valueOf(4.99));
        Product p3 = new Product("3", "C", BigDecimal.valueOf(5.69));
        Product p4 = new Product("4", "D", BigDecimal.valueOf(10.79));

        HashMap<String, Product> products = new HashMap<>();

        products.put(p1.id(), p1);
        products.put(p2.id(), p2);
        products.put(p3.id(), p3);
        products.put(p4.id(), p4);

        return products;
    }

    @Test
    void add_shouldReturn5_whenCalledWith5Products() {
        // Adjust
        HashMap<String, Product> products = this.createProducts();
        ProductRepo productRepo = new ProductRepo(products);

        Product p5 = new Product("5", "E", BigDecimal.valueOf(11.99));

        // Action
        productRepo.add(p5);
        int actualSize = productRepo.products.size();

         // Assert
        assertEquals(5, actualSize);
    }

    @Test
    void remove_shouldReturn3_whenCalledWith3Products() {
        // Adjust
        HashMap<String, Product> products = this.createProducts();
        ProductRepo productRepo = new ProductRepo(products);

        // Action
        productRepo.remove(products.get("1"));
        int actualSize = productRepo.products.size();

        // Assert
        assertEquals(3, actualSize);
    }

    @Test
    void getProductById_shouldReturnProductProps_whenCalledWithProductId1() {
        // Adjust
        HashMap<String, Product> products = this.createProducts();
        ProductRepo productRepo = new ProductRepo(products);

        // Action
        Product product  =productRepo.getProductById("1");

        assertEquals("1", product.id());
        assertEquals("A", product.name());
        assertEquals(BigDecimal.valueOf(4.49) , product.price());
    }

    @Test
    void getProductsByIds_shouldReturn2_whenCalledWith2ExistingProductIds() {
        // Adjust
        HashMap<String, Product> products = this.createProducts();
        ProductRepo productRepo = new ProductRepo(products);
        String[] productIds = {"2", "3"};
        // Action

        ArrayList<Product> foundProducts  = productRepo.getProductsByIds(productIds);

        assertEquals(2, foundProducts.size());
    }

    @Test
    void getProductsByIds_shouldReturn1_whenCalledWith2ExistingProductIds() {
        // Adjust
        HashMap<String, Product> products = this.createProducts();
        ProductRepo productRepo = new ProductRepo(products);
        String[] productIds = {"2", "100"};
        // Action

        ArrayList<Product> foundProducts  = productRepo.getProductsByIds(productIds);

        assertEquals(1, foundProducts.size());
    }
}