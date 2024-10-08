package marketplace.app.service;

import marketplace.app.entity.Customer;
import marketplace.app.entity.Employee;
import marketplace.app.entity.Product;
import marketplace.app.entity.Sale;
import marketplace.app.repository.CustomerRepository;
import marketplace.app.repository.EmployeeRepository;
import marketplace.app.repository.ProductRepository;
import marketplace.app.repository.SaleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProductRepository productRepository;

    public String save(Sale sale) {
        sale.setCustomer(validClientById(sale.getCustomer().getId()));

        sale.setEmployee(validEmployeeById(sale.getEmployee().getId()));

        sale.setProducts(validProductsById(sale.getProducts()));

        sale.setTotal_value(calculateTotalValue(sale.getProducts()));

        saleRepository.save(sale);
        return "Venda salvo com sucesso!";
    }

    public Customer validClientById(Long id) {
        if (customerRepository.existsById(id)) {
            Customer customer = customerRepository.findById(id).get();
            return customer;
        }
        throw new EntityNotFoundException("Cliente com id " + id + " não foi encontrado");
    }

    public Employee validEmployeeById(Long id) {
        if (customerRepository.existsById(id)) {
            Employee employee = employeeRepository.findById(id).get();
            return employee;
        }
        throw new EntityNotFoundException ("Funcionário com id " + id + "não foi encontrado");
    }

    public List<Product> validProductsById(List<Product> products) {
        List<Product> productsInfo = new ArrayList<>();
        products.forEach(product -> {
            if (productRepository.existsById(product.getId())) {
                Product productSale = productRepository.findById(product.getId()).get();
                productsInfo.add(productSale);
            } else {
                throw new EntityNotFoundException("Produto com id " + product.getId() + " não encontrado");
            }
        });
        return productsInfo;
    }

    public Double calculateTotalValue(List<Product> products) {
        Double total_value = products.stream().mapToDouble(Product::getPrice).sum();
        return total_value;
    }

    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    public Sale findById(Long id) {
        if (saleRepository.findById(id).isPresent()) {
            return saleRepository.findById(id).get();
        } else {
            throw new EntityNotFoundException ("Venda com id " + id + " não encontrada");
        }
    }

    public String deleteById(Long id) {
        if (saleRepository.existsById(id)) {
            saleRepository.deleteById(id);
            return "Venda deletado com sucesso!";
        } else {
            throw new EntityNotFoundException ("Venda com id " + id + " não encontrada");
        }
    }

    public String updateById(Long id, Sale sale) {
        if (saleRepository.existsById(id)) {
            sale.setId(id);
            saleRepository.save(sale);
            return "Venda atualizado com sucesso!";
        } else {
            throw new EntityNotFoundException ("Venda com id " + id + " não encontrada");
        }
    }

    public Sale findTopSaleByTotalValue() {
        return saleRepository.findTopSaleByTotalValue();
    }

    public List<Sale> findByEmployee(Employee employee) {
        return saleRepository.findByEmployee(employee);
    }

    public List<Sale> findByCustomer(Customer customer) {
        return saleRepository.findByCustomer(customer);
    }
}
