package marketplace.app.repository;

import marketplace.app.entity.Employee;
import marketplace.app.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByRegistrationNumber (String registrationNumber);
    Boolean existsByRegistrationNumber (String registrationNumber);
    Employee findByName (String name);

    @Query("SELECT s FROM Sale s " +
            "JOIN s.employee e " +
            "WHERE e.registrationNumber = :registrationNumber")
    List<Sale> findSalesByEmployeeRegistrationNumber(@Param("registrationNumber") String registrationNumber);
}
