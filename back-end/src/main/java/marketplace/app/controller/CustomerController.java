package marketplace.app.controller;

import marketplace.app.entity.Customer;
import marketplace.app.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Customer customer) {
        String message = customerService.save(customer);
        return new ResponseEntity<String>(message, HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<Customer> customers = customerService.findAll();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        String message = customerService.deleteById(id);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @Valid @RequestBody Customer customer) {
        String message = customerService.updateById(id, customer);
        return new ResponseEntity<String>(message, HttpStatus.OK);

    }

    @GetMapping("/top3")
    public ResponseEntity<?> top3ByTotalSpend() {
        List<Customer> customers = customerService.findTop3CustomersByTotalSpend();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) {
        Customer customer = customerService.findByName(name);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/findByCpf/{cpf}")
    public ResponseEntity<?> findByCpf(@PathVariable String cpf) {
        Customer customer = customerService.findByCpf(cpf);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
