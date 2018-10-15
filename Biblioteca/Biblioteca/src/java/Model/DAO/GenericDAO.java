package Model.DAO;

import java.util.ArrayList;

public abstract class GenericDAO<T> {
    public abstract T selecionarEntidade(int priKey);
    public abstract int adicionarEntidade(T entidade);
    public abstract int atualizarEntidade(T entidade);
    public abstract int deletarEntidade(int priKey);
    public abstract int deletarTodasEntidades();
    public abstract ArrayList selecionarTodasEntidades();
}
