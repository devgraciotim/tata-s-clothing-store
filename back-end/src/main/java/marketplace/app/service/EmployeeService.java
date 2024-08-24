package marketplace.app.service;

import marketplace.app.entity.Customer;
import marketplace.app.entity.Employee;
import marketplace.app.entity.Sale;
import marketplace.app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Random;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public String save(Employee employee) {
        employee.setRegistrationNumber(generateUniqueRegistrationNumber());
        employeeRepository.save(employee);
        return "Funcionário salvo com sucesso!";
    }

    private String generateUniqueRegistrationNumber() {
        String registrationNumber;
        do {
            registrationNumber = generateRegistrationNumber();
        } while (employeeRepository.existsByRegistrationNumber(registrationNumber));
        return registrationNumber;
    }

    private String generateRegistrationNumber() {
        Random random = new Random();
        int number = random.nextInt((int) Math.pow(10, 5));
        String formattedNumber = String.format("%0" + 5 + "d", number);
        return "RN" + formattedNumber;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).get();
    }

    public String deleteById(Long id) {
        employeeRepository.deleteById(id);
        return "Funcionário deletado com sucesso!";
    }

    public String updateById(Long id, Employee employee) {
        employee.setId(id);
        employeeRepository.save(employee);
        return "Funcionário atualizado com sucesso!";
    }

    public Employee findByRegistrationNumber(String registrationNumber) {
        return employeeRepository.findByRegistrationNumber(registrationNumber);
    }

    public List<Sale> findSalesByEmployeeRegistrationNumber(String registrationNumber) {
        return employeeRepository.findSalesByEmployeeRegistrationNumber(registrationNumber);
    }

    public Employee findByName(String name) {
        return employeeRepository.findByName(name);
    }
}
