package clothing_store.app.service;

import clothing_store.app.entity.Product;
import clothing_store.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public String save(Product product) {
        productRepository.save(product);
        return "Produto salvo com sucesso!";
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
