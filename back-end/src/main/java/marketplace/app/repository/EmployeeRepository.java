package marketplace.app.repository;

import marketplace.app.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByRegistrationNumber (String registrationNumber);

    Boolean existsByRegistrationNumber (String registrationNumber);
}
