package marketplace.app.service;

import marketplace.app.entity.Client;
import marketplace.app.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public String save(Client client) {
        if (validClientByCpf(client.getCpf())) {
            clientRepository.save(client);
        }
        return "Cliente salvo com sucesso!";
    }

    public Boolean validClientByCpf(String cpf) {
        Client client = clientRepository.findByCpf(cpf);
        if (client != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "\nCliente com CPF " + client.getCpf() + " já está cadastrado\n" +
                    "Id: " + client.getId() + "\n" +
                    "Nome: " + client.getName()
            );
        } else {
            return true;
        }
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
