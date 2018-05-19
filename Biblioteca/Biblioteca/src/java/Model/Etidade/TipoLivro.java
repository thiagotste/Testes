package Model.Etidade;

import java.io.Serializable;

public class TipoLivro implements Serializable{
    private int codTipo;
    private String nome;
    
    public TipoLivro() {
        this.codTipo = 0;
        this.nome = "";
    }

    public TipoLivro(String nome) {
        this.nome = nome;
    }

    public int getCodTipo() {
        return codTipo;
    }

    public void setCodTipo(int codTipo) {
        this.codTipo = codTipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
}
