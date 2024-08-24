package marketplace.app.repository;

import marketplace.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);

    @Query("SELECT p FROM Product p " +
            "JOIN p.sales s " +
            "GROUP BY p.id " +
            "ORDER BY COUNT(s) DESC")
    Product findTopByOrderBySalesDesc();

    @Query(value = "SELECT * FROM product " +
            "ORDER BY price DESC " +
            "LIMIT 1", nativeQuery = true)
    Product mostExpensive();
}
