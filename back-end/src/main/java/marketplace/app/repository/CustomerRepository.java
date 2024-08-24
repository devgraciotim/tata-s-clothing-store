package marketplace.app.repository;

import marketplace.app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCpf(String cpf);

    Customer findByName(String name);

    @Query("SELECT c FROM Customer c " +
            "JOIN c.sales s " +
            "GROUP BY c.id " +
            "ORDER BY SUM(s.total_value) DESC")
    List<Customer> findTop3CustomersByTotalSpend();


}
