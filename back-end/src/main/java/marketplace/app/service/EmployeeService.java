package marketplace.app.service;

import marketplace.app.entity.Employee;
import marketplace.app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
