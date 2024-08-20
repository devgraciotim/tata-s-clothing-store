package clothing_store.app.service;

import clothing_store.app.entity.Product;
import clothing_store.app.entity.Sale;
import clothing_store.app.repository.ProductRepository;
import clothing_store.app.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    public String save(Sale sale) {
        saleRepository.save(sale);
        return "Venda salvo com sucesso!";
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
