import records.Product;
import repositories.ProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {

    @org.junit.jupiter.api.Test
    void getProducts() {
        //GIVEN
        ProductRepo repo = new ProductRepo();

        //WHEN
        List<Product> actual = repo.getProducts();

        //THEN
        List<Product> expected = new ArrayList<>();
        expected.add(new Product("1", "Apfel"));
        assertEquals(actual, expected);
    }

    @org.junit.jupiter.api.Test
    void getProductById() {
        //GIVEN
        ProductRepo repo = new ProductRepo();

        //WHEN
        Optional<Product> actualOptional = repo.getProductById("1");

        //THEN
        Product expected = new Product("1", "Apfel");
        assertTrue(actualOptional.isPresent());
        assertEquals(actualOptional.get(), expected);
    }

    @org.junit.jupiter.api.Test
    void addProduct() {
        //GIVEN
        ProductRepo repo = new ProductRepo();
        Product newProduct = new Product("2", "Banane");

        //WHEN
        Product actual = repo.addProduct(newProduct);
        Optional<Product> productOptional = repo.getProductById("2");

        //THEN
        Product expected = new Product("2", "Banane");

        assertEquals(actual, expected);
        assertTrue(productOptional.isPresent());
        assertEquals(productOptional.get(), expected);
    }

    @org.junit.jupiter.api.Test
    void removeProduct() {
        //GIVEN
        ProductRepo repo = new ProductRepo();

        //WHEN
        repo.removeProduct("1");

        //THEN
        assertFalse(repo.getProductById("1").isPresent());
    }
}
