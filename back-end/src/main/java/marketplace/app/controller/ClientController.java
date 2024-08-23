package marketplace.app.controller;

import marketplace.app.entity.Client;
import marketplace.app.service.ClientService;
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
        String message = clientService.save(client);
        return new ResponseEntity<String>(message, HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<Client> clients = clientService.findAll();
        return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Client client = clientService.findById(id);
        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        String message = clientService.deleteById(id);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @Valid @RequestBody Client client) {
        String message = clientService.updateById(id, client);
        return new ResponseEntity<String>(message, HttpStatus.OK);

    }
}
