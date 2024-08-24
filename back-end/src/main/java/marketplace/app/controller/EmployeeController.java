package marketplace.app.controller;

import marketplace.app.entity.Employee;
import marketplace.app.entity.Sale;
import marketplace.app.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Employee employee) {
        String message = employeeService.save(employee);
        return new ResponseEntity<String>(message, HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<Employee> employees = employeeService.findAll();
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Employee employee = employeeService.findById(id);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        String message = employeeService.deleteById(id);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @Valid @RequestBody Employee employee) {
        String message = employeeService.updateById(id, employee);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @GetMapping("/findByRN/{registrationNumber}")
    public ResponseEntity<?> findByRegistrationNumber(@PathVariable String registrationNumber) {
        Employee employee = employeeService.findByRegistrationNumber(registrationNumber);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @GetMapping("/findSalesByRN/{registrationNumber}")
    public ResponseEntity<?> findSalesByEmployeeRegistrationNumber(@PathVariable String registrationNumber) {
        List<Sale> sales = employeeService.findSalesByEmployeeRegistrationNumber(registrationNumber);
        return new ResponseEntity<List<Sale>>(sales, HttpStatus.OK);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        Employee employee = employeeService.findByName(name);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }
}
