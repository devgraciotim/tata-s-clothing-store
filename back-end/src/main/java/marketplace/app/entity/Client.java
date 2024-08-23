package marketplace.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank(message = "Nome não pode estar vazio.")
    String name;
    @CPF(message = "Insira um CPF válido")
    String cpf;
    @Positive(message = "Insira uma idade válida")
    Integer age;
    @Pattern(regexp = "^\\d{12,13}$", message = "Telefone inválido.")
    String phone;

    @OneToMany(mappedBy = "client")
    List<Sale> sales;
}
