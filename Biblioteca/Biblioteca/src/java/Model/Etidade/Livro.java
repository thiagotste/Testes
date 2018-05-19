package Model.Etidade;

import java.io.Serializable;

public class Livro implements Serializable {

    private int codLivro;
    private int codEscritor;
    private int codTipo;
    private int codBiblioteca;
    private String nome;
    private String editora;
    private String volume;
    private String edicao;
    private String idioma;
    private String numPag;
    private String anoLancamento;
    private String dtAquisicao;
    private String descricao;
    private String formato;
    private String  situacao;

    public Livro() {
        this.codLivro = 0;
        this.codEscritor = 0;
        this.codTipo = 0;
        this.codBiblioteca = 0;
        this.nome = "";
        this.editora = "";
        this.volume = "";
        this.edicao = "";
        this.idioma = "";
        this.numPag = "";
        this.anoLancamento = "";
        this.dtAquisicao = "";
        this.descricao = "";
        this.formato = "";
        this.situacao = "";
    }

    public Livro(int codLivro, int codEscritor, int codTipo,
            int codBiblioteca, String nome,
            String editora, String volume, String edicao,
            String idioma, String numPag, String anoLancamento,
            String dtAquisicao, String descricao, String formato,
            String situacao) {
        this.codLivro = codLivro;
        this.codEscritor = codEscritor;
        this.codTipo = codTipo;
        this.codBiblioteca = codBiblioteca;
        this.nome = nome;
        this.editora = editora;
        this.volume = volume;
        this.edicao = edicao;
        this.idioma = idioma;
        this.numPag = numPag;
        this.anoLancamento = anoLancamento;
        this.dtAquisicao = decomporData(dtAquisicao);
        this.descricao = descricao;
        this.formato = formato;
        this.situacao = situacao;
    }

    public int getCodLivro() {
        return codLivro;
    }

    public void setCodLivro(int codLivro) {
        this.codLivro = codLivro;
    }

    public int getCodEscritor() {
        return codEscritor;
    }

    public void setCodEscritor(int codEscritor) {
        this.codEscritor = codEscritor;
    }

    public int getCodTipo() {
        return codTipo;
    }

    public void setCodTipo(int codTipo) {
        this.codTipo = codTipo;
    }

    public int getCodBiblioteca() {
        return codBiblioteca;
    }

    public void setCodBiblioteca(int codBiblioteca) {
        this.codBiblioteca = codBiblioteca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getNumPag() {
        return numPag;
    }

    public void setNumPag(String numPag) {
        this.numPag = numPag;
    }
    
    public String getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(String anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getDtAquisicao() {
        return dtAquisicao;
    }

    public void setDtAquisicao(String dtAquisicao) {
        this.dtAquisicao = decomporData(dtAquisicao);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
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
