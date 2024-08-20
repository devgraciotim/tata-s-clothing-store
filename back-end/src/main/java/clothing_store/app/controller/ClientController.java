package clothing_store.app.controller;

import clothing_store.app.entity.Client;
import clothing_store.app.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Client client) {
        try {
            String message = clientService.save(client);
            return new ResponseEntity<String>(message, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        try {
            List<Client> clients = clientService.findAll();
            return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Client client = clientService.findById(id);
            return new ResponseEntity<Client>(client, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            String message = clientService.deleteById(id);
            return new ResponseEntity<String>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @Valid @RequestBody Client client) {
        try {
            String message = clientService.updateById(id, client);
            return new ResponseEntity<String>(message, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
