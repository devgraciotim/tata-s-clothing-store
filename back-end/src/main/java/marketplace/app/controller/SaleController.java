package marketplace.app.controller;

import marketplace.app.entity.Sale;
import marketplace.app.service.SaleService;
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
        String message = saleService.save(sale);
        return new ResponseEntity<String>(message, HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<Sale> sales = saleService.findAll();
        return new ResponseEntity<List<Sale>>(sales, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Sale sale = saleService.findById(id);
        return new ResponseEntity<Sale>(sale, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        String message = saleService.deleteById(id);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @Valid @RequestBody Sale sale) {
        String message = saleService.updateById(id, sale);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }
}
