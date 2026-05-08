package repos;

import records.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductRepo {
    HashMap<String, Product> products;

    public ProductRepo() {
        this.products = new HashMap<>();
    }

    public ProductRepo(HashMap<String, Product> products) {
        this.products = products;
    }

    public void add(Product product) {
        products.put(product.id(), product);
    }

    public void remove(Product product) {
        products.remove(product.id(), product);
    }

    public Product getProductById(String id) {
        return products.get(id);
    }

    public ArrayList<Product> getProductsByIds(String[] ids) {
        ArrayList<Product> results = new ArrayList<>();
        for(String id: ids) {
            results.add(products.get(id));
        }

        return results;
    }

}
