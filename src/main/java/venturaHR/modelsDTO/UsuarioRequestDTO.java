package venturaHR.modelsDTO;

public class UsuarioRequestDTO {
    
    private String nome;
    private  String email;
    private String password;
    private String cpf;
    private String cnpj;
    public UsuarioRequestDTO(String nome, String email, String password, String cpf, String cnpj) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.cnpj = cnpj;
    }
    public UsuarioRequestDTO() {
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
