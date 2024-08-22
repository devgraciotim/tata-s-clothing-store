package clothing_store.app.service;

import clothing_store.app.entity.Client;
import clothing_store.app.entity.Employee;
import clothing_store.app.entity.Product;
import clothing_store.app.entity.Sale;
import clothing_store.app.repository.ClientRepository;
import clothing_store.app.repository.EmployeeRepository;
import clothing_store.app.repository.ProductRepository;
import clothing_store.app.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProductRepository productRepository;

    public String save(Sale sale) {
        sale.setClient(validClientById(sale.getClient().getId()));

        sale.setEmployee(validEmployeeById(sale.getEmployee().getId()));

        List<Product> productsInfo = new ArrayList<>();
        Double total_value = 0.0;
        sale.getProducts().forEach(product -> {
            if (productRepository.existsById(product.getId())) {
                Product productSale = productRepository.findById(product.getId()).get();
                productsInfo.add(productSale);
            }
        });

        sale.setProducts(productsInfo);
        sale.setTotal_value(total_value);

        saleRepository.save(sale);
        return "Venda salvo com sucesso!";
    }

    public Client validClientById(Long id) {
        if (clientRepository.existsById(id)) {
            Client client = clientRepository.findById(id).get();
            return client;
        }
        throw new RuntimeException("Cliente com id " + id + " não foi encontrado");
    }

    public Employee validEmployeeById(Long id) {
        if (clientRepository.existsById(id)) {
            Employee employee = employeeRepository.findById(id).get();
            return employee;
        }
        throw new RuntimeException("Funcionário com id " + id + "não foi encontrado");
    }

    public List<Product> validProductsById(List<Product> products) {
        return null;
    }

    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    public Sale findById(Long id) {
        return saleRepository.findById(id).get();
    }

    public String deleteById(Long id) {
        saleRepository.deleteById(id);
        return "Venda deletado com sucesso!";
    }

    public String updateById(Long id, Sale sale) {
        sale.setId(id);
        saleRepository.save(sale);
        return "Venda atualizado com sucesso!";
    }
}
