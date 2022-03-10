package venturaHR.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("profissional")
public class Profissional extends Usuario{
    
    private String cpf;

    public Profissional(String nome, String email, String password) {
        super(nome, email, password);
    }

    public Profissional() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

 
}
