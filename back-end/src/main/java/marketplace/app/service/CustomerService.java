package marketplace.app.service;

import marketplace.app.entity.Customer;
import marketplace.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public String save(Customer customer) {
        if (validCustomerByCpf(customer.getCpf())) {
            customerRepository.save(customer);
        }
        return "Cliente salvo com sucesso!";
    }

    public Boolean validCustomerByCpf(String cpf) {
        Customer customer = customerRepository.findByCpf(cpf);
        if (customer != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "\nCliente com CPF " + customer.getCpf() + " já está cadastrado\n" +
                    "Id: " + customer.getId() + "\n" +
                    "Nome: " + customer.getName()
            );
        } else {
            return true;
        }
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).get();
    }

    public String deleteById(Long id) {
        customerRepository.deleteById(id);
        return "Cliente deletado com sucesso!";
    }

    public String updateById(Long id, Customer customer) {
        customer.setId(id);
        customerRepository.save(customer);
        return "Cliente atualizado com sucesso!";
    }

    public List<Customer> findTop3CustomersByTotalSpend() {
        List<Customer> customers = customerRepository.findTop3CustomersByTotalSpend();
        List<Customer> top3 = new ArrayList<>();
        for (int i = 0; i < customers.size() && i < 3; i++) {
            top3.add(customers.get(i));
        }
        return top3;
    }

    public Customer findByName(String name) {
        return customerRepository.findByName(name);
    }

    public Customer findByCpf(String cpf) {
        return customerRepository.findByCpf(cpf);
    }
}
