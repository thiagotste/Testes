package Model.Etidade;

import java.io.Serializable;

public class Biblioteca implements Serializable{
    private int codBiblioteca;
    private String nome;
    private String endereco;
    private String cep;
    private String cidade;
    private String uf;
    private String telefone1;
    private String email;
    
    public Biblioteca(){
        codBiblioteca = 0;
        nome = "";
        endereco = "";
        cep = "";
        cidade = "";
        uf = "";
        telefone1 = "";
        email = "";
    }
    public Biblioteca(String nome, String endere,
            String cep, String cid, String uf, String tel1,
            String email){
        this.nome = nome;
        endereco = endere;
        this.cep = cep;
        cidade = cid;
        this.uf = uf;
        telefone1 = tel1;
        this.email = email;
    }    
    public int getCodBiblioteca(){
        return codBiblioteca;
    }
    public void setCodBiblioteca(int cod){
        codBiblioteca = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
