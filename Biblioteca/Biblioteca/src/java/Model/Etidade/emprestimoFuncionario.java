package Model.Etidade;

import java.io.Serializable;

public class emprestimoFuncionario implements Serializable{
    private int codFuncionario;
    private int codLivro;
    private int codEscritor;
    private String dataEmprestimo;
    private String dataEntrega;
    private String situacao;

    public emprestimoFuncionario() {
        codFuncionario = 0;
        codLivro = 0;
        codEscritor = 0;
        dataEmprestimo = "";
        dataEntrega = "";
        situacao = "1";
    }

    public emprestimoFuncionario(int codFuncionario, int codLivro, int codEscritor,
            String dataEmprestimo, String dataEntrega, String situacao) {
        this.codFuncionario = codFuncionario;
        this.codLivro = codLivro;
        this.codEscritor = codEscritor;
        this.dataEmprestimo = decomporData(dataEmprestimo);
        this.dataEntrega = decomporData(dataEntrega);
        this.situacao = situacao;
    }

    public int getCodFuncionario() {
        return codFuncionario;
    }

    public void setCodFuncionario(int codFuncionario) {
        this.codFuncionario = codFuncionario;
    }

    public int getCodEscritor() {
        return codEscritor;
    }

    public void setCodEscritor(int codEscritor) {
        this.codEscritor = codEscritor;
    }

    public int getCodLivro() {
        return codLivro;
    }

    public void setCodLivro(int codLivro) {
        this.codLivro = codLivro;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = decomporData(dataEmprestimo);
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = decomporData(dataEntrega);
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    

    private String decomporData(String data) {
        if (!data.equals("")) {
            if (data.indexOf("-") == -1) {
                String arrayData[] = data.split("/");
                data = arrayData[2] + "/" + arrayData[1] + "/" + arrayData[0];
            } else {
                String arrayData[] = data.split("-");
                data = arrayData[2] + "/" + arrayData[1] + "/" + arrayData[0];
            }
        }

        return data;
    }
}
