package marketplace.app.repository;

import marketplace.app.entity.Customer;
import marketplace.app.entity.Employee;
import marketplace.app.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query(value = "SELECT * FROM Sale ORDER BY total_value DESC LIMIT 1", nativeQuery = true)
    Sale findTopSaleByTotalValue();

    List<Sale> findByEmployee(Employee employee);

    List<Sale> findByCustomer(Customer customer);
}
