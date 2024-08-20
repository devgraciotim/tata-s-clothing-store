package clothing_store.app.service;

import clothing_store.app.entity.Client;
import clothing_store.app.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public String save(Client client) {
        clientRepository.save(client);
        return "Cliente salvo com sucesso!";
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long id) {
        return clientRepository.findById(id).get();
    }

    public String deleteById(Long id) {
        clientRepository.deleteById(id);
        return "Cliente deletado com sucesso!";
    }

    public String updateById(Long id, Client client) {
        client.setId(id);
        clientRepository.save(client);
        return "Cliente atualizado com sucesso!";
    }
}
