package records;

import java.math.BigDecimal;
import java.util.HashMap;

public record Order(String id, HashMap<String, OrderedProduct> orderedProducts, BigDecimal totalSum) {
}
