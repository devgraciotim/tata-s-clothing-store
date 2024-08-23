package marketplace.app.repository;

import marketplace.app.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByCpf(String cpf);
}
