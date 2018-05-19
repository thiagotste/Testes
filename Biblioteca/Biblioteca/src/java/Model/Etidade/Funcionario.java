package Model.Etidade;

import java.io.Serializable;

public class Funcionario implements Serializable{
    private int codFuncionario;
    private int codBiblioteca;
    private String priNome;
    private String lastNome;
    private String email;
    private String cpf;
    private String sexo;
    private String dtNascimento;
    private String endereco;
    private String complemento;
    private String cep;
    private String cidade;
    private String uf;
    private String telefone1;
    private String telefone2;
    private String senha;
    private String tipo;

    public Funcionario() {
        this.codFuncionario = 0;
        this.codBiblioteca = 0;
        this.priNome = "";
        this.lastNome = "";
        this.email = "";
        this.cpf = "";
        this.sexo = "";
        this.dtNascimento = "";
        this.endereco = "";
        this.complemento = "";
        this.cep = "";
        this.cidade = "";
        this.uf = "";
        this.telefone1 = "";
        this.telefone2 = "";
        this.senha = "";
        this.tipo = "";
    }

    public Funcionario(int codBiblioteca, String priNome, 
             String lastNome, String email, String cpf, String sexo,
            String dt_nascimento, String endereco,
            String complemente, String cep, String cidade,
            String uf, String telefone1, String telefone2,
            String senha, String tipo) {
        this.codBiblioteca = codBiblioteca;
        this.priNome = priNome;
        this.lastNome = lastNome;
        this.email = email;
        this.cpf = cpf;
        this.sexo = sexo;
        this.dtNascimento = decomporData(dt_nascimento);
        this.endereco = endereco;
        this.complemento = complemente;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
        this.senha = senha;
        this.tipo = tipo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getCodFuncionario() {
        return codFuncionario;
    }

    public void setCodFuncionario(int codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    public int getCodBiblioteca() {
        return codBiblioteca;
    }

    public void setCodBiblioteca(int codBiblioteca) {
        this.codBiblioteca = codBiblioteca;
    }
    
    public String getPriNome() {
        return priNome;
    }

    public void setPriNome(String priNome) {
        this.priNome = priNome;
    }

    public String getLastNome() {
        return lastNome;
    }

    public void setLastNome(String lastNome) {
        this.lastNome = lastNome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = decomporData(dtNascimento);
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
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

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    private String decomporData(String data) {
        if (data.indexOf("-") == -1) {
            String arrayData[] = data.split("/");
            data = arrayData[2] + "/" + arrayData[1] + "/" + arrayData[0];
        }else{
            String arrayData[] = data.split("-");
            data = arrayData[2] + "/" + arrayData[1] + "/" + arrayData[0];
        }

        return data;
    }
}
