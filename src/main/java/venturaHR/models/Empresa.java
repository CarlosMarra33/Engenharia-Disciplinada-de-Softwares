package venturaHR.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("empresa")
public class Empresa extends Usuario {
    
    private String cpnj;

    public Empresa() {
    }

    public Empresa(String nome, String email, String password, String cpnj) {
        super(nome, email, password);
        this.cpnj = cpnj;
    }

    public Empresa(String cpnj) {
        this.cpnj = cpnj;
    }

    public String getCpnj() {
        return cpnj;
    }

    public void setCpnj(String cpnj) {
        this.cpnj = cpnj;
    }
}
