package marketplace.app.service;

import marketplace.app.entity.Product;
import marketplace.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public String save(Product product) {
        if (validProductByName(product.getName())) {
            productRepository.save(product);
        }
        return "Produto salvo com sucesso!";
    }

    public Boolean validProductByName(String name) {
        Product product = productRepository.findByName(name);
        if (product != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "\nProduto com nome " + product.getName() + " já está cadastrado\n" +
                    "Id: " + product.getId() + "\n" +
                    "Valor: R$ " + product.getPrice()
            );
        } else {
            return true;
        }
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    public String deleteById(Long id) {
        productRepository.deleteById(id);
        return "Produto deletado com sucesso!";
    }

    public String updateById(Long id, Product product) {
        product.setId(id);
        productRepository.save(product);
        return "Produto atualizado com sucesso!";
    }
}
