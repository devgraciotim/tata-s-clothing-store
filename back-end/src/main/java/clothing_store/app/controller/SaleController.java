package clothing_store.app.controller;

import clothing_store.app.entity.Sale;
import clothing_store.app.service.SaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Sale sale) {
        try {
            String message = saleService.save(sale);
            return new ResponseEntity<String>(message, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        try {
            List<Sale> sales = saleService.findAll();
            return new ResponseEntity<List<Sale>>(sales, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Sale sale = saleService.findById(id);
            return new ResponseEntity<Sale>(sale, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            String message = saleService.deleteById(id);
            return new ResponseEntity<String>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @Valid @RequestBody Sale sale) {
        try {
            String message = saleService.updateById(id, sale);
            return new ResponseEntity<String>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
