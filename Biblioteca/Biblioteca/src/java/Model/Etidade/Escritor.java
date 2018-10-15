package Model.Etidade;

import java.io.Serializable;

public class Escritor implements Serializable{
    private int codEscritor;
    private String priNome;
    private String lastNome;

    public Escritor() {
        this.codEscritor = 0;
        this.priNome = "";
        this.lastNome = "";
    }
    
    public Escritor(String priNome, String lastNome) {
        this.priNome = priNome;
        this.lastNome = lastNome;
    }

    public int getCodEscritor() {
        return codEscritor;
    }

    public void setCodEscritor(int codEscritor) {
        this.codEscritor = codEscritor;
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
    
}

