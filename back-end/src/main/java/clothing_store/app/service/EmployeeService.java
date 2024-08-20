package clothing_store.app.service;

import clothing_store.app.entity.Employee;
import clothing_store.app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public String save(Employee employee) {
        employeeRepository.save(employee);
        return "Funcionário salvo com sucesso!";
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
