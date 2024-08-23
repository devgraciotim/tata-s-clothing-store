package marketplace.app.service;

import marketplace.app.entity.Customer;
import marketplace.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public String save(Customer customer) {
        if (validClientByCpf(customer.getCpf())) {
            customerRepository.save(customer);
        }
        return "Cliente salvo com sucesso!";
    }

    public Boolean validClientByCpf(String cpf) {
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
}
